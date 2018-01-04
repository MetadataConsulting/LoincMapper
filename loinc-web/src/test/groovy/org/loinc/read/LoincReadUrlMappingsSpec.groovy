package org.loinc.read

import grails.testing.web.UrlMappingsUnitTest
import spock.lang.Specification

class LoincReadUrlMappingsSpec extends Specification implements UrlMappingsUnitTest<LoincReadUrlMappings> {

    void setup() {
        mockController(LoincShowController)
        mockController(LoincIndexController)
    }

    void "test forward mappings"() {
        expect:
        verifyForwardUrlMapping("/loinc/1000-9", controller: 'loincShow', action: 'index') {
            loincNum = '1000-9'
        }

        and:
        verifyForwardUrlMapping("/loinc", controller: 'loincIndex', action: 'index')
    }
}
