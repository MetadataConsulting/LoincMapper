package org.loinc.entities;

public interface Loinc {
    String getLoincNum();
    String getComponent();
    String getProp();
    String getTimeAspct();
    String getSystem();
    String getScaleTyp();
    String getMethodTyp();
    String getClazz();
    String getVersionLastChanged();
    String getChngType();
    String getDefinitionDescription();
    String getStatus();
    String getConsumerName();
    Integer getClasstype();
    String getFormula();
    String getSpecies();
    String getExmplAnswers();
    String getSurveyQuestText();
    String getSurveyQuestSrc();
    String getUnitsrequired();
    String getSubmittedUnits();
    String getRelatednames2();
    String getShortname();
    String getOrderObs();
    String getCdiscCommonTests();
    String getHl7FieldSubfieldId();
    String getExternalCopyrightNotice();
    String getExampleUnits();
    String getLongCommonName();
    String getUnitsAndRange();
    String getDocumentSection();
    String getExampleUcumUnits();
    String getExampleSiUcumUnits();
    String getStatusReason();
    String getStatusText();
    String getChangeReasonPublic();
    Integer getCommonTestRank();
    Integer getCommonOrderRank();
    Integer getCommonSiTestRank();
    String getHl7AttachmentStructure();
    String getExternalCopyrightLink();
    String getPanelType();
    String getAskAtOrderEntry();
    String getAssociatedObservations();
    String getVersionFirstReleased();
    String getValidHL7AttachmentRequest();
}