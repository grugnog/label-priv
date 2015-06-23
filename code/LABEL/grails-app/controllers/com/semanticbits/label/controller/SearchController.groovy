package com.semanticbits.label.controller

import com.semanticbits.label.service.LabelServiceException
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * User: Janakiram Gollapudi
 * Date: 6/22/15
 */
class SearchController {
    static defaultAction = "index"
    def searchService

    /**
     * To display label home page(which is search label page)
     * This method also gets called when user clicks on search button
     * @return home page if no term provided or returns search results page
     */
    def index() {
       //If search string provided then render search results page
       if(params.term){
           String term = params.term
           render(view:'results', model:[term:term])
       }
       //Otherwise return same page(Home page)
       else {
            render(view:'index')
       }
    }

    /**
     * This method gets called on every search button click and on every pagination
     * @return labels information based on search string as JSON
     */
    def searchJSON() {
        //User entered term
        String term = params.term
        //To capture labels information
        def labels = []
        //To capture total labels
        int iTotalRecords = 0
        //To capture total labels that we are showing
        int iTotalDisplayRecords = 0
        //If term provided then search by term and return the results
        if(term) {
            try {
                //draw captures current page of datatable
                int page = params.draw?params.int('draw'):0
                //Get the results using search term
                log.info "Searching labels with term ${term}....."
                Map<String, Object> searchResults = searchService.search(term, page)
                log.info "Found ${searchResults.totalCount} labels"
                iTotalRecords = searchResults.totalCount
                iTotalDisplayRecords = searchResults.totalCount
                //loop through labels that are found and construct view to show results
                searchResults.labels.each { label ->
                    def resStr = "<a href='${label.id}'>${label.title}</a><p>${label.description}</p>"
                     labels << [
                         labelDetails:resStr
                     ]
                }
                log.info "Showing results of page ${params.draw}"
            }
            catch (LabelServiceException e) {
                log.error("Exception occurred while searching term ${params.term}: ${e}")
                render([draw: params.draw, iTotalRecords:iTotalRecords, iTotalDisplayRecords:iTotalDisplayRecords, aaData:labels] as JSON)
            }
        }
        //Otherwise return empty results
        render([draw: params.draw, iTotalRecords:iTotalRecords, iTotalDisplayRecords:iTotalDisplayRecords, aaData:labels] as JSON)
    }

    def view(){

    }
}
