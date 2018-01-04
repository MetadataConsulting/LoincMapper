package org.modelcatalogue.core.persistence

import grails.gorm.DetachedCriteria
import grails.gorm.transactions.Transactional
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.MeasurementUnit

class MeasurementUnitGormService {

    @Transactional(readOnly = true)
    MeasurementUnit findByModelCatalogueIdAndDataModel(String modelCatalogueId, DataModel dataModel) {
        findQueryByModelCatalogueIdAndDataModel(modelCatalogueId, dataModel).get()
    }

    protected DetachedCriteria<MeasurementUnit> findQueryByModelCatalogueIdAndDataModel(String modelCatalogueIdParam, DataModel dataModelParam) {
        MeasurementUnit.where { modelCatalogueId == modelCatalogueIdParam && dataModel == dataModelParam }
    }

    @Transactional(readOnly = true)
    MeasurementUnit findByNameAndDataModel(String name, DataModel dataModel) {
        findQueryByNameAndDataModel(name, dataModel).get()
    }

    protected DetachedCriteria<MeasurementUnit> findQueryByNameAndDataModel(String nameParam, DataModel dataModelParam) {
        MeasurementUnit.where { name == nameParam && dataModel == dataModelParam }
    }

    @Transactional(readOnly = true)
    MeasurementUnit findBySymbolAndDataModel(String muSymbol, DataModel dataModel) {
        findQueryBySymbolAndDataModel(muSymbol, dataModel).get()
    }

    protected DetachedCriteria<MeasurementUnit> findQueryBySymbolAndDataModel(String muSymbol, DataModel dataModelParam) {
        MeasurementUnit.where { symbol == muSymbol && dataModel == dataModelParam }
    }
}
