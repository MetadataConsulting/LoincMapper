package org.loinc.gorm

import grails.gorm.transactions.ReadOnly
import groovy.util.logging.Slf4j
import org.grails.PersistenceOperation
import org.loinc.Loinc
import org.loinc.LoincGormEntityUtils
import org.loinc.gorm.LoincGormEntity

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
}