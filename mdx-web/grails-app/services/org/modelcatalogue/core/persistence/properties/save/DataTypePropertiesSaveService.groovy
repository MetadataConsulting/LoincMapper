package org.modelcatalogue.core.persistence.properties.save

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.DataType
import org.modelcatalogue.core.persistence.DataTypeGormService
import org.modelcatalogue.core.persistence.operations.DataTypePersistenceOperation
import org.modelcatalogue.core.persistence.properties.DataTypeProperties
import org.modelcatalogue.core.persistence.properties.ModelCatalogueProperties

@CompileStatic
class DataTypePropertiesSaveService {

    DataTypeGormService dataTypeGormService

    DataType findByProperties(DataTypeProperties dataTypeProperties, DataModel dataModel) {
        if (dataTypeProperties.id) {
            return dataTypeGormService.findByModelCatalogueIdAndDataModel(dataTypeProperties.id, dataModel)

        } else if (dataTypeProperties.name) {
            return dataTypeGormService.findByNameAndDataModel(dataTypeProperties.name, dataModel)
        }
        null
    }

    void populate(DataType dataType, DataModel dataModel, DataTypeProperties properties) {
        dataType.with {
            modelCatalogueId = properties.id
            name = properties.name
            description = properties.description
            rule = properties.rule
            // properties.enums
        }
        dataType.dataModel = dataModel
    }

    DataTypePersistenceOperation save(ModelCatalogueProperties properties, DataModel dataModel, Closure cls) {
        DataTypePersistenceOperation dataTypeOperation = null
        if ( properties.dataType != null ) {
            dataTypeOperation = find(properties.dataType, dataModel)
        }
        if ( dataTypeOperation != null && cls != null ) {
            cls(dataTypeOperation)
        }
        dataTypeOperation

    }

    DataTypePersistenceOperation find(DataTypeProperties properties, DataModel dataModel) {
        PersistenceOperation operation = PersistenceOperation.UPDATE
        DataType dataType = findByProperties(properties, dataModel)
        if ( dataType == null ) {
            operation = PersistenceOperation.INSERT
            dataType = new DataType()
        }
        populate(dataType, dataModel, properties)

        new DataTypePersistenceOperation(operation: operation, dataType: dataType)
    }
}