package org.modelcatalogue.core.persistence

import grails.gorm.DetachedCriteria
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.WarnGormErrors
import org.modelcatalogue.core.DataModel
import org.springframework.context.MessageSource

@Slf4j
@CompileStatic
class DataModelGormService implements WarnGormErrors {

    MessageSource messageSource

    @Transactional
    DataModel save(String name){
        DataModel dataModelInstance = new DataModel()
        dataModelInstance.name = name
        if ( !dataModelInstance.save() ) {
            log.warn '{}', beanWarnMessage(dataModelInstance, messageSource)
        }
        dataModelInstance
    }

    @ReadOnly
    DataModel findByName(String name) {
        finQueryByName(name).get()
    }

    private DetachedCriteria<DataModel> finQueryByName(String nameParam) {
        DataModel.where { name == nameParam}
    }

}