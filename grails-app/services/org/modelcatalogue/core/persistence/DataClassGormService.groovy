package org.modelcatalogue.core.persistence

import groovy.transform.CompileStatic
import org.grails.WarnGormErrors
import org.modelcatalogue.core.DataClass
import grails.gorm.DetachedCriteria
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.modelcatalogue.core.DataModel
import org.springframework.context.MessageSource

@CompileStatic
@Slf4j
class DataClassGormService implements WarnGormErrors {

    MessageSource messageSource

    @Transactional
    DataClass findById(long id) {
        DataClass.get(id)
    }

    @Transactional
    DataClass saveWithNameAndDescription(String name, String description) {
        save(new DataClass(name: name, description: description))
    }

    @Transactional
    DataClass save(DataClass dataClassInstance) {
        if ( !dataClassInstance.save() ) {
            log.warn '{}', beanWarnMessage(dataClassInstance, messageSource)
            transactionStatus.setRollbackOnly()
        }
        dataClassInstance
    }

    @Transactional(readOnly = true)
    DataClass findByModelCatalogueIdAndDataModel(String modelCatalogueId, DataModel dataModel) {
        findQueryByModelCatalogueIdAndDataModel(modelCatalogueId, dataModel).get()
    }

    protected DetachedCriteria<DataClass> findQueryByModelCatalogueIdAndDataModel(String modelCatalogueIdParam, DataModel dataModelParam) {
        DataClass.where { modelCatalogueId == modelCatalogueIdParam && dataModel == dataModelParam }
    }

    @Transactional(readOnly = true)
    DataClass findByNameAndDataModel(String name, DataModel dataModel) {
        findQueryByNameAndDataModel(name, dataModel).get()
    }

    protected DetachedCriteria<DataClass> findQueryByNameAndDataModel(String nameParam, DataModel dataModelParam) {
        DataClass.where { name == nameParam && dataModel == dataModelParam }
    }

    @Transactional(readOnly = true)
    List<DataClass> findAllByDataModel(DataModel dataModel) {
        findQueryByDataModel(dataModel).list()
    }

    protected DetachedCriteria<DataClass> findQueryByDataModel(DataModel dataModelParam) {
        DataClass.where { dataModel == dataModelParam }
    }
}
