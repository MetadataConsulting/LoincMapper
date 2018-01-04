package org.loinc

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable
import groovy.transform.EqualsAndHashCode
import org.loinc.entities.Loinc

@EqualsAndHashCode
@GrailsCompileStatic
class LoincImpl implements Loinc, Validateable {
        String loincNum
        String component
        String prop
        String timeAspct
        String system
        String scaleTyp
        String methodTyp
        String clazz
        String versionLastChanged
        String chngType
        String definitionDescription
        String status
        String consumerName
        Integer classtype
        String formula
        String species
        String exmplAnswers
        String surveyQuestText
        String surveyQuestSrc
        String unitsrequired
        String submittedUnits
        String relatednames2
        String shortname
        String orderObs
        String cdiscCommonTests
        String hl7FieldSubfieldId
        String externalCopyrightNotice
        String exampleUnits
        String longCommonName
        String unitsAndRange
        String documentSection
        String exampleUcumUnits
        String exampleSiUcumUnits
        String statusReason
        String statusText
        String changeReasonPublic
        Integer commonTestRank
        Integer commonOrderRank
        Integer commonSiTestRank
        String hl7AttachmentStructure
        String externalCopyrightLink
        String panelType
        String askAtOrderEntry
        String associatedObservations
        String versionFirstReleased
        String validHL7AttachmentRequest

        static constraints = {
                loincNum nullable: false, maxSize: 10
                component nullable: true, maxSize: 255
                prop nullable: true, maxSize: 30
                timeAspct nullable: true, maxSize: 15
                system nullable: true, maxSize: 110 //100
                scaleTyp nullable: true, maxSize: 30
                methodTyp nullable: true, maxSize: 50
                clazz nullable: true, maxSize: 20
                versionLastChanged nullable: true, maxSize: 10
                chngType nullable: true, maxSize: 3
                definitionDescription nullable: true
                status nullable: true, maxSize: 11
                consumerName nullable: true, maxSize: 255
                classtype nullable: true
                formula nullable: true
                species nullable: true, maxSize: 20
                exmplAnswers nullable: true
                surveyQuestText nullable: true
                surveyQuestSrc nullable: true, maxSize: 50
                unitsrequired nullable: true, maxSize: 1
                submittedUnits nullable: true, maxSize: 30
                relatednames2 nullable: true
                shortname nullable: true, maxSize: 60//40
                orderObs nullable: true, maxSize: 15
                cdiscCommonTests nullable: true, maxSize: 1
                hl7FieldSubfieldId nullable: true, maxSize: 50
                externalCopyrightNotice nullable: true
                exampleUnits maxSize: 255, nullable: true
                longCommonName maxSize: 255, nullable: true
                unitsAndRange nullable: true
                documentSection maxSize: 255, nullable: true
                exampleUcumUnits maxSize: 255, nullable: true
                exampleSiUcumUnits maxSize: 255, nullable: true
                statusReason maxSize: 9, nullable: true
                statusText nullable: true
                changeReasonPublic nullable: true
                commonTestRank nullable: true
                commonOrderRank nullable: true
                commonSiTestRank nullable: true
                hl7AttachmentStructure nullable: true, maxSize: 15
                externalCopyrightLink nullable: true, maxSize: 255
                panelType nullable: true, maxSize: 50
                askAtOrderEntry maxSize: 255, nullable: true
                associatedObservations maxSize: 255, nullable: true
                versionFirstReleased nullable: true, maxSize: 10
                validHL7AttachmentRequest nullable: true, maxSize: 50
        }
}
