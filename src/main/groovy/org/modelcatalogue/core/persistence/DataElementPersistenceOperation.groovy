package org.modelcatalogue.core.persistence

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.DataElement

@CompileStatic
class DataElementPersistenceOperation {
    DataElement dataElement
    PersistenceOperation operation
}
