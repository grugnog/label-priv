package com.semanticbits.label.service

/**
 * openFDA API involcation service
 * @author gopal
 */
class OpenFDAService {
    def grailsApplication

    /**
     * Invoke openAPI service and return the response
     * @param requestParams request parameters as expected by the API
     * @return return the server response from the API invocation
     * @throws LabelServiceException if there are any errors during the invocation
     */
    def invoke(String requestParams) throws LabelServiceException {
        try {
            if (requestParams) {
                return new URL(new StringBuilder(grailsApplication.config.openFDA.API.url).append('?')
                        .append(requestParams).toString()).text
            }
            throw new LabelServiceException('Requst parameters must be specified for invoking the openFDA API')
        } catch (all) {
            throw new LabelServiceException('Error invoking the OpenFDA REST servive', all)
        }
    }
}
