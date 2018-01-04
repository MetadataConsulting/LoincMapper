package org.modelcatalogue.core.persistence.properties.adapters

import groovy.transform.CompileStatic
import org.loinc.entities.Loinc
import org.modelcatalogue.core.persistence.properties.DataClassProperties
import org.modelcatalogue.core.persistence.properties.DataClassPropertiesImpl
import org.modelcatalogue.core.persistence.properties.DataElementProperties
import org.modelcatalogue.core.persistence.properties.DataElementPropertiesImpl
import org.modelcatalogue.core.persistence.properties.DataTypeProperties
import org.modelcatalogue.core.persistence.properties.MeasurementUnitProperties
import org.modelcatalogue.core.persistence.properties.MeasurementUnitPropertiesImpl
import org.modelcatalogue.core.persistence.properties.MetadataProperties
import org.modelcatalogue.core.persistence.properties.MetadataPropertiesImpl
import org.modelcatalogue.core.persistence.properties.ModelCatalogueProperties

@CompileStatic
class LoincAdapter implements ModelCatalogueProperties {

    DataTypeProperties dataType
    DataElementProperties dataElement
    DataClassProperties dataClass
    MeasurementUnitProperties measurementUnit
    List<MetadataProperties> metadataList = []

    LoincAdapter(Loinc loinc) {
        if ( loinc.loincNum  || loinc.component  || loinc.longCommonName ) {
            dataElement = new DataElementPropertiesImpl(id: loinc.loincNum, name: loinc.component, description: loinc.longCommonName)
        }
        if ( loinc.clazz ) {
            dataClass = new DataClassPropertiesImpl(name: loinc.clazz)
        }
        if ( loinc.scaleTyp ) {
            dataClass = new DataClassPropertiesImpl(name: loinc.scaleTyp)
        }
        if ( loinc.exampleUcumUnits ) {
            measurementUnit = new MeasurementUnitPropertiesImpl(symbol: loinc.exampleUcumUnits)
        }
        populateMetadataList(loinc)
    }

