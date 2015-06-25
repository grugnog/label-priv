package com.semanticbits.label.controller

import com.semanticbits.label.service.BarcodeService
import com.semanticbits.label.service.LabelServiceException
import com.semanticbits.label.service.SearchService
import grails.converters.JSON
import grails.web.JSONBuilder
import org.apache.commons.lang.StringUtils
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

/**
 * User: Janakiram Gollapudi
 * Date: 6/22/15
 */
class SearchController {
    static final String INDEX_VIEW = 'index'
    SearchService searchService
    BarcodeService barcodeService

/**
 * To display label home page(which is search label page)
 * This method also gets called when user clicks on search button
 * @return home page if no term provided or returns search results page
 */
    Object index() {
        //If search string provided then render search results page
        if (params.term) {
            String term = params.term
            render(view:'results', model:[term:term])
        }
        //Otherwise return same page(Home page)
        else {
            render(view:INDEX_VIEW)
        }
    }

    /**
     * Processes a bar code and returns value
     */
    JSON processBarCodeImage() {
        def results = []
        if (request instanceof MultipartHttpServletRequest) {
            for (filename in request.fileNames) {

                MultipartFile file = request.getFile(filename)

                def scannedCode = barcodeService.scanBarcode(file.bytes)

                JSONBuilder jSON = new JSONBuilder()
                JSON json = jSON.build {
                    size = file.size
                    code = scannedCode
                }

                results = json.toString()
            }
        }

        render results
    }

/**
 * This method gets called on every search button click and on every pagination
 * @return labels information based on search string as JSON
 */
    JSON searchJSON() {
        //User entered term
        String term = params.term
        //To capture labels information
        List<Object> labels = []
        //To capture total labels
        int iTotalRecords = 0
        //To capture total labels that we are showing
        int iTotalDisplayRecords = 0
        //If term provided then search by term and return the results
        if (term) {
            try {
                //draw captures current page of datatable
                int page = params.draw ? params.int('draw') : 0
                //Get the results using search term
                log.info "Searching for labels with given string: ${term}"
                Map<String, Object> searchResults = searchService.search(term, page)
                log.info "Found ${searchResults.totalCount} labels"
                iTotalRecords = searchResults.totalCount ?: 0
                iTotalDisplayRecords = searchResults.totalCount ?: 0
                //loop through labels that are found and construct view to show results
                searchResults.labels.each { label ->
                    labels << [
                            labelDetails:label
                    ]
                }
                log.info "Showing results of page ${params.draw}"
            }
            catch (LabelServiceException e) {
                log.error("Exception occurred while searching term ${params.term}: ${e}")
                render([draw: params.draw, iTotalRecords:iTotalRecords,
                        iTotalDisplayRecords:iTotalDisplayRecords, aaData:labels] as JSON)
            }
        }
        //Otherwise return empty results
        render([draw: params.draw, iTotalRecords:iTotalRecords,
                iTotalDisplayRecords:iTotalDisplayRecords, aaData:labels] as JSON)
    }

    /**
     * The method returns details for a LABEL term
     */
    Object details() {
        Map termDetails = searchService.getLabelDetails(params.id)
        render(view: 'view', model: [details: termDetails, title: params.title], term: params.term, page: params.page)
    }
}
