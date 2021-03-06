package org.modelcatalogue.core.persistence.properties.save

import groovy.transform.CompileStatic
import org.modelcatalogue.core.DataClass
import org.modelcatalogue.core.DataElement
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.DataType
import org.modelcatalogue.core.ExtensionValue
import org.modelcatalogue.core.MeasurementUnit
import org.modelcatalogue.core.PrimitiveType
import org.modelcatalogue.core.persistence.operations.DataClassPersistenceOperation
import org.modelcatalogue.core.persistence.operations.DataElementPersistenceOperation
import org.modelcatalogue.core.persistence.operations.DataTypePersistenceOperation
import org.modelcatalogue.core.persistence.operations.ExtensionValuePersistenceOperation
import org.modelcatalogue.core.persistence.operations.MeasurementUnitPersistenceOperation
import org.modelcatalogue.core.persistence.operations.PrimitiveTypePersistenceOperation
import org.modelcatalogue.core.persistence.properties.ModelCatalogueProperties

@CompileStatic
class ModelCataloguePropertiesSaveService {

    boolean validate = false

    PrimitiveTypePropertiesSaveService primitiveTypePropertiesSaveService
    MetadataPropertiesSaveService metadataPropertiesSaveService
    MeasurementUnitPropertiesSaveService measurementUnitPropertiesSaveService
    DataElementPropertiesSaveService dataElementPropertiesSaveService
    DataTypePropertiesSaveService dataTypePropertiesSaveService
    DataClassPropertiesSaveService dataClassPropertiesSaveService

    void save(DataModel dataModel, List<ModelCatalogueProperties> modelCataloguePropertiesList) {

        for ( ModelCatalogueProperties properties : modelCataloguePropertiesList ) {
            dataClassPropertiesSaveService.save(properties.dataClass, dataModel) { DataClassPersistenceOperation operation ->
                DataClass dataClass = operation.dataClass
                dataClass.save(validate: validate)
            }
            MeasurementUnitPersistenceOperation measurementUnitOperation = measurementUnitPropertiesSaveService
                    .save(properties.measurementUnit, dataModel) { MeasurementUnitPersistenceOperation operation ->
                MeasurementUnit measurementUnit = operation.measurementUnit
                measurementUnit.save(validate: validate)
            }

            DataTypePersistenceOperation dataTypeOperation = null
            if ( measurementUnitOperation != null ) {
                dataTypeOperation = primitiveTypePropertiesSaveService.save(properties, dataModel, measurementUnitOperation.measurementUnit) { PrimitiveTypePersistenceOperation operation ->
                    PrimitiveType primitiveType = operation.dataType as PrimitiveType
                    primitiveType.save(validate: validate)
                }
            } else {
                dataTypeOperation = dataTypePropertiesSaveService.save(properties, dataModel) { DataTypePersistenceOperation operation ->
                    DataType dataType = operation.dataType
                    dataType.save(validate: validate)
                }
            }

            DataElementPersistenceOperation dataElementPersistenceOperation = dataElementPropertiesSaveService.save(properties, dataModel, dataTypeOperation?.dataType) { DataElementPersistenceOperation operation ->
                    DataElement dataElement = operation.dataElement
                    dataElement.save(validate: validate)
            }
            metadataPropertiesSaveService.save(properties.metadataList, dataElementPersistenceOperation?.dataElement) { ExtensionValuePersistenceOperation operation ->
                ExtensionValue extensionValue = operation.extensionValue
                extensionValue.save(validate: validate)
            }
        }
    }
}
