package org.modelcatalogue.core

import grails.testing.web.UrlMappingsUnitTest
import spock.lang.Specification

class DataModelLoincImportUrlMappingsSpec  extends Specification implements UrlMappingsUnitTest<DataModelLoincImportUrlMappings> {

    void setup() {
        mockController(DataModelLoincImportController)
    }

    void "test forward mappings"() {
        expect:
        verifyForwardUrlMapping("/dataModel/loinc/upload", controller: 'dataModelLoincImport', action: 'uploadLoinc')

        and:

        verifyForwardUrlMapping("/dataModel/loinc/import", controller: 'dataModelLoincImport', action: 'importLoinc')

    }
}
