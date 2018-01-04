package org.modelcatalogue.core.persistence.properties

import groovy.transform.CompileStatic

@CompileStatic
class DataElementPropertiesImpl implements DataElementProperties {
    String id
    String name
    String description
    String multiplicity
}
