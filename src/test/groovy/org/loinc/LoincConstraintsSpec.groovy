package org.loinc

import grails.testing.gorm.DomainUnitTest
import org.loinc.gorm.LoincGormEntity
import spock.lang.Ignore
import spock.lang.Specification

class LoincConstraintsSpec extends Specification implements DomainUnitTest<LoincGormEntity> {

    void 'test loincNum cannot be null'() {
        when:
        domain.id = null

        then:
        !domain.validate(['id'])
        domain.errors['id'].code == 'nullable'
    }

    void 'test loincNum can have a maximum of 10 characters'() {
        when: 'for a string of 11 characters'
        String str = 'a' * 11
        domain.id = str

        then: 'id validation fails'
        !domain.validate(['id'])
        domain.errors['id'].code == 'maxSize.exceeded'

        when: 'for a string of 10 characters'
        str = 'a' * 10
        domain.id = str

        then: 'id validation passes'
        domain.validate(['id'])
    }

    void 'test component can be null'() {
        when:
        domain.component = null

        then:
        domain.validate(['component'])
    }

    void 'test component can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.component = str

        then: 'component validation fails'
        !domain.validate(['component'])
        domain.errors['component'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.component = str

        then: 'component validation passes'
        domain.validate(['component'])
    }

    void 'test prop can be null'() {
        when:
        domain.prop = null

        then:
        domain.validate(['prop'])
    }

    void 'test prop can have a maximum of 30 characters'() {
        when: 'for a string of 31 characters'
        String str = 'a' * 31
        domain.prop = str

        then: 'prop validation fails'
        !domain.validate(['prop'])
        domain.errors['prop'].code == 'maxSize.exceeded'

        when: 'for a string of 30 characters'
        str = 'a' * 30
        domain.prop = str

        then: 'prop validation passes'
        domain.validate(['prop'])
    }

    void 'test timeAspct can be null'() {
        when:
        domain.timeAspct = null

        then:
        domain.validate(['timeAspct'])
    }

    void 'test timeAspct can have a maximum of 15 characters'() {
        when: 'for a string of 16 characters'
        String str = 'a' * 16
        domain.timeAspct = str

        then: 'timeAspct validation fails'
        !domain.validate(['timeAspct'])
        domain.errors['timeAspct'].code == 'maxSize.exceeded'

        when: 'for a string of 15 characters'
        str = 'a' * 15
        domain.timeAspct = str

        then: 'timeAspct validation passes'
        domain.validate(['timeAspct'])
    }

    void 'test system can be null'() {
        when:
        domain.system = null

        then:
        domain.validate(['system'])
    }

    @Ignore("There are csv values with more than 100 characters")
    void 'test system can have a maximum of 100 characters'() {
        when: 'for a string of 101 characters'
        String str = 'a' * 101
        domain.system = str

        then: 'system validation fails'
        !domain.validate(['system'])
        domain.errors['system'].code == 'maxSize.exceeded'

        when: 'for a string of 100 characters'
        str = 'a' * 100
        domain.system = str

        then: 'system validation passes'
        domain.validate(['system'])
    }

    void 'test scaleTyp can be null'() {
        when:
        domain.scaleTyp = null

        then:
        domain.validate(['scaleTyp'])
    }

    void 'test scaleTyp can have a maximum of 30 characters'() {
        when: 'for a string of 31 characters'
        String str = 'a' * 31
        domain.scaleTyp = str

        then: 'scaleTyp validation fails'
        !domain.validate(['scaleTyp'])
        domain.errors['scaleTyp'].code == 'maxSize.exceeded'

        when: 'for a string of 30 characters'
        str = 'a' * 30
        domain.scaleTyp = str

        then: 'scaleTyp validation passes'
        domain.validate(['scaleTyp'])
    }

    void 'test methodTyp can be null'() {
        when:
        domain.methodTyp = null

        then:
        domain.validate(['methodTyp'])
    }

