package com.semanticbits.label.service

import grails.transaction.Transactional
import groovy.json.JsonBuilder;
import groovy.json.JsonSlurper;

@Transactional
class SearchService {

    def grailsApplication
    def openFDAService
    def resultSanitizationList = ['description':'DESCRIPTION','boxed_warning':'WARNING','adverse_reactions':'ADVERSE REACTIONS']

    /**
     * Search for a term, return the result from the specified page
     * @param term search term(s)
     * @param page result page number to return, page index start from 0 
     * @return Searh results in JSON format
     * {
     *  totalCount: 10 
     *  totalPages: 1
     *  currentPage: 0
     *  currentCount: 10
     *  labels:[
     *  {id:'f229e866-5775-4e42-a316-8480dd92fec6',
     *   title:'TITANIUM DIOXIDE, OCTINOXATE, ZINC OXIDE',
     *   description:'a description'
     *  }
     *  ...
     *  ]
     * }
     * @throws LabelServiceException
     */
    String search(String term, int page = 0) throws LabelServiceException{
        if(term) {
            log.debug("Searching for term  ${term}")
            String response = openFDAService.invoke(buildSearchRequestParam(sanitizeSearchTerm(term), page, grailsApplication.config.itemsPerPage))
            log.debug("Retrieved response ${response} from openFDA")
            return generateResponse(response)
        } else {
            throw new LabelServiceException('Invalid search, empty search string')
        }
    }

    /**
     * Sanitize the input search term per the openFDA API rules
     * @param term search term
     * @return sanitized term
     */
    private String sanitizeSearchTerm(String term){
        // Replace all spaces with +, https://open.fda.gov/api/reference/#spaces
        return term.replaceAll(' ', '+')
    }

    /**
     * Build the search request param as expected by the OpenFDA API    
     * @param term
     * @param page
     * @param itemsPerPage
     * @return return the search request parameters
     */
    private String buildSearchRequestParam(String term, int page, int itemsPerPage){
        StringBuilder sb = new StringBuilder('search=');
        sb.append(term).append('&limit=').append(itemsPerPage)
        sb.append('&skip=').append(page);
        return sb.toString()
    }

    /**
     * Generate the respose JSON string to be returned
     * @param response response returned by the openFDA service
     * @return JSON in the format
     * [ {id:'93321bf0-d58d-4ac1-bfa7-cb033ca9df85', name:'label', description: 'A description of the label' },
     *  {id:'93321bf0-d58d-4ac1-bfa7-1234434', name:'label 2', description: 'A description of the label 2' }
     * ] 
     */
    private generateResponse(String response){
        try {
            def responseJson =  new JsonSlurper().parseText(response)

            def totalCount = responseJson.meta?.results?.total
            def currentPage =  responseJson.meta?.results?.skip
            def totalPages
            if(totalCount && responseJson.meta?.results?.limit){
                totalPages = totalCount/responseJson.meta?.results?.limit
            } else if(totalCount){
                totalPages = 1
            } else {
                totalPages = 0
            }
            def labels
            if(responseJson.results){
                labels = responseJson.results.collect {
                    [id: it.id,
                     title: getLabelName(it),
                     description: it.description?it.description[0].replaceFirst('DESCRIPTION ', ''):'']
                }
            }
            def resultJson = new JsonBuilder(totalCount:totalCount,totalPages:Math.ceil(totalPages).intValue(),currentCount:labels.size(), currentPage:currentPage, labels:labels )
            return resultJson.toPrettyString()
        }catch (Exception e){
            throw new LabelServiceException("Error parsing response from openFDA api", e)
        }
    }

    /**
     * Extract the label name from label json
     * @param labelJson json representation of the label record
     * @return selected displayname of the label
     */
    private getLabelName(def labelJson){
        if(labelJson.openfda.generic_name){
            return labelJson.openfda.generic_name[0]
        }else if(labelJson.openfda.brand_name){
            return labelJson.openfda.brand_name[0]
        }else if (labelJson.openfda.substance_name){
            return labelJson.openfda.substance_name[0]
        }else{
            return labelJson.id
        }
    }
}

