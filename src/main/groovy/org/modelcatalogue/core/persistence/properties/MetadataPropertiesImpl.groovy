package org.modelcatalogue.core.persistence.properties

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable

@GrailsCompileStatic
class MetadataPropertiesImpl implements MetadataProperties, Validateable {
    String name
    Object value
}
