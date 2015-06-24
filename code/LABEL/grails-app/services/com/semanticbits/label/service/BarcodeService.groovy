package com.semanticbits.label.service

import org.codehaus.groovy.grails.commons.GrailsApplication

import com.google.zxing.client.j2se.CommandLineRunner

/**
 * Scans a barcode image and extract the barcode
 * @author gopal
 *
 */

@SuppressWarnings(['JavaIoPackageAccess'])
class BarcodeService {

    GrailsApplication grailsApplication

    /**
     * Scan barcode from the image
     * @param image image data
     * @return the barcode string contained in the image
     */
    String scanBarcode(byte[] image, String imageName = null) throws LabelServiceException {
        if (!image) {
            throw new LabelServiceException('Image file is empty')
        }
        // This is the only invokable public method on the ZXing API!
        String imageFile = saveImage(image, imageName)
        CommandLineRunner.main([imageFile, '--try_harder', '--dump_results'] as String [])
        String result = readBarcodeOutput(imageFile)
        cleanupTempfiles(imageFile)
        result
    }

    /**
     * Save the image to work dir and return the filename
     * @param image image data
     * @param imageName imageName
     * @return Path of saved image file
     */
    private String saveImage(byte[] image, String imageName) {
        File imageFile = new File(grailsApplication.config.barcode.workdir, imageName ?: "${System.currentTimeMillis()}.jpg")
        FileOutputStream out = new FileOutputStream(imageFile) << image
        out.close()
        imageFile.absolutePath
    }

    /**
     * Read the barcode output file and return the contents
     * @param imageName
     * @return
     */
    private String readBarcodeOutput(String imageName) throws LabelServiceException {
        File outFile = new File(grailsApplication.config.barcode.workdir, getBarcodeOutFilename(imageName))
        if (outFile.exists()) {
            return outFile.text.trim()
        }
        throw new LabelServiceException("Barcode scanning failed, output file ${outFile.absolutePath} not found")
    }

    /**
     * Get barcode output file name for the given image file
     * @param imageFileName imagefile path
     * @return
     */
    private String getBarcodeOutFilename(String imageFileName) {
        String outFileName = new File(imageFileName).name
        int pos = outFileName.lastIndexOf('.')
        outFileName = pos ? (outFileName[0..pos - 1] + '.txt') : outFileName
    }

    /**
     * Cleanup temp files created for scanning
     * @param imageName
     * @return
     */
    private cleanupTempfiles(String imageName) {
        new File(imageName).delete()
        new File(getBarcodeOutFilename(imageName)).delete()
    }
}
