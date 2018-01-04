package org.modelcatalogue.core.persistence.dataimport

import grails.gorm.transactions.Transactional
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.utils.Benchmark
import org.loinc.entities.Loinc
import org.loinc.entities.LoincImportProcessorService
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.persistence.DataModelGormService
import org.modelcatalogue.core.persistence.properties.ModelCatalogueProperties
import org.modelcatalogue.core.persistence.properties.adapters.LoincAdapter
import org.modelcatalogue.core.persistence.properties.save.ModelCataloguePropertiesSaveService
import org.modelcatalogue.core.persistence.properties.save.ModelCataloguePropertiesSaveStatelessService

@CompileStatic
@Slf4j
class ModelCatalogueLoincImportService implements Benchmark {

    LoincImportProcessorService loincImportProcessorService

    ModelCataloguePropertiesSaveStatelessService modelCataloguePropertiesSaveStatelessService

    DataModelGormService dataModelGormService

    def sessionFactory

    @Transactional
    void save(String dataModelName, InputStream inputStream, Integer batchSize) {

        DataModel dataModel = dataModelGormService.findByName(dataModelName)
        if ( dataModel == null ) {
            dataModel = dataModelGormService.save(dataModelName)
        }

        long duration = benchmark {
            loincImportProcessorService.processInputStream(inputStream, batchSize) { List<Loinc> loincList ->
                List<ModelCatalogueProperties> modelCataloguePropertiesList = loincList.collect { Loinc loinc ->
                    new LoincAdapter(loinc)
                } as List<ModelCatalogueProperties>
                modelCataloguePropertiesSaveStatelessService.save(dataModel, modelCataloguePropertiesList)
                //cleanUpGorm()
            }
        }
        log.info "ModelCatalogueLoincImportService.save with a batchSize of: ${batchSize} took ${duration} ms"

    }

    @CompileDynamic
    def cleanUpGorm() {
        def session = sessionFactory.currentSession
        session.flush()
        session.clear()
        //propertyInstanceMap.get().clear()
    }
}