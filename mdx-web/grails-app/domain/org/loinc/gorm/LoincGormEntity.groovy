package org.loinc.gorm

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class LoincGormEntity {
    String id
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
        id nullable: false, maxSize: 10
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

    static mapping = {
        table 'loincImport'
        version false
        id generator: 'assigned', column: 'loinc_num'
        prop column: 'property'
        timeAspct column: 'time_aspct'
        scaleTyp column: 'scale_typ'
        methodTyp column: 'method_typ'
        clazz column: 'class'
        versionLastChanged column: 'VersionLastChanged'
        chngType column: 'chng_type'
        definitionDescription column: 'DefinitionDescription', type: 'text'
        consumerName column: 'consumer_name'
        formula type: 'text'
        exmplAnswers column: 'exmpl_answers', type: 'text'
        surveyQuestText column: 'survey_quest_text', type: 'text'
        surveyQuestSrc column: 'survey_quest_src'
        submittedUnits column: 'submitted_units'
        relatednames2 type: 'text'
        orderObs column: 'order_obs'
        cdiscCommonTests column: 'cdisc_common_tests'
        hl7FieldSubfieldId column: 'hl7_field_subfield_id'
        externalCopyrightNotice column: 'external_copyright_notice', type: 'text'
        exampleUnits column: 'example_units'
        longCommonName column: 'long_common_name'
        unitsAndRange column: 'UnitsAndRange', type: 'text'
        documentSection column: 'document_section'
        exampleUcumUnits column: 'example_ucum_units'
        exampleSiUcumUnits column: 'example_si_ucum_units'
        statusReason column: 'status_reason'
        statusText column: 'status_text', type: 'text'
        changeReasonPublic column: 'change_reason_public', type: 'text'
        commonTestRank column: 'common_test_rank'
        commonOrderRank column: 'common_order_rank'
        commonSiTestRank column: 'common_si_test_rank'
        hl7AttachmentStructure column: 'hl7_attachment_structure'
        externalCopyrightLink column: 'ExternalCopyrightLink'
        panelType column: 'PanelType'
        askAtOrderEntry column: 'AskAtOrderEntry'
        associatedObservations column: 'AssociatedObservations'
        versionFirstReleased column: 'VersionFirstReleased'
        validHL7AttachmentRequest column: 'ValidHL7AttachmentRequest'
    }
}