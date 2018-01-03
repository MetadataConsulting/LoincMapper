package org.loinc

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED
import static javax.servlet.http.HttpServletResponse.SC_OK
import static javax.servlet.http.HttpServletResponse.SC_MOVED_TEMPORARILY
import spock.lang.Unroll

class LoincControllerAllowedMethodsSpec extends Specification implements ControllerUnitTest<LoincController> {

    @Unroll
    def "test TestController.importLoinc does not accept #method requests"(String method) {
        when:
        request.method = method
        controller.importLoinc()

        then:
        response.status == SC_METHOD_NOT_ALLOWED

        where:
        method << ['PATCH', 'DELETE', 'POST', 'PUT']
    }

    def "test TestController.importLoinc accepts GET requests"() {
        when:
        request.method = 'GET'
        controller.importLoinc()

        then:
        response.status == SC_OK
    }

    @Unroll
    def "test TestController.uploadLoinc does not accept #method requests"(String method) {
        when:
        request.method = method
        controller.uploadLoinc()

        then:
        response.status == SC_METHOD_NOT_ALLOWED

        where:
        method << ['PATCH', 'DELETE', 'GET', 'PUT']
    }

    def "test TestController.uploadLoinc accepts POST requests"() {
        when:
        request.method = 'POST'
        controller.uploadLoinc()

        then:
        response.status == SC_MOVED_TEMPORARILY
    }
}