    void 'test methodTyp can have a maximum of 50 characters'() {
        when: 'for a string of 11 characters'
        String str = 'a' * 51
        domain.methodTyp = str

        then: 'methodTyp validation fails'
        !domain.validate(['methodTyp'])
        domain.errors['methodTyp'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.methodTyp = str

        then: 'methodTyp validation passes'
        domain.validate(['methodTyp'])
    }

    void 'test clazz can be null'() {
        when:
        domain.clazz = null

        then:
        domain.validate(['clazz'])
    }

    void 'test clazz can have a maximum of 20 characters'() {
        when: 'for a string of 21 characters'
        String str = 'a' * 21
        domain.clazz = str

        then: 'clazz validation fails'
        !domain.validate(['clazz'])
        domain.errors['clazz'].code == 'maxSize.exceeded'

        when: 'for a string of 20 characters'
        str = 'a' * 20
        domain.clazz = str

        then: 'clazz validation passes'
        domain.validate(['scaleTyp'])
    }

    void 'test versionLastChanged can be null'() {
        when:
        domain.versionLastChanged = null

        then:
        domain.validate(['versionLastChanged'])
    }

    void 'test versionLastChanged can have a maximum of 10 characters'() {
        when: 'for a string of 11 characters'
        String str = 'a' * 11
        domain.versionLastChanged = str
        then: 'versionLastChanged validationfails'
        !domain.validate(['versionLastChanged'])
        domain.errors['versionLastChanged'].code == 'maxSize.exceeded'

        when: 'for a string of 10 characters'
        str = 'a' * 1
        domain.versionLastChanged = str
        then: 'versionLastChanged validation passes'
        domain.validate(['versionLastChanged'])
    }

    void 'test chngType can be null'() {
        when:
        domain.chngType = null

        then:
        domain.validate(['chngType'])
    }

    void 'test chngType can have a maximum of 3 characters'() {
        when: 'for a string of 4 characters'
        String str = 'a' * 4
        domain.chngType = str
        then: 'chngType validationfails'
        !domain.validate(['chngType'])
        domain.errors['chngType'].code == 'maxSize.exceeded'

        when: 'for a string of 3 characters'
        str = 'a' * 3
        domain.chngType = str
        then: 'chngType validatio passes'
        domain.validate(['chngType'])
    }

    void 'test definitionDescription can be null'() {
        when:
        domain.definitionDescription = null

        then:
        domain.validate(['definitionDescription'])
    }

    void 'test definitionDescription can have more than 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.definitionDescription = str
        then: 'definitionDescription validation passes'
        domain.validate(['definitionDescription'])
    }

    void 'test status can be null'() {
        when:
        domain.status = null

        then:
        domain.validate(['status'])
    }

    void 'test status can have a maximum of 11 characters'() {
        when: 'for a string of 12 characters'
        String str = 'a' * 12
        domain.status = str
        then: 'status validationfails'
        !domain.validate(['status'])
        domain.errors['status'].code == 'maxSize.exceeded'

        when: 'for a string of 11 characters'
        str = 'a' * 11
        domain.status = str
        then: 'status validatio passes'
        domain.validate(['status'])
    }

    void 'test consumerName can be null'() {
        when:
        domain.consumerName = null

        then:
        domain.validate(['consumerName'])
    }

    void 'test consumerName can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.consumerName = str
        then: 'consumerName validationfails'
        !domain.validate(['consumerName'])
        domain.errors['consumerName'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.consumerName = str
        then: 'consumerName validatio passes'
        domain.validate(['consumerName'])
    }

    void 'test classtype can be null'() {
        when:
        domain.classtype = null

        then:
        domain.validate(['classtype'])
    }

    void 'test formula can be null'() {
        when:
        domain.formula = null

        then:
        domain.validate(['formula'])
    }

