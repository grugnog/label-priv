package com.semanticbits.label.service

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(BarcodeService)
class BarcodeServiceSpec extends Specification {

    def barcodeService
    
    void "test scan empty barcode"() {
        when:
        barcodeService.scanBarcode(null)
        then:
        LabelServiceException e = thrown()
        e.message == 'Image file is empty'
    }

    void "test scan barcode"() {
        given:
        byte [] image1 =  new File ('test/integration/resources/barcode1.png').bytes
        byte [] image2 =  new File ('test/integration/resources/barcode2.png').bytes
        byte [] image3 =  new File ('test/integration/resources/barcode3.png').bytes
        when:
        def barCode1 = barcodeService.scanBarcode(image1)
        def barCode2 = barcodeService.scanBarcode(image2)
        def barCode3 = barcodeService.scanBarcode(image3)
        then:
        barCode1 == '123456'
        barCode2 == '123456789104'
        barCode3 == '811204012344'
    }
    
    void "test scan barcode no filename"() {
        given:
        byte [] image =  new File ('test/integration/resources/barcode1.png').bytes
        when:
        def barCode = barcodeService.scanBarcode(image, 'barcode.png')
        then:
        barCode == '123456'
    }
    
    void "test scan barcode on photo 1"() {
        given:
        byte [] image =  new File ('test/integration/resources/barcode-photo1.jpg').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '043000181706'
        
    }
    
     void "test scan barcode on photo 2"() {
        given:
        byte [] image =  new File ('test/integration/resources/barcode-photo2.jpg').bytes
        when:
        def barCode = barcodeService.scanBarcode(image)
        then:
        barCode == '083275099870'
        
    }
   
}
