package org.modelcatalogue.core.persistence.operations

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.DataClass

@CompileStatic
class DataClassPersistenceOperation {
    DataClass dataClass
    PersistenceOperation operation
}
