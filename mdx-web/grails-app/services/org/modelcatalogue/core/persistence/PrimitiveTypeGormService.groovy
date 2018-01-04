package org.modelcatalogue.core.persistence

import grails.gorm.DetachedCriteria
import grails.gorm.transactions.ReadOnly
import groovy.transform.CompileStatic
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.MeasurementUnit
import org.modelcatalogue.core.PrimitiveType

@CompileStatic
class PrimitiveTypeGormService {

    @ReadOnly
    PrimitiveType findByModelCatalogueIdAndDataModelAndMeasurementUnit(String modelCatalogueId, DataModel dataModel, MeasurementUnit measurementUnit) {
        findQueryByModelCatalogueIdAndDataModelAndMeasurementUnit(modelCatalogueId, dataModel, measurementUnit).get()
    }

    private DetachedCriteria<PrimitiveType> findQueryByModelCatalogueIdAndDataModelAndMeasurementUnit(String modelCatalogueIdParam,
                                                                                              DataModel dataModelParam,
                                                                                              MeasurementUnit measurementUnitParam) {
        PrimitiveType.where {
            modelCatalogueId == modelCatalogueId && dataModel == dataModelParam && measurementUnit == measurementUnitParam
        }
    }

    @ReadOnly
    PrimitiveType findByNameAndDataModelAndMeasurementUnit(String name, DataModel dataModel, MeasurementUnit measurementUnit) {
        findQueryByNameAndDataModelAndMeasurementUnit(name, dataModel, measurementUnit).get()
    }

    private DetachedCriteria<PrimitiveType> findQueryByNameAndDataModelAndMeasurementUnit(String nameParam,
                                                                                                      DataModel dataModelParam,
                                                                                                      MeasurementUnit measurementUnitParam) {
        PrimitiveType.where {
            name == nameParam && dataModel == dataModelParam && measurementUnit == measurementUnitParam
        }
    }
}