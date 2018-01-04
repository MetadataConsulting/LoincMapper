package org.loinc.gorm

import groovy.util.logging.Slf4j
import org.grails.utils.Benchmark
import org.hibernate.StatelessSession
import org.hibernate.Transaction
import org.loinc.entities.LoincImportProcessorService
import org.loinc.entities.LoincImportService
import org.loinc.entities.Loinc

@Slf4j
class ImportLoincStatelessGormService implements LoincImportService, Benchmark {

    LoincImportProcessorService loincImportProcessorService

    LoincGormService loincGormService

    def sessionFactory

    @Override
    void save(InputStream inputStream, Integer batchSize) {
        def duration = benchmark {
            StatelessSession session = sessionFactory.openStatelessSession()
            Transaction tx = session.beginTransaction()
            loincImportProcessorService.processInputStream(inputStream, batchSize) { List<Loinc> loincList ->
                save(loincList, session)
            }
            tx.commit()
            session.close()
        }
        log.info "execution batchSize: ${batchSize} took ${duration} ms"
    }

    void save(List<Loinc> loincList, StatelessSession session) {
        List<LoincPersistenceOperation> loincGormEntityList = loincGormService.loincGormEntityList(loincList)
        for ( LoincPersistenceOperation loincPersistenceOperation : loincGormEntityList ) {
            LoincGormEntity loincGormEntity = loincPersistenceOperation.loinc
            if ( loincPersistenceOperation.operation == PersistenceOperation.INSERT ) {
                session.insert(loincGormEntity)
            } else if ( loincPersistenceOperation.operation == PersistenceOperation.UPDATE ) {
                session.update(loincGormEntity)
            }
        }
    }
}