    private void populateMetadataList(Loinc loinc) {
        if (loinc.prop) {
            this.metadataList << new MetadataPropertiesImpl(name: 'PROPERTY', value: loinc.prop)
        }
        if (loinc.timeAspct) {
            this.metadataList << new MetadataPropertiesImpl(name: 'TIME_ASPCT', value: loinc.timeAspct)
        }
        if (loinc.system) {
            this.metadataList << new MetadataPropertiesImpl(name: 'SYSTEM', value: loinc.system)
        }
        if (loinc.methodTyp) {
            this.metadataList << new MetadataPropertiesImpl(name: 'METHOD_TYP', value: loinc.methodTyp)
        }
        if (loinc.versionLastChanged) {
            this.metadataList << new MetadataPropertiesImpl(name: 'VersionLastChanged', value: loinc.versionLastChanged)
        }
        if (loinc.chngType) {
            this.metadataList << new MetadataPropertiesImpl(name: 'CHNG_TYPE', value: loinc.chngType)
        }
        if (loinc.definitionDescription) {
            this.metadataList << new MetadataPropertiesImpl(name: 'DefinitionDescription', value: loinc.definitionDescription)
        }
        if (loinc.status) {
            this.metadataList << new MetadataPropertiesImpl(name: 'STATUS', value: loinc.status)
        }
        if (loinc.consumerName) {
            this.metadataList << new MetadataPropertiesImpl(name: 'CONSUMER_NAME', value: loinc.consumerName)
        }
        if (loinc.classtype) {
            this.metadataList << new MetadataPropertiesImpl(name: 'CLASSTYPE', value: loinc.classtype)
        }
        if (loinc.formula) {
            this.metadataList << new MetadataPropertiesImpl(name: 'FORMULA', value: loinc.formula)
        }
        if (loinc.species) {
            this.metadataList << new MetadataPropertiesImpl(name: 'SPECIES', value: loinc.species)
        }
        if (loinc.exmplAnswers) {
            this.metadataList << new MetadataPropertiesImpl(name: 'EXMPL_ANSWERS', value: loinc.exmplAnswers)
        }
        if (loinc.surveyQuestText) {
            this.metadataList << new MetadataPropertiesImpl(name: 'SURVEY_QUEST_TEXT', value: loinc.surveyQuestText)
        }
        if (loinc.surveyQuestText) {
            this.metadataList << new MetadataPropertiesImpl(name: 'SURVEY_QUEST_SRC', value: loinc.surveyQuestText)
        }
        if (loinc.unitsrequired) {
            this.metadataList << new MetadataPropertiesImpl(name: 'UNITSREQUIRED', value: loinc.unitsrequired)
        }
        if (loinc.submittedUnits) {
            this.metadataList << new MetadataPropertiesImpl(name: 'SUBMITTED_UNITS', value: loinc.submittedUnits)
        }
        if (loinc.relatednames2) {
            this.metadataList << new MetadataPropertiesImpl(name: 'RELATEDNAMES2', value: loinc.relatednames2)
        }
        if (loinc.shortname) {
            this.metadataList << new MetadataPropertiesImpl(name: 'SHORTNAME', value: loinc.shortname)
        }
        if (loinc.orderObs) {
            this.metadataList << new MetadataPropertiesImpl(name: 'ORDER_OBS', value: loinc.orderObs)
        }
        if (loinc.cdiscCommonTests) {
            this.metadataList << new MetadataPropertiesImpl(name: 'CDISC_COMMON_TESTS', value: loinc.cdiscCommonTests)
        }
        if (loinc.hl7FieldSubfieldId) {
            this.metadataList << new MetadataPropertiesImpl(name: 'HL7_FIELD_SUBFIELD_ID', value: loinc.hl7FieldSubfieldId)
        }
        if (loinc.externalCopyrightNotice) {
            this.metadataList << new MetadataPropertiesImpl(name: 'EXTERNAL_COPYRIGHT_NOTICE', value: loinc.externalCopyrightNotice)
        }
        if (loinc.exampleUnits) {
            this.metadataList << new MetadataPropertiesImpl(name: 'EXAMPLE_UNITS', value: loinc.exampleUnits)
        }
        if (loinc.unitsAndRange) {
            this.metadataList << new MetadataPropertiesImpl(name: 'UnitsAndRange', value: loinc.unitsAndRange)
        }
        if (loinc.documentSection) {
            this.metadataList << new MetadataPropertiesImpl(name: 'DOCUMENT_SECTION', value: loinc.documentSection)
        }
        if (loinc.exampleSiUcumUnits) {
            this.metadataList << new MetadataPropertiesImpl(name: 'EXAMPLE_SI_UCUM_UNITS', value: loinc.exampleSiUcumUnits)
        }
        if (loinc.statusReason) {
            this.metadataList << new MetadataPropertiesImpl(name: 'STATUS_REASON', value: loinc.statusReason)
        }
        if (loinc.statusText) {
            this.metadataList << new MetadataPropertiesImpl(name: 'STATUS_TEXT', value: loinc.statusText)
        }
        if (loinc.changeReasonPublic) {
            this.metadataList << new MetadataPropertiesImpl(name: 'CHANGE_REASON_PUBLIC', value: loinc.changeReasonPublic)
        }
        if (loinc.commonTestRank) {
            this.metadataList << new MetadataPropertiesImpl(name: 'COMMON_TEST_RANK', value: loinc.commonTestRank)
        }
        if (loinc.commonOrderRank) {
            this.metadataList << new MetadataPropertiesImpl(name: 'COMMON_ORDER_RANK', value: loinc.commonOrderRank)
        }
        if (loinc.commonSiTestRank) {
            this.metadataList << new MetadataPropertiesImpl(name: 'COMMON_SI_TEST_RANK', value: loinc.commonSiTestRank)
        }
        if (loinc.hl7AttachmentStructure) {
            this.metadataList << new MetadataPropertiesImpl(name: 'HL7_ATTACHMENT_STRUCTURE', value: loinc.hl7AttachmentStructure)
        }
        if (loinc.externalCopyrightLink) {
            this.metadataList << new MetadataPropertiesImpl(name: 'EXTERNAL_COPYRIGHT_LINK', value: loinc.externalCopyrightLink)
        }
        if (loinc.panelType) {
            this.metadataList << new MetadataPropertiesImpl(name: 'PanelType', value: loinc.panelType)
        }
        if (loinc.askAtOrderEntry) {
            this.metadataList << new MetadataPropertiesImpl(name: 'AskAtOrderEntry', value: loinc.askAtOrderEntry)
        }
        if (loinc.associatedObservations) {
            this.metadataList << new MetadataPropertiesImpl(name: 'AssociatedObservations', value: loinc.associatedObservations)
        }
        if (loinc.versionFirstReleased) {
            this.metadataList << new MetadataPropertiesImpl(name: 'VersionFirstReleased', value: loinc.versionFirstReleased)
        }
        if (loinc.validHL7AttachmentRequest) {
            this.metadataList << new MetadataPropertiesImpl(name: 'ValidHL7AttachmentRequest', value: loinc.validHL7AttachmentRequest)
        }
    }
}
