package org.loinc

import groovy.transform.CompileStatic
import org.loinc.gorm.ImportLoincGormService
import org.loinc.gorm.ImportLoincStatelessGormService

@CompileStatic
class LoincController {

    static allowedMethods = [
            importLoinc: 'GET',
            uploadLoinc: 'POST',
    ]

    ImportLoincGormService importLoincGormService
    ImportLoincStatelessGormService importLoincStatelessGormService

    private LoincImport getLoincImport() {
        importLoincStatelessGormService
    }

    def importLoinc() {
        [:]
    }

    def uploadLoinc(LoincCommand cmd) {

        if ( cmd.hasErrors() ) {
            redirect action: 'importLoinc'
            return
        }

        InputStream inputStream = cmd.csvFile.inputStream
        Integer batchSize = cmd.batchSize
        loincImport.save(inputStream, batchSize)

        flash.message = 'loinc.import.finished'
        flash.args = ["Loinc"]
        flash.default = "Loinc imported"

        [:]
    }
}