package org.loinc.importcsv

class LoincImportUrlMappings {

    static mappings = {
        '/loinc/import'(action: 'importLoinc', controller: 'loincImport')
        '/loinc/upload'(action: 'uploadLoinc', controller: 'loincImport')
    }
}
