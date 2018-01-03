package org.loinc.gorm

import groovy.util.logging.Slf4j
import org.grails.PersistenceOperation
import org.grails.utils.Benchmark
import org.hibernate.StatelessSession
import org.hibernate.Transaction
import org.loinc.ImportLoincService
import org.loinc.Loinc
import org.loinc.LoincImport

@Slf4j
class ImportLoincStatelessGormService implements LoincImport, Benchmark {

    ImportLoincService importLoincService

    LoincGormService loincGormService

    def sessionFactory

    @Override
    void save(InputStream inputStream, Integer batchSize) {
        def duration = benchmark {
            StatelessSession session = sessionFactory.openStatelessSession()
            Transaction tx = session.beginTransaction()
            importLoincService.processInputStream(inputStream, batchSize) { List<Loinc> loincList ->
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