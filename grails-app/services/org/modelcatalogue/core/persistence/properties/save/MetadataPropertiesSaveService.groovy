package org.modelcatalogue.core.persistence.properties.save

import groovy.transform.CompileStatic
import org.grails.PersistenceOperation
import org.modelcatalogue.core.DataElement
import org.modelcatalogue.core.ExtensionValue
import org.modelcatalogue.core.persistence.ExtensionValueGormService
import org.modelcatalogue.core.persistence.operations.ExtensionValuePersistenceOperation
import org.modelcatalogue.core.persistence.properties.MetadataProperties

@CompileStatic
class MetadataPropertiesSaveService {

    ExtensionValueGormService extensionValueGormService

    boolean validateSaveOperations() {
        false
    }

    void save(List<MetadataProperties> metadataList, DataElement dataElement) {
        if ( metadataList ) {
            for ( MetadataProperties metadataProperties : metadataList ) {
                save(metadataProperties, dataElement)
            }
        }
    }

    ExtensionValuePersistenceOperation save(MetadataProperties properties, DataElement dataElement) {
        ExtensionValuePersistenceOperation extensionValueOperation = null
        if ( properties != null ) {
            extensionValueOperation = find(properties, dataElement)
        }
        if ( extensionValueOperation != null ) {
            process(extensionValueOperation)
        }
        extensionValueOperation
    }

    ExtensionValuePersistenceOperation find(MetadataProperties properties, DataElement dataElement) {
        PersistenceOperation operation = PersistenceOperation.UPDATE
        ExtensionValue extensionValue = findByProperties(properties, dataElement)
        if ( extensionValue == null ) {
            operation = PersistenceOperation.INSERT
            extensionValue = new ExtensionValue()
        }
        populate(extensionValue, dataElement, properties)
        new ExtensionValuePersistenceOperation(operation: operation, extensionValue: extensionValue)
    }

    ExtensionValue findByProperties(MetadataProperties properties, DataElement dataElement) {
        if (properties.name) {
            return extensionValueGormService.findByNameAndDataModel(properties.name, dataElement)
        }
        null
    }

    void populate(ExtensionValue extensionValue, DataElement dataElement, MetadataProperties properties) {
        extensionValue.with {
            name = properties.name
        }
        extensionValue.extensionValue = (properties.value as String)
        extensionValue.element = dataElement
    }


    void process(ExtensionValuePersistenceOperation extensionValueOperation) {
        ExtensionValue extensionValue = extensionValueOperation.extensionValue
        extensionValue.save(validate: validateSaveOperations())
    }
}