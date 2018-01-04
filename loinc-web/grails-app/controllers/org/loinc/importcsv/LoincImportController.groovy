package org.loinc.importcsv

import groovy.transform.CompileStatic
import org.loinc.entities.LoincImportService

@CompileStatic
class LoincImportController {

    static allowedMethods = [
            importLoinc: 'GET',
            uploadLoinc: 'POST',
    ]

    LoincImportService loincImportService

    def importLoinc() {
        [:]
    }

    def uploadLoinc(LoincCsvCommand cmd) {

        if ( cmd.hasErrors() ) {
            redirect action: 'importLoinc'
            return
        }

        InputStream inputStream = cmd.csvFile.inputStream
        Integer batchSize = cmd.batchSize
        loincImportService.save(inputStream, batchSize)

        flash.message = 'loincImport.import.finished'
        flash.args = ["Loinc"]
        flash.default = "Loinc imported"

        redirect controller: 'loincIndex', action: 'index'
    }
}