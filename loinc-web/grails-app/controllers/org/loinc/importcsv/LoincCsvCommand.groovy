package org.loinc.importcsv

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

@GrailsCompileStatic
class LoincCsvCommand implements Validateable {

    MultipartFile csvFile
    Integer batchSize = 100

    static constraints = {
        csvFile  validator: { MultipartFile val, LoincCsvCommand obj ->
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