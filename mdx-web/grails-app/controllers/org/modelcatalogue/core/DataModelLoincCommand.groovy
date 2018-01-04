package org.modelcatalogue.core

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

@GrailsCompileStatic
class DataModelLoincCommand implements Validateable {

    String name
    MultipartFile csvFile
    Integer batchSize = 100

    static constraints = {
        name nullable: false, blank: false
        csvFile  validator: { MultipartFile val, DataModelLoincCommand obj ->
            if ( val == null ) {
                return false
            }
            if ( val.empty ) {
                return false
            }

            ['csv'].any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }
}