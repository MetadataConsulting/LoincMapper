package org.modelcatalogue.core.persistence

import grails.gorm.DetachedCriteria
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.WarnGormErrors
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.DataType
import org.springframework.context.MessageSource

@CompileStatic
@Slf4j
class DataTypeGormService implements WarnGormErrors {

    MessageSource messageSource

    @Transactional
    DataType findById(long id) {
        DataType.get(id)
    }

    @Transactional(readOnly = true)
    DataType findByName(String name) {
        findQueryByName(name).get()
    }

    protected DetachedCriteria<DataType> findQueryByName(String nameParam) {
        DataType.where { name == nameParam }
    }

    @Transactional
    DataType save(DataType dataTypeInstance) {
        if ( !dataTypeInstance.save() ) {
            log.warn '{}', beanWarnMessage(dataTypeInstance, messageSource)
            transactionStatus.setRollbackOnly()
        }
        dataTypeInstance
    }

    @Transactional(readOnly = true)
    DataType findByModelCatalogueIdAndDataModel(String modelCatalogueId, DataModel dataModel) {
        findQueryByModelCatalogueIdAndDataModel(modelCatalogueId, dataModel).get()
    }

    protected DetachedCriteria<DataType> findQueryByModelCatalogueIdAndDataModel(String modelCatalogueIdParam, DataModel dataModelParam) {
        DataType.where { modelCatalogueId == modelCatalogueIdParam && dataModel == dataModelParam }
    }

    @Transactional(readOnly = true)
    DataType findByNameAndDataModel(String name, DataModel dataModel) {
        findQueryByNameAndDataModel(name, dataModel).get()
    }

    protected DetachedCriteria<DataType> findQueryByNameAndDataModel(String nameParam, DataModel dataModelParam) {
        DataType.where { name == nameParam && dataModel == dataModelParam }
    }
}
