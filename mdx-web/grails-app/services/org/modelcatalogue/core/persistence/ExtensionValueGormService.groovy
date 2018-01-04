package org.modelcatalogue.core.persistence

import grails.gorm.DetachedCriteria
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.WarnGormErrors
import org.modelcatalogue.core.CatalogueElement
import org.modelcatalogue.core.DataElement
import org.modelcatalogue.core.ExtensionValue
import org.springframework.context.MessageSource

@CompileStatic
@Slf4j
class ExtensionValueGormService implements WarnGormErrors {

    MessageSource messageSource

    @Transactional
    ExtensionValue save(ExtensionValue extensionValueInstance) {
        if ( !extensionValueInstance.save() ) {
            log.warn '{}', beanWarnMessage(extensionValueInstance, messageSource)
            transactionStatus.setRollbackOnly()
        }
        extensionValueInstance
    }

    @Transactional
    ExtensionValue saveWithNameAndExtensionValueAndDataElement(String name, String extensionValue, DataElement dataElement) {
        save(new ExtensionValue(name: name, extensionValue: extensionValue, element: dataElement))
    }

    @Transactional(readOnly = true)
    List<ExtensionValue> findAllByCatalogueElement(CatalogueElement dataElement) {
        findQueryByCatalogueElement(dataElement).list()
    }

    protected DetachedCriteria<ExtensionValue> findQueryByCatalogueElement(CatalogueElement catalogueElementParam) {
        ExtensionValue.where { element == catalogueElementParam}
    }

    @Transactional(readOnly = true)
    ExtensionValue findByNameAndDataModel(String name, DataElement dataElement) {
        findQueryByNameAndDataModel(name, dataElement).get()
    }

    private DetachedCriteria<ExtensionValue> findQueryByNameAndDataModel(String nameParam, DataElement dataElement) {
        ExtensionValue.where {
            name == nameParam && element == dataElement
        }
    }
}