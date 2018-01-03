package org.loinc

class LoincUrlMappings {

    static mappings = {
        '/loinc/import'(action: 'importLoinc', controller: 'loinc')
        '/loinc/upload'(action: 'uploadLoinc', controller: 'loinc')
    }
}
