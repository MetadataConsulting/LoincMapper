package org.modelcatalogue.core

class DataModelLoincImportUrlMappings {

    static mappings = {
        '/dataModel/loinc/import'(action: 'importLoinc', controller: 'dataModelLoincImport')
        '/dataModel/loinc/upload'(action: 'uploadLoinc', controller: 'dataModelLoincImport')
    }
}
