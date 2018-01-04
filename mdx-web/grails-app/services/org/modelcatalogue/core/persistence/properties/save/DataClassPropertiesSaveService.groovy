package org.modelcatalogue.core.persistence.properties.save

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.DataClass
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.persistence.DataClassGormService
import org.modelcatalogue.core.persistence.operations.DataClassPersistenceOperation
import org.modelcatalogue.core.persistence.properties.DataClassProperties

@CompileStatic
class DataClassPropertiesSaveService {

    DataClassGormService dataClassGormService

    DataClass findByProperties(DataClassProperties properties, DataModel dataModel) {
        if (properties.name) {
            return dataClassGormService.findByNameAndDataModel(properties.name, dataModel)
        }
        null
    }

    void populate(DataClass dataClass, DataModel dataModel, DataClassProperties properties) {
        dataClass.with {
            name = properties.name
            description = properties.description
        }
        dataClass.dataModel = dataModel
    }

    DataClassPersistenceOperation save(DataClassProperties properties, DataModel dataModel, Closure cls) {
        DataClassPersistenceOperation dataClassOperation = null
        if ( properties != null ) {
            dataClassOperation = find(properties, dataModel)
        }
        if ( dataClassOperation != null && cls != null) {
            cls(dataClassOperation)
        }
        dataClassOperation

    }

    DataClassPersistenceOperation find(DataClassProperties properties, DataModel dataModel) {
        PersistenceOperation operation = PersistenceOperation.UPDATE
        DataClass dataClass = findByProperties(properties, dataModel)
        if ( dataClass == null ) {
            operation = PersistenceOperation.INSERT
            dataClass = new DataClass()
        }
        populate(dataClass, dataModel, properties)

        new DataClassPersistenceOperation(operation: operation, dataClass: dataClass)
    }
}