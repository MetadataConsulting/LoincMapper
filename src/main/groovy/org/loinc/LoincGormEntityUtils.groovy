package org.loinc

import groovy.transform.CompileStatic
import org.loinc.gorm.LoincGormEntity

@CompileStatic
class LoincGormEntityUtils {

    static void populate(LoincGormEntity loincGormEntity, Loinc loinc) {
        loincGormEntity.with {
            component = loinc.component
            prop = loinc.prop
            timeAspct = loinc.timeAspct
            system = loinc.system
            scaleTyp = loinc.scaleTyp
            methodTyp = loinc.methodTyp
            clazz = loinc.clazz
            versionLastChanged = loinc.versionLastChanged
            chngType = loinc.chngType
            definitionDescription = loinc.definitionDescription
            status = loinc.status
            consumerName = loinc.consumerName
            classtype = loinc.classtype
            formula = loinc.formula
            species = loinc.species
            exmplAnswers = loinc.exmplAnswers
            surveyQuestText = loinc.surveyQuestText
            surveyQuestSrc = loinc.surveyQuestSrc
            unitsrequired = loinc.unitsrequired
            submittedUnits = loinc.submittedUnits
            relatednames2 = loinc.relatednames2
            shortname = loinc.shortname
            orderObs = loinc.orderObs
            cdiscCommonTests = loinc.cdiscCommonTests
            hl7FieldSubfieldId = loinc.hl7FieldSubfieldId
            externalCopyrightNotice = loinc.externalCopyrightNotice
            exampleUnits = loinc.exampleUnits
            longCommonName = loinc.longCommonName
            unitsAndRange = loinc.unitsAndRange
            documentSection = loinc.documentSection
            exampleUcumUnits = loinc.exampleUcumUnits
            exampleSiUcumUnits = loinc.exampleSiUcumUnits
            statusReason = loinc.statusReason
            statusText = loinc.statusText
            changeReasonPublic = loinc.changeReasonPublic
            commonTestRank = loinc.commonTestRank
            commonOrderRank = loinc.commonOrderRank
            commonSiTestRank = loinc.commonSiTestRank
            hl7AttachmentStructure = loinc.hl7AttachmentStructure
            externalCopyrightLink = loinc.externalCopyrightLink
            panelType = loinc.panelType
            askAtOrderEntry = loinc.askAtOrderEntry
            associatedObservations = loinc.associatedObservations
            versionFirstReleased = loinc.versionFirstReleased
            validHL7AttachmentRequest = loinc.validHL7AttachmentRequest
        }
    }

    static LoincGormEntity of(Loinc loinc) {
        LoincGormEntity loincGormEntity = new LoincGormEntity()
        loincGormEntity.id = loinc.loincNum
        populate(loincGormEntity, loinc)
        loincGormEntity
    }
}
