package org.modelcatalogue.core

import groovy.transform.CompileStatic
import org.modelcatalogue.core.persistence.dataimport.ModelCatalogueLoincImportService

@CompileStatic
class DataModelLoincImportController {

    ModelCatalogueLoincImportService modelCatalogueLoincImportService

    def importLoinc() {
        [:]
    }

    def uploadLoinc(DataModelLoincCommand cmd) {

        if ( cmd.hasErrors() ) {
            redirect action: 'importLoinc'
            return
        }

        InputStream inputStream = cmd.csvFile.inputStream
        Integer batchSize = cmd.batchSize

        modelCatalogueLoincImportService.save(cmd.name, inputStream, batchSize)

        flash.message = 'dataModel.loincImport.import.finished'
        flash.args = []
        flash.default = "Data Model Loinc imported"

        [:]

    }
}