package org.loinc.gorm

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.grails.WarnGormErrors
import org.grails.utils.Benchmark
import org.loinc.ImportLoincService
import org.loinc.Loinc
import org.loinc.LoincImport
import org.springframework.context.MessageSource

@Slf4j
class ImportLoincGormService implements LoincImport, WarnGormErrors, Benchmark {

    ImportLoincService importLoincService

    LoincGormService loincGormService

    MessageSource messageSource

    def sessionFactory

    @Override
    @Transactional
    void save(InputStream inputStream, Integer batchSize) {
        long duration = benchmark {
            importLoincService.processInputStream(inputStream, batchSize) { List<Loinc> loincList ->
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