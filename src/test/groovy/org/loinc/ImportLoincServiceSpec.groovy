package org.loinc

import grails.testing.services.ServiceUnitTest
import spock.lang.Ignore
import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ImportLoincServiceSpec extends Specification implements ServiceUnitTest<ImportLoincService> {

    @Shared
    String headerLine = '"LOINC_NUM","COMPONENT","PROPERTY","TIME_ASPCT","SYSTEM","SCALE_TYP","METHOD_TYP","CLASS","VersionLastChanged","CHNG_TYPE","DefinitionDescription","STATUS","CONSUMER_NAME","CLASSTYPE","FORMULA","SPECIES","EXMPL_ANSWERS","SURVEY_QUEST_TEXT","SURVEY_QUEST_SRC","UNITSREQUIRED","SUBMITTED_UNITS","RELATEDNAMES2","SHORTNAME","ORDER_OBS","CDISC_COMMON_TESTS","HL7_FIELD_SUBFIELD_ID","EXTERNAL_COPYRIGHT_NOTICE","EXAMPLE_UNITS","LONG_COMMON_NAME","UnitsAndRange","DOCUMENT_SECTION","EXAMPLE_UCUM_UNITS","EXAMPLE_SI_UCUM_UNITS","STATUS_REASON","STATUS_TEXT","CHANGE_REASON_PUBLIC","COMMON_TEST_RANK","COMMON_ORDER_RANK","COMMON_SI_TEST_RANK","HL7_ATTACHMENT_STRUCTURE","EXTERNAL_COPYRIGHT_LINK","PanelType","AskAtOrderEntry","AssociatedObservations","VersionFirstReleased","ValidHL7AttachmentRequest"'

    @IgnoreIf( { !System.getProperty('RUN_SLOW') } )
    def "process loinc 2.63 csv, check objects are inflated for each line and constraints validate"() {
        when:
        File f = new File('src/test/resources/loinc_2.63.csv')

        then:
        f.exists()

        when:
        InputStream inputStream = f.newInputStream()
        List<Loinc> result = []
        service.processInputStream(inputStream) {  List<Loinc> loincList ->
            result.addAll(loincList)
        }

        then:
        result
        result.size() == 86528
        for ( Loinc loinc : result ) {
            assert loinc.loincNum
            assert loinc instanceof LoincImpl
            LoincImpl loincImpl = (LoincImpl) loinc
            boolean validate = loincImpl.validate()
            if ( !validate ) {
                println loincImpl.errors
            }
            assert validate
        }
    }

    def "test readLines"() {
        when:
        File f = new File('src/test/resources/loinc_2.63.csv')

        then:
        f.exists()
        when:
        InputStream inputStream = f.newInputStream()
        List<String> lines = service.readLines(inputStream, 0..0)

        then:
        lines
        lines.size() == 1

        when:
        inputStream = f.newInputStream()
        lines = service.readLines(inputStream, 0..2)

        then:
        lines
        lines.size() == 3

        when:
        String firstline = lines[0]

        then:
        firstline.contains(headerLine)

        when: 'read a differerent range'
        inputStream = f.newInputStream()
        lines = service.readLines(inputStream, 2..4)

        then: 'different line range is read'
        lines
        lines.size() == 3

        when:
        firstline = lines[0]

        then:
        !firstline.contains(headerLine)
    }

    def "test lineValues"() {
        given:
        String line = '"10013-1","R\' wave amplitude.lead I","Elpot","Pt","Heart","Qn","EKG","EKG.MEAS","2.48","MIN",,"ACTIVE",,2,,,,,,"Y",,"Cardiac; ECG; EKG.MEASUREMENTS; Electrical potential; Electrocardiogram; Electrocardiograph; Hrt; Painter\'s colic; PB; Plumbism; Point in time; QNT; Quan; Quant; Quantitative; R prime; R\' wave Amp L-I; R wave Amp L-I; Random; Right; Voltage","R\' wave Amp L-I","Observation",,,,"mV","R\' wave amplitude in lead I",,,"mV",,,,,0,0,0,,,,,,"1.0i",'

        when:
        List<String> values = service.lineValues(line)
        List<String> expected = [
                '10013-1',
                'R\' wave amplitude.lead I',
                'Elpot',
                'Pt',
                'Heart',
                'Qn',
                'EKG',
                'EKG.MEAS',
                '2.48',
                'MIN',
                '',
                'ACTIVE',
                '',
                '2',
                '',
                '',
                '',
                '',
                '',
                'Y',
                '',
                'Cardiac; ECG; EKG.MEASUREMENTS; Electrical potential; Electrocardiogram; Electrocardiograph; Hrt; Painter\'s colic; PB; Plumbism; Point in time; QNT; Quan; Quant; Quantitative; R prime; R\' wave Amp L-I; R wave Amp L-I; Random; Right; Voltage',
                'R\' wave Amp L-I',
                'Observation',
                '',
                '',
                '',
                'mV',
                'R\' wave amplitude in lead I',
                '',
                '',
                'mV',
                '',
                '',
                '',
                '',
                '0',
                '0',
                '0',
                '',
                '',
                '',
                '',
                '',
                '1.0i',
        ]

        then:
        values
        values.size() == expected.size()
        for ( int i = 0; i < values.size(); i++ ) {
            String name = values[i]
            String expectedNam = expected[i]
            assert name == expectedNam
        }

    }

    @Unroll('loincWithLine with #loincNum')
    def "test loincWithLine"() {
        given:
        List<String> headerPropertyList = service.headerLineToPropertiesList(headerLine)
        List<String> values = service.lineValues(line)

        when:
        Loinc result = service.loincWithLine(headerPropertyList, values)

        def resultLoincNum = result.loincNum
        def expectedLoincNum = expected.loincNum
        def resultComponent = result.component
        def expectedComponent = expected.component
        def resultProp = result.prop
        def expectedProp = expected.prop
        def resultTimeAspct = result.timeAspct
        def expectedTimeAspct = expected.timeAspct
        def resultSystem = result.system
        def expectedSystem = expected.system
        def resultScaleTyp = result.scaleTyp
        def expectedScaleTyp = expected.scaleTyp
        def resultMethodTyp = result.methodTyp
        def expectedMethodTyp = expected.methodTyp
        def resultClazz = result.clazz
        def expectedClazz = expected.clazz
        def resultVersionLastChanged = result.versionLastChanged
        def expectedVersionLastChanged = expected.versionLastChanged
        def resultChngType = result.chngType
        def expectedChngType = expected.chngType
        def resultDefinitionDescription = result.definitionDescription
        def expectedDefinitionDescription = expected.definitionDescription
        def resultStatus = result.status
        def expectedStatus  = expected.status
        def resultConsumerName = result.consumerName
        def expectedConsumerName = expected.consumerName
        def resultClasstype = result.classtype
        def expectedClasstype = expected.classtype
        def resultFormula = result.formula
        def expectedFormula = expected.formula
        def resultSpecies = result.species
        def expectedSpecies = expected.species
        def resultExmplAnswers = result.exmplAnswers
        def expectedExmplAnswers = expected.exmplAnswers
        def resultSurveyQuestText = result.surveyQuestText
        def expectedSurveyQuestText = expected.surveyQuestText
        def resultSurveyQuestSrc = result.surveyQuestSrc
        def expectedSurveyQuestSrc = expected.surveyQuestSrc
        def resultUnitsrequired = result.unitsrequired
        def expectedUnitsrequired = expected.unitsrequired
        def resultSubmittedUnits = result.submittedUnits
        def expectedSubmittedUnits = expected.submittedUnits
        def resultRelatednames2 = result.relatednames2
        def expectedRelatednames2 = expected.relatednames2
        def resultShortname = result.shortname
        def expectedShortname = expected.shortname
        def resultOrderObs = result.orderObs
        def expectedOrderObs = expected.orderObs
        def resultCdiscCommonTests = result.cdiscCommonTests
        def expectedCdiscCommonTests = expected.cdiscCommonTests
        def resultHl7FieldSubfieldId = result.hl7FieldSubfieldId
        def expectedHl7FieldSubfieldId = expected.hl7FieldSubfieldId
        def resultExternalCopyrightNotice = result.externalCopyrightNotice
        def expectedExternalCopyrightNotice = expected.externalCopyrightNotice
        def resultExampleUnits = result.exampleUnits
        def expectedExampleUnits = expected.exampleUnits
        def resultLongCommonName = result.longCommonName
        def expectedLongCommonName = expected.longCommonName
        def resultUnitsAndRange = result.unitsAndRange
        def expectedUnitsAndRange = expected.unitsAndRange
        def resultDocumentSection = result.documentSection
        def expectedDocumentSection = expected.documentSection
        def resultExampleUcumUnits = result.exampleUcumUnits
        def expectedExampleUcumUnits = expected.exampleUcumUnits
        def resultExampleSiUcumUnits = result.exampleSiUcumUnits
        def expectedExampleSiUcumUnits = expected.exampleSiUcumUnits
        def resultStatusReason = result.statusReason
        def expectedStatusReason = expected.statusReason
        def resultStatusText = result.statusText
        def expectedStatusText = expected.statusText
        def resultChangeReasonPublic = result.changeReasonPublic
        def expectedChangeReasonPublic = expected.changeReasonPublic
        def resultCommonTestRank = result.commonTestRank
        def expectedCommonTestRank = expected.commonTestRank
        def resultCommonOrderRank = result.commonOrderRank
        def expectedCommonOrderRank = expected.commonOrderRank
        def resultCommonSiTestRank = result.commonSiTestRank
        def expectedCommonSiTestRank = expected.commonSiTestRank
        def resultHl7AttachmentStructure = result.hl7AttachmentStructure
        def expectedHl7AttachmentStructure = expected.hl7AttachmentStructure
        def resultExternalCopyrightLink = result.externalCopyrightLink
        def expectedExternalCopyrightLink = expected.externalCopyrightLink
        def resultPanelType = result.panelType
        def expectedPanelType = expected.panelType
        def resultAskAtOrderEntry = result.askAtOrderEntry
        def expectedAskAtOrderEntry = expected.askAtOrderEntry
        def resultAssociatedObservations = result.associatedObservations
        def expectedAssociatedObservations = expected.associatedObservations
        def resultVersionFirstReleased = result.versionFirstReleased
        def expectedVersionFirstReleased = expected.versionFirstReleased
        def resultValidHL7AttachmentRequest = result.validHL7AttachmentRequest
        def expectedValidHL7AttachmentRequest = expected.validHL7AttachmentRequest


        then:
        result instanceof LoincImpl
        resultLoincNum == expectedLoincNum
        resultComponent == expectedComponent
        resultProp == expectedProp
        resultTimeAspct == expectedTimeAspct
        resultSystem == expectedSystem
        resultScaleTyp == expectedScaleTyp
        resultMethodTyp == expectedMethodTyp
        resultClazz == expectedClazz
        resultVersionLastChanged == expectedVersionLastChanged
        resultChngType == expectedChngType
        resultDefinitionDescription == expectedDefinitionDescription
        resultStatus == expectedStatus
        resultConsumerName == expectedConsumerName
        resultClasstype == expectedClasstype
        resultFormula == expectedFormula
        resultSpecies == expectedSpecies
        resultExmplAnswers == expectedExmplAnswers
        resultSurveyQuestText == expectedSurveyQuestText
        resultSurveyQuestSrc == expectedSurveyQuestSrc
        resultUnitsrequired == expectedUnitsrequired
        resultSubmittedUnits == expectedSubmittedUnits
        resultRelatednames2 == expectedRelatednames2
        resultShortname == expectedShortname
        resultOrderObs == expectedOrderObs
        resultCdiscCommonTests == expectedCdiscCommonTests
        resultHl7FieldSubfieldId == expectedHl7FieldSubfieldId
        resultExternalCopyrightNotice == expectedExternalCopyrightNotice
        resultExampleUnits == expectedExampleUnits
        resultLongCommonName == expectedLongCommonName
        resultUnitsAndRange == expectedUnitsAndRange
        resultDocumentSection == expectedDocumentSection
        resultExampleUcumUnits == expectedExampleUcumUnits
        resultExampleSiUcumUnits == expectedExampleSiUcumUnits
        resultStatusReason == expectedStatusReason
        resultStatusText == expectedStatusText
        resultChangeReasonPublic == expectedChangeReasonPublic
        resultCommonTestRank == expectedCommonTestRank
        resultCommonOrderRank == expectedCommonOrderRank
        resultCommonSiTestRank == expectedCommonSiTestRank
        resultHl7AttachmentStructure == expectedHl7AttachmentStructure
        resultExternalCopyrightLink == expectedExternalCopyrightLink
        resultPanelType == expectedPanelType
        resultAskAtOrderEntry == expectedAskAtOrderEntry
        resultAssociatedObservations == expectedAssociatedObservations
        resultVersionFirstReleased == expectedVersionFirstReleased
        resultValidHL7AttachmentRequest == expectedValidHL7AttachmentRequest

        where:
        loincNum  | line | _
        '10013-1' | '"10013-1","R\' wave amplitude.lead I","Elpot","Pt","Heart","Qn","EKG","EKG.MEAS","2.48","MIN",,"ACTIVE",,2,,,,,,"Y",,"Cardiac; ECG; EKG.MEASUREMENTS; Electrical potential; Electrocardiogram; Electrocardiograph; Hrt; Painter\'s colic; PB; Plumbism; Point in time; QNT; Quan; Quant; Quantitative; R prime; R\' wave Amp L-I; R wave Amp L-I; Random; Right; Voltage","R\' wave Amp L-I","Observation",,,,"mV","R\' wave amplitude in lead I",,,"mV",,,,,0,0,0,,,,,,"1.0i",' | _
        '10157-6' | '"10157-6","History of family member diseases","Hx","Pt","^Family member","Nar",,"H&P.HX","2.63","MIN","History of family member diseases is a report of health information, including medical, genetic, environmental, and lifestyle factors, pertaining to the patient and his or her genetic relatives (living or deceased).  The family history information is used to determine possible or relevant health risk factors that may have a potential impact on the patient\'s healthcare risk profile.","ACTIVE",,2,,,,,,,,"Fam Mem; Family member diseases Hx; H/O; H+P; H+P.HX; History; Hx; Narrative; P prime; Point in time; Random; Report","Family member diseases Hx",,,,,,"History of family member diseases Narrative",,,,,,,"Changed Property from ""Find"" to ""Hx"", removed Method of ""Reported"" and corrected System from Family to ^Family member to align with existing family history terms and to represent family member as a Super System per 8/2015 Clinical LOINC Committee decision; Because it is too difficult to maintain and because the distinction between documents and sections is not clear-cut nor necessary in most cases, the DOCUMENT_SECTION field has been deemed to have little value. The field has been set to null in the December 2017 release in preparation for removal in the December 2018 release. These changes were approved by the Clinical LOINC Committee.",0,0,0,,,,,,"1.0i",' | _
        expected << [
                [
                        loincNum:'10013-1',
                        component:'R\' wave amplitude.lead I',
                        prop:'Elpot',
                        timeAspct:'Pt',
                        system:'Heart',
                        scaleTyp:'Qn',
                        methodTyp:'EKG',
                        clazz: 'EKG.MEAS',
                        versionLastChanged:'2.48',
                        chngType:'MIN',
                        definitionDescription:null,
                        status:'ACTIVE',
                        consumerName:null,
                        classtype:2,
                        formula:null,
                        species:null,
                        exmplAnswers:null,
                        surveyQuestText:null,
                        surveyQuestSrc:null,
                        unitsrequired:'Y',
                        submittedUnits:null,
                        relatednames2:'Cardiac; ECG; EKG.MEASUREMENTS; Electrical potential; Electrocardiogram; Electrocardiograph; Hrt; Painter\'s colic; PB; Plumbism; Point in time; QNT; Quan; Quant; Quantitative; R prime; R\' wave Amp L-I; R wave Amp L-I; Random; Right; Voltage',
                        shortname:'R\' wave Amp L-I',
                        orderObs:'Observation',
                        cdiscCommonTests:null,
                        hl7FieldSubfieldId:null,
                        externalCopyrightNotice:null,
                        exampleUnits:'mV',
                        longCommonName:'R\' wave amplitude in lead I',
                        unitsAndRange:null,
                        documentSection:null,
                        exampleUcumUnits:'mV',
                        exampleSiUcumUnits:null,
                        statusReason:null,
                        statusText:null,
                        changeReasonPublic:null,
                        commonTestRank:0,
                        commonOrderRank:0,
                        commonSiTestRank:0,
                        hl7AttachmentStructure:null,
                        externalCopyrightLink:null,
                        panelType:null,
                        askAtOrderEntry:null,
                        associatedObservations:null,
                        versionFirstReleased:'1.0i',
                        validHL7AttachmentRequest:null,
                ],
                [
                        loincNum:'10157-6',
                        component:'History of family member diseases',
                        prop:'Hx',
                        timeAspct:'Pt',
                        system:'^Family member',
                        scaleTyp:'Nar',
                        methodTyp: null,
                        clazz: 'H&P.HX',
                        versionLastChanged: '2.63',
                        chngType: 'MIN',
                        definitionDescription: 'History of family member diseases is a report of health information, including medical, genetic, environmental, and lifestyle factors, pertaining to the patient and his or her genetic relatives (living or deceased).  The family history information is used to determine possible or relevant health risk factors that may have a potential impact on the patient\'s healthcare risk profile.',
                        status: 'ACTIVE',
                        consumerName:null,
                        classtype: 2,
                        formula:null,
                        species:null,
                        exmplAnswers:null,
                        surveyQuestText:null,
                        surveyQuestSrc:null,
                        unitsrequired: null,
                        submittedUnits:null,
                        relatednames2: 'Fam Mem; Family member diseases Hx; H/O; H+P; H+P.HX; History; Hx; Narrative; P prime; Point in time; Random; Report',
                        shortname: 'Family member diseases Hx',
                        orderObs: null,
                        cdiscCommonTests: null,
                        hl7FieldSubfieldId: null,
                        externalCopyrightNotice:null,
                        exampleUnits: null,
                        longCommonName: 'History of family member diseases Narrative',
                        unitsAndRange: null,
                        documentSection: null,
                        exampleUcumUnits: null,
                        exampleSiUcumUnits: null,
                        statusReason: null,
                        statusText: null,
                        changeReasonPublic: 'Changed Property from "Find" to "Hx", removed Method of "Reported" and corrected System from Family to ^Family member to align with existing family history terms and to represent family member as a Super System per 8/2015 Clinical LOINC Committee decision; Because it is too difficult to maintain and because the distinction between documents and sections is not clear-cut nor necessary in most cases, the DOCUMENT_SECTION field has been deemed to have little value. The field has been set to null in the December 2017 release in preparation for removal in the December 2018 release. These changes were approved by the Clinical LOINC Committee.',
                        commonTestRank: 0,
                        commonOrderRank: 0,
                        commonSiTestRank: 0,
                        hl7AttachmentStructure: null,
                        externalCopyrightLink: null,
                        panelType: null,
                        askAtOrderEntry: null,
                        associatedObservations: null,
                        versionFirstReleased: '1.0i',
                        validHL7AttachmentRequest: null,
                ],
        ]
    }

    def "test headerLineToPropertiesList"() {
        when:
        List<String> propertiesList = service.headerLineToPropertiesList(headerLine)

        List<String> expected = ['loincNum',
                                 'component',
                                 'prop',
                                 'timeAspct',
                                 'system',
                                 'scaleTyp',
                                 'methodTyp',
                                 'clazz',
                                 'versionLastChanged',
                                 'chngType',
                                 'definitionDescription',
                                 'status',
                                 'consumerName',
                                 'classtype',
                                 'formula',
                                 'species',
                                 'exmplAnswers',
                                 'surveyQuestText',
                                 'surveyQuestSrc',
                                 'unitsrequired',
                                 'submittedUnits',
                                 'relatednames2',
                                 'shortname',
                                 'orderObs',
                                 'cdiscCommonTests',
                                 'hl7FieldSubfieldId',
                                 'externalCopyrightNotice',
                                 'exampleUnits',
                                 'longCommonName',
                                 'unitsAndRange',
                                 'documentSection',
                                 'exampleUcumUnits',
                                 'exampleSiUcumUnits',
                                 'statusReason',
                                 'statusText',
                                 'changeReasonPublic',
                                 'commonTestRank',
                                 'commonOrderRank',
                                 'commonSiTestRank',
                                 'hl7AttachmentStructure',
                                 'externalCopyrightLink',
                                 'panelType',
                                 'askAtOrderEntry',
                                 'associatedObservations',
                                 'versionFirstReleased',
                                 'validHL7AttachmentRequest']

        then:
        propertiesList
        propertiesList.size() == expected.size()
        for ( int i = 0; i < propertiesList.size(); i++ ) {
            String propertyName = propertiesList[i]
            String expectedPropertyName = expected[i]
            assert propertyName == expectedPropertyName
        }
    }
}
