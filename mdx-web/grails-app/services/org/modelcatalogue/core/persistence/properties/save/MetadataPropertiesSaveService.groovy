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

    void save(List<MetadataProperties> metadataList, DataElement dataElement, Closure cls) {
        if ( metadataList ) {
            for ( MetadataProperties metadataProperties : metadataList ) {
                save(metadataProperties, dataElement, cls)
            }
        }
    }

    ExtensionValuePersistenceOperation save(MetadataProperties properties, DataElement dataElement, Closure cls) {
        ExtensionValuePersistenceOperation extensionValueOperation = null
        if ( properties != null ) {
            extensionValueOperation = find(properties, dataElement)
        }
        if ( extensionValueOperation != null && cls != null ) {
            cls(extensionValueOperation)
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
}