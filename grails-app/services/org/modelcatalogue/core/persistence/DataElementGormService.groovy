package org.modelcatalogue.core.persistence

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.WarnGormErrors
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.DataType
import org.springframework.context.MessageSource
import grails.gorm.DetachedCriteria
import org.modelcatalogue.core.DataElement

@CompileStatic
@Slf4j
class DataElementGormService implements WarnGormErrors {

    MessageSource messageSource

    @Transactional
    DataElement findById(long id) {
        DataElement.get(id)
    }

    @Transactional(readOnly = true)
    DataElement findByName(String name) {
        findQueryByName(name).get()
    }

    @Transactional
    DataElement saveByNameAndPrimitiveType(String name, DataType dataType) {
        save(new DataElement(name: name, dataType: dataType))
    }

    @Transactional
    DataElement save(DataElement dataElementInstance) {
        if ( !dataElementInstance.save() ) {
            log.warn beanWarnMessage(dataElementInstance, messageSource)
            transactionStatus.setRollbackOnly()
        }
        dataElementInstance
    }

    @Transactional
    DataElement saveWithNameAndDescription(String name, String description) {
        save(new DataElement(name: name, description: description))
    }

    @Transactional
    DataElement saveWithNameAndDescriptionAndDataType(String name, String description, DataType dataType) {
        save(new DataElement(name: name, description: description, dataType: dataType))
    }

    protected DetachedCriteria<DataElement> findQueryByName(String nameParam) {
        DataElement.where { name == nameParam }
    }

    @Transactional(readOnly = true)
    DataElement findByModelCatalogueIdAndDataModel(String modelCatalogueId, DataModel dataModel) {
        findQueryByModelCatalogueIdAndDataModel(modelCatalogueId, dataModel).get()
    }

    protected DetachedCriteria<DataElement> findQueryByModelCatalogueIdAndDataModel(String modelCatalogueIdParam, DataModel dataModelParam) {
        DataElement.where { modelCatalogueId == modelCatalogueIdParam && dataModel == dataModelParam }
    }

    @Transactional(readOnly = true)
    DataElement findDataElementByNameAndDataModel(String name, DataModel dataModel) {
        findQueryByNameAndDataModel(name, dataModel).get()
    }

    protected DetachedCriteria<DataElement> findQueryByNameAndDataModel(String nameParam, DataModel dataModelParam) {
        DataElement.where { name == nameParam && dataModel == dataModelParam }
    }

    @Transactional(readOnly = true)
    List<DataElement> findAllByDataModel(DataModel dataModel) {
        findQueryByDataModel(dataModel).list()
    }

    protected DetachedCriteria<DataElement> findQueryByDataModel(DataModel dataModelParam) {
        DataElement.where { dataModel == dataModelParam }
    }

    @Transactional(readOnly = true)
    DataElement findByModelCatalogueIdAndDataModelAndDataType(String modelCatalogueId,
                                                              DataModel dataModel,
                                                              DataType dataType) {
        findQueryByModelCatalogueIdAndDataModelAndDataType(modelCatalogueId, dataModel, dataType).get()
    }

    private DetachedCriteria<DataElement> findQueryByModelCatalogueIdAndDataModelAndDataType(String modelCatalogueIdParam,
                                                              DataModel dataModelParam,
                                                              DataType dataTypeParam) {
        DataElement.where {
            modelCatalogueId == modelCatalogueIdParam && dataModel == dataModelParam && dataType == dataTypeParam
        }
    }

    @Transactional(readOnly = true)
    DataElement findByNameAndDataModelAndDataType(String name,
                                                  DataModel dataModel,
                                                  DataType dataType) {
        findQueryByNameAndDataModelAndDataType(name, dataModel, dataType).get()
    }

    private DetachedCriteria<DataElement> findQueryByNameAndDataModelAndDataType(String nameParam,
                                                                                DataModel dataModelParam,
                                                                                DataType dataTypeParam) {
        DataElement.where {
            name == nameParam && dataModel == dataModelParam && dataType == dataTypeParam
        }
    }
}
