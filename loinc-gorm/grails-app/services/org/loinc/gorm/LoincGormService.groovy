package org.loinc.gorm

import grails.gorm.DetachedCriteria
import grails.gorm.transactions.ReadOnly
import groovy.util.logging.Slf4j
import org.loinc.entities.Loinc

@Slf4j
class LoincGormService {

    @ReadOnly
    List<LoincPersistenceOperation> loincGormEntityList(List<Loinc> loincList) {
        loincList.collect { Loinc loinc ->
            LoincGormEntity loincGormEntity = findByLoincNum(loinc.loincNum)
            PersistenceOperation operation = PersistenceOperation.UPDATE
            if ( loincGormEntity == null ) {
                operation = PersistenceOperation.INSERT
                loincGormEntity = LoincGormEntityUtils.of(loinc)
            } else {
                LoincGormEntityUtils.populate(loincGormEntity, loinc)
            }
            new LoincPersistenceOperation(loinc: loincGormEntity, operation: operation)
        }
    }

    @ReadOnly
    LoincGormEntity findByLoincNum(String loincNum) {
        LoincGormEntity.get(loincNum)
    }

    @ReadOnly
    List<LoincGormEntity> findAll(int max, int offset) {
        LoincGormEntity.where {}.list([max: max, offset: offset])
    }

    int count() {
        LoincGormEntity.count()
    }
}