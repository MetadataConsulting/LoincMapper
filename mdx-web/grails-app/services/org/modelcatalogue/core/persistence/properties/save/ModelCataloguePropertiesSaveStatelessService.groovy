package org.modelcatalogue.core.persistence.properties.save

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.hibernate.SessionFactory
import org.hibernate.StatelessSession
import org.hibernate.Transaction
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
class ModelCataloguePropertiesSaveStatelessService {

    PrimitiveTypePropertiesSaveService primitiveTypePropertiesSaveService
    MetadataPropertiesSaveService metadataPropertiesSaveService
    MeasurementUnitPropertiesSaveService measurementUnitPropertiesSaveService
    DataElementPropertiesSaveService dataElementPropertiesSaveService
    DataTypePropertiesSaveService dataTypePropertiesSaveService
    DataClassPropertiesSaveService dataClassPropertiesSaveService

    SessionFactory sessionFactory

    void save(DataModel dataModel, List<ModelCatalogueProperties> modelCataloguePropertiesList) {

        StatelessSession session = sessionFactory.openStatelessSession()
        Transaction tx = session.beginTransaction()


        for ( ModelCatalogueProperties properties : modelCataloguePropertiesList ) {
            dataClassPropertiesSaveService.save(properties.dataClass, dataModel) { DataClassPersistenceOperation operation ->
                DataClass dataClass = operation.dataClass
                Date now = new Date()
                dataClass.setLastUpdated(now)
                if ( operation.operation == PersistenceOperation.INSERT ) {
                    dataClass.setDateCreated(now)
                    session.insert(dataClass)
                } else if ( operation.operation == PersistenceOperation.UPDATE ) {
                    session.update(dataClass)
                }
            }

            MeasurementUnitPersistenceOperation measurementUnitOperation = measurementUnitPropertiesSaveService
                    .save(properties.measurementUnit, dataModel) { MeasurementUnitPersistenceOperation operation ->
                MeasurementUnit measurementUnit = operation.measurementUnit
                Date now = new Date()
                measurementUnit.setLastUpdated(now)
                if ( operation.operation == PersistenceOperation.INSERT ) {
                    measurementUnit.setDateCreated(now)
                    session.insert(measurementUnit)
                } else if ( operation.operation == PersistenceOperation.UPDATE ) {
                    session.update(measurementUnit)
                }
            }

            DataTypePersistenceOperation dataTypeOperation = null
            if ( measurementUnitOperation != null ) {
                dataTypeOperation = primitiveTypePropertiesSaveService.save(properties, dataModel, measurementUnitOperation.measurementUnit) { PrimitiveTypePersistenceOperation operation ->
                    PrimitiveType primitiveType = operation.dataType as PrimitiveType
                    Date now = new Date()
                    primitiveType.setLastUpdated(now)
                    if ( operation.operation == PersistenceOperation.INSERT ) {
                        primitiveType.setDateCreated(now)
                        session.insert(primitiveType)
                    } else if ( operation.operation == PersistenceOperation.UPDATE ) {
                        session.update(primitiveType)
                    }
                }
            } else {
                dataTypeOperation = dataTypePropertiesSaveService.save(properties, dataModel) { DataTypePersistenceOperation operation ->
                    DataType dataType = operation.dataType
                    Date now = new Date()
                    dataType.setLastUpdated(now)
                    if ( operation.operation == PersistenceOperation.INSERT ) {
                        dataType.setDateCreated(now)
                        session.insert(dataType)
                    } else if ( operation.operation == PersistenceOperation.UPDATE ) {
                        session.update(dataType)
                    }
                }
            }

            DataElementPersistenceOperation dataElementPersistenceOperation = dataElementPropertiesSaveService.save(properties, dataModel, dataTypeOperation?.dataType) { DataElementPersistenceOperation operation ->
                DataElement dataElement = operation.dataElement
                Date now = new Date()
                dataElement.setLastUpdated(now)
                if ( operation.operation == PersistenceOperation.INSERT ) {
                    dataElement.setDateCreated(now)
                    session.insert(dataElement)
                } else if ( operation.operation == PersistenceOperation.UPDATE ) {
                    session.update(dataElement)
                }
            }
            metadataPropertiesSaveService.save(properties.metadataList, dataElementPersistenceOperation?.dataElement) { ExtensionValuePersistenceOperation operation ->
                ExtensionValue extensionValue = operation.extensionValue
                if ( operation.operation == PersistenceOperation.INSERT ) {
                    session.insert(extensionValue)
                } else if ( operation.operation == PersistenceOperation.UPDATE ) {
                    session.update(extensionValue)
                }
            }
        }

        tx.commit()
        session.close()

    }
}
