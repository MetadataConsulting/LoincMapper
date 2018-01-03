package org.modelcatalogue.core.persistence.saveproperties

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.DataTypeProperties
import org.modelcatalogue.core.MeasurementUnit
import org.modelcatalogue.core.ModelCatalogueProperties
import org.modelcatalogue.core.PrimitiveType
import org.modelcatalogue.core.persistence.PrimitiveTypeGormService
import org.modelcatalogue.core.persistence.PrimitiveTypePersistenceOperation

@CompileStatic
class PrimitiveTypePropertiesSaveService {

    PrimitiveTypeGormService primitiveTypeGormService

    boolean validateSaveOperations() {
        false
    }

    PrimitiveTypePersistenceOperation save(ModelCatalogueProperties properties, DataModel dataModel, MeasurementUnit measurementUnit) {
        PrimitiveTypePersistenceOperation primitiveTypeOperation = null
        if ( properties.dataType != null ) {
            primitiveTypeOperation = find(properties.dataType, dataModel, measurementUnit)
        }
        if ( primitiveTypeOperation != null ) {
            process(primitiveTypeOperation)
        }
        primitiveTypeOperation
    }

    PrimitiveTypePersistenceOperation find(DataTypeProperties properties, DataModel dataModel, MeasurementUnit measurementUnit) {
        PersistenceOperation operation = PersistenceOperation.UPDATE
        PrimitiveType primitiveType = findByProperties(properties, dataModel, measurementUnit)
        if ( primitiveType == null ) {
            operation = PersistenceOperation.INSERT
            primitiveType = new PrimitiveType()
        }
        populate(primitiveType, dataModel, properties, measurementUnit)

        new PrimitiveTypePersistenceOperation(operation: operation, dataType: primitiveType)
    }

    void populate(PrimitiveType primitiveType, DataModel dataModel, DataTypeProperties properties, MeasurementUnit measurementUnit) {
        primitiveType.with {
            modelCatalogueId = properties.id
            name = properties.name
            description = properties.description
            rule = properties.rule
            // properties.enums
        }
        primitiveType.measurementUnit = measurementUnit
        primitiveType.dataModel = dataModel
    }

    PrimitiveType findByProperties(DataTypeProperties dataTypeProperties, DataModel dataModel, MeasurementUnit measurementUnit) {
        if (dataTypeProperties.id) {
            return primitiveTypeGormService.findByModelCatalogueIdAndDataModelAndMeasurementUnit(dataTypeProperties.id, dataModel, measurementUnit)

        } else if (dataTypeProperties.name) {
            return primitiveTypeGormService.findByNameAndDataModelAndMeasurementUnit(dataTypeProperties.name, dataModel, measurementUnit)
        }
        null
    }

    void process(PrimitiveTypePersistenceOperation primitiveTypeOperation) {
        PrimitiveType primitiveType = primitiveTypeOperation.dataType as PrimitiveType
        primitiveType.save(validate: validateSaveOperations())
    }
}