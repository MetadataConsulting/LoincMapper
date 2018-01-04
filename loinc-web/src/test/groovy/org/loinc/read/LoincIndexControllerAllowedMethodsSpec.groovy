package org.loinc.read

import grails.testing.web.controllers.ControllerUnitTest
import org.loinc.gorm.LoincGormService
import spock.lang.Specification
import spock.lang.Unroll

import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND

class LoincIndexControllerAllowedMethodsSpec extends Specification implements ControllerUnitTest<LoincShowController> {

    @Unroll
    def "test LoincIndexController.index does not accept #method requests"(String method) {
        when:
        request.method = method
        controller.index()

        then:
        response.status == SC_METHOD_NOT_ALLOWED

        where:
        method << ['PATCH', 'DELETE', 'POST', 'PUT']
    }

    def "test LoincIndexController.index accepts GET requests"() {
        given:
        controller.loincGormService = Mock(LoincGormService)

        when:
        request.method = 'GET'
        controller.index()

        then:
        response.status == SC_NOT_FOUND
    }
}
