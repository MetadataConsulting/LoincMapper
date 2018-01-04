package org.loinc.importcsv

import grails.testing.web.UrlMappingsUnitTest
import org.loinc.importcsv.LoincImportController
import org.loinc.importcsv.LoincImportUrlMappings
import spock.lang.Specification

class LoincImportUrlMappingSpec extends Specification implements UrlMappingsUnitTest<LoincImportUrlMappings> {

    void setup() {
        mockController(LoincImportController)
    }

    void "test forward mappings"() {
        expect:
        verifyForwardUrlMapping("/loinc/upload", controller: 'loincImport', action: 'uploadLoinc')

        and:

        verifyForwardUrlMapping("/loinc/import", controller: 'loincImport', action: 'importLoinc')

    }
}