    void 'test formula can have more than 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.formula = str
        then: 'formula validation passes'
        domain.validate(['formula'])
    }

    void 'test species can be null'() {
        when:
        domain.species = null

        then:
        domain.validate(['species'])
    }

    void 'test species can have a maximum of 20 characters'() {
        when: 'for a string of 21 characters'
        String str = 'a' * 21
        domain.species = str
        then: 'species validationfails'
        !domain.validate(['species'])
        domain.errors['species'].code == 'maxSize.exceeded'

        when: 'for a string of 20 characters'
        str = 'a' * 20
        domain.species = str
        then: 'species validatio passes'
        domain.validate(['species'])
    }

    void 'test exmplAnswers can be null'() {
        when:
        domain.exmplAnswers = null

        then:
        domain.validate(['exmplAnswers'])
    }

    void 'test exmplAnswers can have more than 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.exmplAnswers = str
        then: 'exmplAnswers validation passes'
        domain.validate(['exmplAnswers'])
    }

    void 'test surveyQuestText can be null'() {
        when:
        domain.surveyQuestText = null

        then:
        domain.validate(['surveyQuestText'])
    }

    void 'test surveyQuestText can have more than 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.surveyQuestText = str
        then: 'surveyQuestText validation passes'
        domain.validate(['surveyQuestText'])
    }

    void 'test surveyQuestSrc can be null'() {
        when:
        domain.surveyQuestSrc = null

        then:
        domain.validate(['surveyQuestSrc'])
    }

    void 'test surveyQuestSrc can have a maximum of 50 characters'() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.surveyQuestSrc = str
        then: 'surveyQuestSrc validationfails'
        !domain.validate(['surveyQuestSrc'])
        domain.errors['surveyQuestSrc'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.surveyQuestSrc = str
        then: 'surveyQuestSrc validatio passes'
        domain.validate(['surveyQuestSrc'])
    }

    void 'test unitsrequired can be null'() {
        when:
        domain.unitsrequired = null

        then:
        domain.validate(['unitsrequired'])
    }

    void 'test unitsrequired can have a maximum of 1 characters'() {
        when: 'for a string of 2 characters'
        String str = 'a' * 2
        domain.unitsrequired = str
        then: 'unitsrequired validationfails'
        !domain.validate(['unitsrequired'])
        domain.errors['unitsrequired'].code == 'maxSize.exceeded'

        when: 'for a string of 1 characters'
        str = 'a' * 1
        domain.unitsrequired = str
        then: 'unitsrequired validatio passes'
        domain.validate(['unitsrequired'])
    }

    void 'test submittedUnits can be null'() {
        when:
        domain.submittedUnits = null

        then:
        domain.validate(['submittedUnits'])
    }

    void 'test submittedUnits can have a maximum of 30 characters'() {
        when: 'for a string of 31 characters'
        String str = 'a' * 31
        domain.submittedUnits = str
        then: 'submittedUnits validationfails'
        !domain.validate(['submittedUnits'])
        domain.errors['submittedUnits'].code == 'maxSize.exceeded'

        when: 'for a string of 30 characters'
        str = 'a' * 30
        domain.submittedUnits = str
        then: 'submittedUnits validatio passes'
        domain.validate(['submittedUnits'])
    }

    void 'test relatednames2 can be null'() {
        when:
        domain.relatednames2 = null

        then:
        domain.validate(['relatednames2'])
    }

    void 'test relatednames2 can have more than 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.relatednames2 = str
        then: 'relatednames2 validation passes'
        domain.validate(['relatednames2'])
    }

    void 'test shortname can be null'() {
        when:
        domain.shortname = null

        then:
        domain.validate(['shortname'])
    }

    @Ignore("There are values in CSV with more than 40 characters")
    void 'test shortname can have a maximum of 40 characters'() {
        when: 'for a string of 41 characters'
        String str = 'a' * 41
        domain.shortname = str
        then: 'shortname validationfails'
        !domain.validate(['shortname'])
        domain.errors['shortname'].code == 'maxSize.exceeded'

        when: 'for a string of 40 characters'
        str = 'a' * 40
        domain.shortname = str
        then: 'shortname validatio passes'
        domain.validate(['shortname'])
    }

    void 'test orderObs can be null'() {
        when:
        domain.orderObs = null

        then:
        domain.validate(['orderObs'])
    }

    void 'test orderObs can have a maximum of 15 characters'() {
        when: 'for a string of 16 characters'
        String str = 'a' * 16
        domain.orderObs = str
        then: 'orderObs validationfails'
        !domain.validate(['orderObs'])
        domain.errors['orderObs'].code == 'maxSize.exceeded'

        when: 'for a string of 15 characters'
        str = 'a' * 15
        domain.orderObs = str
        then: 'orderObs validatio passes'
        domain.validate(['orderObs'])
    }


    void 'test cdiscCommonTests can be null'() {
        when:
        domain.cdiscCommonTests = null

        then:
        domain.validate(['cdiscCommonTests'])
    }

    void 'test cdiscCommonTests can have a maximum of 1 characters'() {
        when: 'for a string of 2 characters'
        String str = 'a' * 2
        domain.cdiscCommonTests = str
        then: 'cdiscCommonTests validationfails'
        !domain.validate(['cdiscCommonTests'])
        domain.errors['cdiscCommonTests'].code == 'maxSize.exceeded'

        when: 'for a string of 1 characters'
        str = 'a' * 1
        domain.cdiscCommonTests = str
        then: 'cdiscCommonTests validatio passes'
        domain.validate(['cdiscCommonTests'])
    }

    void 'test hl7FieldSubfieldId can be null'() {
        when:
        domain.hl7FieldSubfieldId = null

        then:
        domain.validate(['hl7FieldSubfieldId'])
    }

    void 'test hl7FieldSubfieldId can have a maximum of 50 characters'() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.hl7FieldSubfieldId = str
        then: 'hl7FieldSubfieldId validationfails'
        !domain.validate(['hl7FieldSubfieldId'])
        domain.errors['hl7FieldSubfieldId'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.hl7FieldSubfieldId = str
        then: 'hl7FieldSubfieldId validatio passes'
        domain.validate(['hl7FieldSubfieldId'])
    }

    void 'test externalCopyrightNotice can be null'() {
        when:
        domain.externalCopyrightNotice = null

        then:
        domain.validate(['externalCopyrightNotice'])
    }

    void 'test externalCopyrightNotice can have more than 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.externalCopyrightNotice = str
        then: 'externalCopyrightNotice validation passes'
        domain.validate(['externalCopyrightNotice'])
    }

    void 'test exampleUnits can be null'() {
        when:
        domain.exampleUnits = null

        then:
        domain.validate(['exampleUnits'])
    }

    void 'test exampleUnits can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.exampleUnits = str
        then: 'exampleUnits validationfails'
        !domain.validate(['exampleUnits'])
        domain.errors['exampleUnits'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.exampleUnits = str
        then: 'exampleUnits validatio passes'
        domain.validate(['exampleUnits'])
    }

    void 'test longCommonName can be null'() {
        when:
        domain.longCommonName = null

        then:
        domain.validate(['longCommonName'])
    }

    void 'test longCommonName can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.longCommonName = str
        then: 'longCommonName validationfails'
        !domain.validate(['longCommonName'])
        domain.errors['longCommonName'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.longCommonName = str
        then: 'longCommonName validatio passes'
        domain.validate(['longCommonName'])
    }

    void 'test unitsAndRange can be null'() {
        when:
        domain.unitsAndRange = null

        then:
        domain.validate(['unitsAndRange'])
    }

    void 'test unitsAndRange can have more than 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.unitsAndRange = str
        then: 'unitsAndRange validation passes'
        domain.validate(['unitsAndRange'])
    }

    void 'test documentSection can be null'() {
        when:
        domain.documentSection = null

        then:
        domain.validate(['documentSection'])
    }

    void 'test documentSection can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.documentSection = str
        then: 'documentSection validationfails'
        !domain.validate(['documentSection'])
        domain.errors['documentSection'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.documentSection = str
        then: 'documentSection validatio passes'
        domain.validate(['documentSection'])
    }

    void 'test exampleUcumUnits can be null'() {
        when:
        domain.exampleUcumUnits = null

        then:
        domain.validate(['exampleUcumUnits'])
    }

    void 'test exampleUcumUnits can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.exampleUcumUnits = str
        then: 'exampleUcumUnits validationfails'
        !domain.validate(['exampleUcumUnits'])
        domain.errors['exampleUcumUnits'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.exampleUcumUnits = str
        then: 'exampleUcumUnits validatio passes'
        domain.validate(['exampleUcumUnits'])
    }

    void 'test exampleSiUcumUnits can be null'() {
        when:
        domain.exampleSiUcumUnits = null

        then:
        domain.validate(['exampleSiUcumUnits'])
    }

    void 'test exampleSiUcumUnits can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.exampleSiUcumUnits = str
        then: 'exampleSiUcumUnits validationfails'
        !domain.validate(['exampleSiUcumUnits'])
        domain.errors['exampleSiUcumUnits'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.exampleSiUcumUnits = str
        then: 'exampleSiUcumUnits validatio passes'
        domain.validate(['exampleSiUcumUnits'])
    }

    void 'test statusReason can be null'() {
        when:
        domain.statusReason = null

        then:
        domain.validate(['statusReason'])
    }

    void 'test statusReason can have a maximum of 9 characters'() {
        when: 'for a string of 10 characters'
        String str = 'a' * 10
        domain.statusReason = str
        then: 'statusReason validationfails'
        !domain.validate(['statusReason'])
        domain.errors['statusReason'].code == 'maxSize.exceeded'

        when: 'for a string of 9 characters'
        str = 'a' * 9
        domain.statusReason = str
        then: 'statusReason validatio passes'
        domain.validate(['statusReason'])
    }

    void 'test statusText can be null'() {
        when:
        domain.statusText = null

        then:
        domain.validate(['statusText'])
    }

    void 'test statusText can have more than 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.statusText = str
        then: 'statusText validation passes'
        domain.validate(['statusText'])
    }

    void 'test changeReasonPublic can be null'() {
        when:
        domain.changeReasonPublic = null

        then:
        domain.validate(['changeReasonPublic'])
    }

    void 'test changeReasonPublic can have more than 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.changeReasonPublic = str
        then: 'changeReasonPublic validation passes'
        domain.validate(['changeReasonPublic'])
    }

    void 'test commonTestRank can be null'() {
        when:
        domain.commonTestRank = null

        then:
        domain.validate(['commonTestRank'])
    }

    void 'test commonOrderRank can be null'() {
        when:
        domain.commonOrderRank = null

        then:
        domain.validate(['commonOrderRank'])
    }

    void 'test commonSiTestRank can be null'() {
        when:
        domain.commonSiTestRank = null

        then:
        domain.validate(['commonSiTestRank'])
    }

    void 'test hl7AttachmentStructure can be null'() {
        when:
        domain.hl7AttachmentStructure = null

        then:
        domain.validate(['hl7AttachmentStructure'])
    }

    void 'test hl7AttachmentStructure can have a maximum of 15 characters'() {
        when: 'for a string of 16 characters'
        String str = 'a' * 16
        domain.hl7AttachmentStructure = str
        then: 'hl7AttachmentStructure validationfails'
        !domain.validate(['hl7AttachmentStructure'])
        domain.errors['hl7AttachmentStructure'].code == 'maxSize.exceeded'

        when: 'for a string of 15 characters'
        str = 'a' * 15
        domain.hl7AttachmentStructure = str
        then: 'hl7AttachmentStructure validation passes'
        domain.validate(['hl7AttachmentStructure'])
    }

    void 'test externalCopyrightLink can be null'() {
        when:
        domain.externalCopyrightLink = null

        then:
        domain.validate(['externalCopyrightLink'])
    }

    void 'test externalCopyrightLink can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.externalCopyrightLink = str
        then: 'externalCopyrightLink validationfails'
        !domain.validate(['externalCopyrightLink'])
        domain.errors['externalCopyrightLink'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.externalCopyrightLink = str
        then: 'externalCopyrightLink validation passes'
        domain.validate(['externalCopyrightLink'])
    }

    void 'test panelType can be null'() {
        when:
        domain.panelType = null

        then:
        domain.validate(['panelType'])
    }

    void 'test panelType can have a maximum of 50 characters'() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.panelType = str
        then: 'panelType validationfails'
        !domain.validate(['panelType'])
        domain.errors['panelType'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.panelType = str
        then: 'panelType validation passes'
        domain.validate(['panelType'])
    }

    void 'test askAtOrderEntry can be null'() {
        when:
        domain.askAtOrderEntry = null

        then:
        domain.validate(['askAtOrderEntry'])
    }

    void 'test askAtOrderEntry can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.askAtOrderEntry = str
        then: 'askAtOrderEntry validationfails'
        !domain.validate(['askAtOrderEntry'])
        domain.errors['askAtOrderEntry'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.askAtOrderEntry = str
        then: 'askAtOrderEntry validation passes'
        domain.validate(['askAtOrderEntry'])
    }

    void 'test associatedObservations can be null'() {
        when:
        domain.associatedObservations = null

        then:
        domain.validate(['associatedObservations'])
    }

    void 'test associatedObservations can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.associatedObservations = str
        then: 'associatedObservations validationfails'
        !domain.validate(['associatedObservations'])
        domain.errors['associatedObservations'].code == 'maxSize.exceeded'

        when: 'for a string of 255 characters'
        str = 'a' * 255
        domain.associatedObservations = str
        then: 'associatedObservations validation passes'
        domain.validate(['associatedObservations'])
    }

    void 'test versionFirstReleased can be null'() {
        when:
        domain.versionFirstReleased = null

        then:
        domain.validate(['versionFirstReleased'])
    }

    void 'test versionFirstReleased can have a maximum of 10 characters'() {
        when: 'for a string of 11 characters'
        String str = 'a' * 11
        domain.versionFirstReleased = str

        then: 'versionFirstReleased validation fails'
        !domain.validate(['versionFirstReleased'])
        domain.errors['versionFirstReleased'].code == 'maxSize.exceeded'

        when: 'for a string of 10 characters'
        str = 'a' * 10
        domain.versionFirstReleased = str

        then: 'versionFirstReleased validation passes'
        domain.validate(['versionFirstReleased'])
    }

    void 'test validHL7AttachmentRequest can be null'() {
        when:
        domain.validHL7AttachmentRequest = null

        then:
        domain.validate(['validHL7AttachmentRequest'])
    }

    void 'test validHL7AttachmentRequest can have a maximum of 50 characters'() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.validHL7AttachmentRequest = str
        then: 'validHL7AttachmentRequest validationfails'
        !domain.validate(['validHL7AttachmentRequest'])
        domain.errors['validHL7AttachmentRequest'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.validHL7AttachmentRequest = str
        then: 'validHL7AttachmentRequest validation passes'
        domain.validate(['validHL7AttachmentRequest'])
    }
}
