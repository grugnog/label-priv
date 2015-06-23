package com.semanticbits.label.controller

import com.semanticbits.label.service.SearchService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.json.JSONObject
import spock.lang.Specification

/**
 * User: JanakiRam Gollapudi
 * Date: 6/22/15
 */
@TestFor(SearchController)
@Mock(SearchService)
class SearchControllerSpec extends Specification {

    def setup() {

    }

    def 'test label home page'() {
        when:'I call index method'
        controller.index()

        then:'I should be able to see lable home page'
        view == '/search/index'
    }

    def 'test if home page redirect to search results page'(){
        when:'I try to search label'
        controller.params.term = 'motrin'
        controller.index()

        then:'I should be redirected to search results page'
        view == '/search/results'
    }

    def 'test search by label'() {
        given:'construct a json to return as response for search method'
        JSONObject resultJSON = new JSONObject()
        resultJSON['totalCount'] = 10
        resultJSON['label'] = [
                {
                    id:1
                    title:"sample title"
                    description:"test description"
                }
        ]
        when:'I try to search label with valid value'
        controller.params.draw = 0
        controller.params.term = 'motrin'
        def mockSearchService =  mockFor(SearchService)
        mockSearchService.demand.search { obj1, obj2 -> resultJSON.toString() }
        controller.searchService = mockSearchService
        controller.searchJSON()

        then:'I should get valid results'
        response.json.iTotalRecords == resultJSON.totalCount
        response.json.iTotalDisplayRecords == resultJSON.totalCount
        response.json.label.id == resultJSON.label.id
        response.json.label.title == resultJSON.label.title
        response.json.label.description == resultJSON.label.description
    }


}
