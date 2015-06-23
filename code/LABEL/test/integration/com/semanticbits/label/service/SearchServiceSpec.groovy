package com.semanticbits.label.service

import com.google.javascript.jscomp.ClosureCodingConvention.AssertFunctionByTypeName;

import grails.test.mixin.TestFor
import groovy.json.JsonSlurper;
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(SearchService)
class SearchServiceSpec extends Specification {

    def searchService
    def grailsApplication

    void "test simple search"() {
        when:
        def result =  searchService.search('motrin')
        then:
        assert result.totalCount == 1171
        assert result.totalPages == 118
        assert result.currentCount == 10
        assert result.currentPage == 0
        assert result.labels.size() == 10
        assert result.labels[0].id == 'c46c814a-545b-4b7e-a732-23fae5fb3800'
        assert result.labels[0].title == 'MELOXICAM'
        assert result.labels[0].description == 'Meloxicam, an oxicam derivative, is a member of the enolic acid group of nonsteroidal anti-inflammatory drugs (NSAIDs). Each pastel yellow Meloxicam Tablets, USP contains 7.5 mg or 15 mg meloxicam for oral administration. Meloxicam is chemically designated as 4-hydroxy-2-methyl-N-(5-methyl-2-thiazolyl)-2H-1,2-benzothiazine-3-carboxamide-1,1-dioxide. The molecular weight is 351.4. Its empirical formula is C14H13N3O4S2 and it has the following structural formula: Meloxicam is a pastel yellow solid, practically insoluble in water, with higher solubility observed in strong acids and bases. It is very slightly soluble in methanol. Meloxicam has an apparent partition coefficient (log P)app = 0.1 in n-octanol/buffer pH 7.4. Meloxicam has pKa values of 1.1 and 4.2. Meloxicam is available as a tablet for oral administration containing 7.5 mg or 15 mg meloxicam. The inactive ingredients in Meloxicam Tablets, USP include Colloidal Silicon Dioxide, Sodium Starch Glycolate, Lactose, Magnesium Stearate, Microcrystalline Cellulose, Povidone K-30, and Sodium Citrate. MM1'
    
    }

    void "test search with empty search string"() {
        when:
        def result = searchService.search('')
        then :
        LabelServiceException e = thrown()
        e.message == 'Invalid search, empty search string'
    }
    
    void "test search with special character"() {
        when:
        def result = searchService.search("mor&tin")
        then :
        LabelServiceException e = thrown()
        e.message == 'Error invoking the openFDA API'
    }
    
    void "test search with page number"() {
        when:
        def result =searchService.search('fever', 25)
        then :
        assert result.totalCount == 28330
        assert result.totalPages == 2833
        assert result.currentCount == 10
        assert result.currentPage == 25
        assert result.labels.size() == 10
    }
    
    void "test search with out of bound page number"() {
        when:
        def result = searchService.search('fever', 2834)
        then :
        assert result.totalCount == 28330
        assert result.totalPages == 2833
        assert result.currentCount == 10
        assert result.currentPage == 2834
        assert result.labels.size() == 10
    }
    
    void "test search for multiple result without page number"() {
        when:
        def result = searchService.search('fever')
        then :
        assert result.totalCount == 28330
        assert result.totalPages == 2833
        assert result.currentCount == 10
        assert result.currentPage == 0
        assert result.labels.size() == 10
    }
    
 
    
    void "test search for not existing label"() {
        when:
        def result = searchService.search('sadasdasds')
        then :
        assert result.totalCount == 0
        assert result.totalPages == 0
        assert result.currentCount == 0
        assert result.currentPage == 0
        assert result.labels.size() == 0
    }
    
    void "test search by attribute id"() {
        when:
        def result = searchService.search('#id:c46c814a-545b-4b7e-a732-23fae5fb3800')
        then :
        assert result.totalCount == 1
        assert result.totalPages == 1
        assert result.currentCount == 1
        assert result.currentPage == 0
        assert result.labels.size() == 1
        assert result.labels[0].id == 'c46c814a-545b-4b7e-a732-23fae5fb3800'
        assert result.labels[0].title == 'MELOXICAM'
        assert result.labels[0].description == 'Meloxicam, an oxicam derivative, is a member of the enolic acid group of nonsteroidal anti-inflammatory drugs (NSAIDs). Each pastel yellow Meloxicam Tablets, USP contains 7.5 mg or 15 mg meloxicam for oral administration. Meloxicam is chemically designated as 4-hydroxy-2-methyl-N-(5-methyl-2-thiazolyl)-2H-1,2-benzothiazine-3-carboxamide-1,1-dioxide. The molecular weight is 351.4. Its empirical formula is C14H13N3O4S2 and it has the following structural formula: Meloxicam is a pastel yellow solid, practically insoluble in water, with higher solubility observed in strong acids and bases. It is very slightly soluble in methanol. Meloxicam has an apparent partition coefficient (log P)app = 0.1 in n-octanol/buffer pH 7.4. Meloxicam has pKa values of 1.1 and 4.2. Meloxicam is available as a tablet for oral administration containing 7.5 mg or 15 mg meloxicam. The inactive ingredients in Meloxicam Tablets, USP include Colloidal Silicon Dioxide, Sodium Starch Glycolate, Lactose, Magnesium Stearate, Microcrystalline Cellulose, Povidone K-30, and Sodium Citrate. MM1'
    }
 
    void "test search by attribute generic_name"() {
        when:
        def result = searchService.search('#generic_name:MELOXICAM')
        then :
        assert result.totalCount == 81
        assert result.totalPages == 9
        assert result.currentCount == 10
        assert result.currentPage == 0
        assert result.labels.size() == 10
        assert result.labels[0].id == 'c46c814a-545b-4b7e-a732-23fae5fb3800'
        assert result.labels[0].title == 'MELOXICAM'
        assert result.labels[0].description == 'Meloxicam, an oxicam derivative, is a member of the enolic acid group of nonsteroidal anti-inflammatory drugs (NSAIDs). Each pastel yellow Meloxicam Tablets, USP contains 7.5 mg or 15 mg meloxicam for oral administration. Meloxicam is chemically designated as 4-hydroxy-2-methyl-N-(5-methyl-2-thiazolyl)-2H-1,2-benzothiazine-3-carboxamide-1,1-dioxide. The molecular weight is 351.4. Its empirical formula is C14H13N3O4S2 and it has the following structural formula: Meloxicam is a pastel yellow solid, practically insoluble in water, with higher solubility observed in strong acids and bases. It is very slightly soluble in methanol. Meloxicam has an apparent partition coefficient (log P)app = 0.1 in n-octanol/buffer pH 7.4. Meloxicam has pKa values of 1.1 and 4.2. Meloxicam is available as a tablet for oral administration containing 7.5 mg or 15 mg meloxicam. The inactive ingredients in Meloxicam Tablets, USP include Colloidal Silicon Dioxide, Sodium Starch Glycolate, Lactose, Magnesium Stearate, Microcrystalline Cellulose, Povidone K-30, and Sodium Citrate. MM1'
    }

}
