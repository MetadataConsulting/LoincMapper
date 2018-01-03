package org.modelcatalogue.core

import groovy.transform.CompileStatic

@CompileStatic
class DataTypePropertiesImpl implements DataTypeProperties {
    String id
    String name
    String description
    String rule
    String enums
}
