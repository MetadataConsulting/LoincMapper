package org.modelcatalogue.core.persistence.properties

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable

@GrailsCompileStatic
class MeasurementUnitPropertiesImpl implements MeasurementUnitProperties, Validateable {
    String id
    String name
    String symbol

    static constraints = {
        id nullable: true, size: 1..255
        symbol nullable: true, size: 1..100
        name nullable: false, blank: false, size: 1..255
    }
}
