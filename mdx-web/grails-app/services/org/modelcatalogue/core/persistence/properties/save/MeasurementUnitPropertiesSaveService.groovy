package org.modelcatalogue.core.persistence.properties.save

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.MeasurementUnit
import org.modelcatalogue.core.persistence.MeasurementUnitGormService
import org.modelcatalogue.core.persistence.operations.MeasurementUnitPersistenceOperation
import org.modelcatalogue.core.persistence.properties.MeasurementUnitProperties

@CompileStatic
class MeasurementUnitPropertiesSaveService {

    MeasurementUnitGormService measurementUnitGormService

    MeasurementUnitPersistenceOperation find(MeasurementUnitProperties properties,
                                             DataModel dataModel) {
        PersistenceOperation operation = PersistenceOperation.UPDATE
        MeasurementUnit measurementUnit = findByProperties(properties, dataModel)
        if ( measurementUnit == null ) {
            operation = PersistenceOperation.INSERT
            measurementUnit = new MeasurementUnit()
        }
        populate(measurementUnit, dataModel, properties)

        new MeasurementUnitPersistenceOperation(operation: operation, measurementUnit: measurementUnit)
    }

    void populate(MeasurementUnit measurementUnit, DataModel dataModel, MeasurementUnitProperties properties) {
        measurementUnit.with {
            modelCatalogueId = properties.id
            name = properties.name
            symbol = properties.symbol
        }
        measurementUnit.dataModel = dataModel
    }

    MeasurementUnit findByProperties(MeasurementUnitProperties measurementUnitProperties,
                                     DataModel dataModel) {
        if (measurementUnitProperties.id) {
            return measurementUnitGormService.findByModelCatalogueIdAndDataModel(measurementUnitProperties.id, dataModel)

        } else if (measurementUnitProperties.name) { //see if a datatype with this name already exists in this model
            return measurementUnitGormService.findByNameAndDataModel(measurementUnitProperties.name, dataModel)

        } else if (measurementUnitProperties.symbol) {
            return measurementUnitGormService.findBySymbolAndDataModel(measurementUnitProperties.symbol, dataModel)
        }
        null
    }

    MeasurementUnitPersistenceOperation save(MeasurementUnitProperties properties, DataModel dataModel, Closure cls) {
        MeasurementUnitPersistenceOperation measurementUnitOperation = null
        if ( properties != null && properties.validate()) {
            measurementUnitOperation = find(properties, dataModel)
        }
        if ( measurementUnitOperation != null ) {
            cls(measurementUnitOperation)
        }
        measurementUnitOperation
    }
}
