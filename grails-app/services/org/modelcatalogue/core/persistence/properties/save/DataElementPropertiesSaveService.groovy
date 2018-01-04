package org.modelcatalogue.core.persistence.properties.save

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.DataElement
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.DataType
import org.modelcatalogue.core.persistence.DataElementGormService
import org.modelcatalogue.core.persistence.operations.DataElementPersistenceOperation
import org.modelcatalogue.core.persistence.properties.DataElementProperties
import org.modelcatalogue.core.persistence.properties.ModelCatalogueProperties

@CompileStatic
class DataElementPropertiesSaveService {

    DataElementGormService dataElementGormService

    boolean validateSaveOperations() {
        false
    }

    DataElementPersistenceOperation find(DataElementProperties properties, DataModel dataModel, DataType dataType) {
        PersistenceOperation operation = PersistenceOperation.UPDATE
        DataElement dataElement = findByProperites(properties, dataModel, dataType)
        if ( dataElement == null ) {
            operation = PersistenceOperation.INSERT
            dataElement = new DataElement()
        }
        populate(dataElement, dataModel, properties, dataType)

        new DataElementPersistenceOperation(operation: operation, dataElement: dataElement)
    }

    DataElementPersistenceOperation save(ModelCatalogueProperties properties, DataModel dataModel, DataType dataType) {
        DataElementPersistenceOperation dataElementOperation = null
        if ( properties.dataElement != null ) {
            dataElementOperation = find(properties.dataElement, dataModel, dataType)
        }
        if ( dataElementOperation != null ) {
            process(dataElementOperation)
        }
        dataElementOperation
    }


    void populate(DataElement dataElement, DataModel dataModel, DataElementProperties properties, DataType dataType) {
        dataElement.with {
            modelCatalogueId = properties.id
            name = properties.name
            description = properties.description
            // properties.multiplicity
        }
        dataElement.dataModel = dataModel
        dataElement.dataType = dataType
    }

    DataElement findByProperites(DataElementProperties dataElementProperties, DataModel dataModel, DataType dataType) {
        if (dataElementProperties.id) {
            return dataElementGormService.findByModelCatalogueIdAndDataModelAndDataType(dataElementProperties.id, dataModel, dataType)

        } else if (dataElementProperties.name) {
            return dataElementGormService.findByNameAndDataModelAndDataType(dataElementProperties.name, dataModel, dataType)
        }
        null
    }

    void process(DataElementPersistenceOperation dataElementPersistenceOperation) {
        DataElement dataElement = dataElementPersistenceOperation.dataElement
        dataElement.save(validate: validateSaveOperations())
    }

}