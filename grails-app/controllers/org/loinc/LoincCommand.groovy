package org.loinc

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

@GrailsCompileStatic
class LoincCommand implements Validateable {

    MultipartFile csvFile
    Integer batchSize = 100

    static constraints = {
        csvFile  validator: { MultipartFile val, LoincCommand obj ->
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