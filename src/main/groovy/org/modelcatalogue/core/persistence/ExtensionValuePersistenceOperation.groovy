package org.modelcatalogue.core.persistence

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.ExtensionValue

@CompileStatic
class ExtensionValuePersistenceOperation {
    ExtensionValue extensionValue
    PersistenceOperation operation
}
