package org.snomed.importtxt

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class SnomedImportServiceSpec extends Specification implements ServiceUnitTest<SnomedImportService> {

    def "test headerLineToPropertiesList"() {
        given:
        String headerLine = 'id\teffectiveTime\tactive\tmoduleId\trefsetId\treferencedComponentId\trangeConstraint\tattributeRule\truleStrengthId\tcontentTypeId'
        when:
        List<String> propertiesList = service.headerLineToPropertiesList(headerLine)

        List<String> expected = ['id', 'effectiveTime', 'active', 'moduleId', 'refsetId', 'referencedComponentId', 'rangeConstraint', 'attributeRule', 'ruleStrengthId', 'contentTypeId']

        then:
        propertiesList
        propertiesList.size() == expected.size()
        for ( int i = 0; i < propertiesList.size(); i++ ) {
            String propertyName = propertiesList[i]
            String expectedPropertyName = expected[i]
            assert propertyName == expectedPropertyName
        }
    }

}
