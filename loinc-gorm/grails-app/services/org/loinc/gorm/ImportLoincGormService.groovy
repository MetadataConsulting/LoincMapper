package org.loinc.gorm

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.grails.utils.Benchmark
import org.loinc.entities.LoincImportProcessorService
import org.loinc.entities.LoincImportService
import org.loinc.entities.Loinc
import org.springframework.context.MessageSource

@Slf4j
class ImportLoincGormService implements LoincImportService, WarnGormErrors, Benchmark {

    LoincImportProcessorService loincImportProcessorService

    LoincGormService loincGormService

    MessageSource messageSource

    def sessionFactory

    @Override
    @Transactional
    void save(InputStream inputStream, Integer batchSize) {
        long duration = benchmark {
            loincImportProcessorService.processInputStream(inputStream, batchSize) { List<Loinc> loincList ->
                save(loincList)
                cleanUpGorm()
            }
        }
        log.info "execution batchSize: ${batchSize} took ${duration} ms"
    }

    @Transactional
    void save(List<Loinc> loincList) {
        List<LoincPersistenceOperation> loincGormEntityList = loincGormService.loincGormEntityList(loincList)
        for ( LoincGormEntity loincGormEntity : loincGormEntityList.collect { it.loinc } ) {
            if ( !loincGormEntity.save() ) {
                log.warn beanWarnMessage(loincGormEntity, messageSource, locale)
            }
        }
    }

    def cleanUpGorm() {
        def session = sessionFactory.currentSession
        session.flush()
        session.clear()
        //propertyInstanceMap.get().clear()
    }
}