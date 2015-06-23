package com.semanticbits.label.controller

import com.semanticbits.label.service.LabelServiceException
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * Created with IntelliJ IDEA.
 * User: Janakiram_G
 * Date: 6/22/15
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
class SearchController {
    static defaultAction = "index"
    def searchService

    /**
     * To display home page
     * @return html page
     */
    def index() {
       if(params.term){
           String term = params.term
           render(view:'results', model:[term:term])
       }
       else {
            render(view:'index')
       }
    }

    /**
     * Search based on given label
     * @return results as JSON
     */
    def searchJSON() {
        String term = params.term
        def dataList = []
        int iTotalRecords = 0
        int iTotalDisplayRecords = 0
        if(term) {
            try {
                int page = params.draw?params.int('draw'):0
                def response = searchService.search(term, page)
                JSONElement responseJSON = JSON.parse(response)
                iTotalRecords = responseJSON.totalCount
                iTotalDisplayRecords = responseJSON.totalCount
                responseJSON.labels.each { label ->
                    def resStr = "<a href='${label.id}'>${label.title}</a><p>${label.description}</p>"
                     dataList << [
                         column1:resStr
                     ]
                }
            }
            catch (LabelServiceException e) {
                log.error("Exception occurred while searching term ${params.term}: ${e}")
                render([draw: params.draw, iTotalRecords:iTotalRecords, iTotalDisplayRecords:iTotalDisplayRecords, aaData:dataList] as JSON)
            }
        }
        render([draw: params.draw, iTotalRecords:iTotalRecords, iTotalDisplayRecords:iTotalDisplayRecords, aaData:dataList] as JSON)
    }

    def view(){

    }
}
