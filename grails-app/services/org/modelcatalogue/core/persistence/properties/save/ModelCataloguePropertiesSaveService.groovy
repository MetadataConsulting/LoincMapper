package org.modelcatalogue.core.persistence.properties.save

import groovy.transform.CompileStatic
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.persistence.operations.DataElementPersistenceOperation
import org.modelcatalogue.core.persistence.operations.DataTypePersistenceOperation
import org.modelcatalogue.core.persistence.operations.MeasurementUnitPersistenceOperation
import org.modelcatalogue.core.persistence.properties.ModelCatalogueProperties

@CompileStatic
class ModelCataloguePropertiesSaveService {

    PrimitiveTypePropertiesSaveService primitiveTypePropertiesSaveService
    MetadataPropertiesSaveService metadataPropertiesSaveService
    MeasurementUnitPropertiesSaveService measurementUnitPropertiesSaveService
    DataElementPropertiesSaveService dataElementPropertiesSaveService
    DataTypePropertiesSaveService dataTypePropertiesSaveService
    DataClassPropertiesSaveService dataClassPropertiesSaveService

    void save(DataModel dataModel, List<ModelCatalogueProperties> modelCataloguePropertiesList) {

        for ( ModelCatalogueProperties properties : modelCataloguePropertiesList ) {
            dataClassPropertiesSaveService.save(properties.dataClass, dataModel)
            MeasurementUnitPersistenceOperation measurementUnitOperation = measurementUnitPropertiesSaveService.save(properties.measurementUnit, dataModel)
            DataTypePersistenceOperation dataTypeOperation = measurementUnitOperation != null ?
                    primitiveTypePropertiesSaveService.save(properties, dataModel, measurementUnitOperation.measurementUnit) :
                    dataTypePropertiesSaveService.save(properties, dataModel)

            DataElementPersistenceOperation dataElementPersistenceOperation = dataElementPropertiesSaveService.save(properties, dataModel, dataTypeOperation?.dataType)
            metadataPropertiesSaveService.save(properties.metadataList, dataElementPersistenceOperation?.dataElement)
        }
    }

}
