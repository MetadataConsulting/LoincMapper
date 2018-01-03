package org.modelcatalogue.core.persistence.saveproperties

import groovy.transform.CompileStatic
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.ModelCatalogueProperties
import org.modelcatalogue.core.persistence.DataClassPersistenceOperation
import org.modelcatalogue.core.persistence.DataElementPersistenceOperation
import org.modelcatalogue.core.persistence.DataTypePersistenceOperation
import org.modelcatalogue.core.persistence.MeasurementUnitPersistenceOperation

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
