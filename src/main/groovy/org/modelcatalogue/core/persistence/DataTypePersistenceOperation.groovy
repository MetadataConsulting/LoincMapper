package org.modelcatalogue.core.persistence

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.DataType

@CompileStatic
class DataTypePersistenceOperation {
    DataType dataType
    PersistenceOperation operation
}
