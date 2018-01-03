package org.loinc.gorm

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation

@CompileStatic
class LoincPersistenceOperation {
    PersistenceOperation operation
    LoincGormEntity loinc
}
