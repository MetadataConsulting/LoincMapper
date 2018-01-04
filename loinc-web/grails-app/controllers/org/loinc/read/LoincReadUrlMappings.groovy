package org.loinc.read

class LoincReadUrlMappings {

    static mappings = {
        "/loinc/$loincNum"(action: 'index', controller: 'loincShow')
        "/loinc"(action: 'index', controller: 'loincIndex')
    }
}
