package org.loinc

import grails.testing.web.UrlMappingsUnitTest
import spock.lang.Specification

class LoincUrlMappingSpec extends Specification implements UrlMappingsUnitTest<LoincUrlMappings> {

    void setup() {
        mockController(LoincController)
    }

    void "test forward mappings"() {
        expect:
        verifyForwardUrlMapping("/loinc/upload", controller: 'loinc', action: 'uploadLoinc')

        and:

        verifyForwardUrlMapping("/loinc/import", controller: 'loinc', action: 'importLoinc')

    }
}
