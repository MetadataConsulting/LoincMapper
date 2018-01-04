<html>
<head>
    <title>LOINC ${loincInstance.id}</title>
    <meta name="layout" content="main" />
    <style type="text/css">
    ol li { list-style-type: none; }
    </style>
</head>
<body>
<ol>
    <g:if test="${loincInstance.id}">
        <li>
            <g:message code="loinc.id.label" default="LOINC_NUM"/>
            <span class="loincNum">${loincInstance.id}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.component}">
        <li>
            <g:message code="loinc.component.label" default="COMPONENT"/>
            <span class="component">${loincInstance.component}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.prop}">
        <li>
            <g:message code="loinc.prop.label" default="PROPERTY"/>
            <span class="property">${loincInstance.prop}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.timeAspct}">
        <li>
            <g:message code="loinc.timeAspct.label" default="TIME_ASPCT"/>
            <span class="timeAspct">${loincInstance.timeAspct}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.system}">
        <li>
            <g:message code="loinc.system.label" default="SYSTEM"/>
            <span class="system">${loincInstance.system}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.scaleTyp}">
        <li>
            <g:message code="loinc.scaleTyp.label" default="SCALE_TYP"/>
            <span class="scaleTyp">${loincInstance.scaleTyp}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.methodTyp}">
        <li>
            <g:message code="loinc.methodTyp.label" default="METHOD_TYP"/>
            <span class="methodTyp">${loincInstance.methodTyp}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.clazz}">
        <li>
            <g:message code="loinc.clazz.label" default="CLASS"/>
            <span class="clazz">${loincInstance.clazz}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.versionLastChanged}">
        <li>
            <g:message code="loinc.versionLastChanged.label" default="VersionLastChanged"/>
            <span class="versionLastChanged">${loincInstance.versionLastChanged}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.chngType}">
        <li>
            <g:message code="loinc.chngType.label" default="CHNG_TYPE"/>
            <span class="chngType">${loincInstance.chngType}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.definitionDescription}">
        <li>
            <g:message code="loinc.definitionDescription.label" default="DefinitionDescription"/>
            <span class="definitionDescription">${loincInstance.definitionDescription}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.status}">
        <li>
            <g:message code="loinc.status.label" default="STATUS"/>
            <span class="status">${loincInstance.status}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.consumerName}">
        <li>
            <g:message code="loinc.consumerName.label" default="CONSUMER_NAME"/>
            <span class="consumerName">${loincInstance.consumerName}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.classtype}">
        <li>
            <g:message code="loinc.classtype.label" default="CLASSTYPE"/>
            <span class="classtype">${loincInstance.classtype}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.formula}">
        <li>
            <g:message code="loinc.formula.label" default="FORMULA"/>
            <span class="formula">${loincInstance.formula}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.species}">
        <li>
            <g:message code="loinc.species.label" default="SPECIES"/>
            <span class="species">${loincInstance.species}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.exmplAnswers}">
        <li>
            <g:message code="loinc.exmplAnswers.label" default="EXMPL_ANSWERS"/>
            <span class="exmplAnswers">${loincInstance.exmplAnswers}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.surveyQuestText}">
        <li>
            <g:message code="loinc.surveyQuestText.label" default="SURVEY_QUEST_TEXT"/>
            <span class="surveyQuestText">${loincInstance.surveyQuestText}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.surveyQuestSrc}">
        <li>
            <g:message code="loinc.surveyQuestSrc.label" default="SURVEY_QUEST_SRC"/>
            <span class="surveyQuestSrc">${loincInstance.surveyQuestSrc}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.unitsrequired}">
        <li>
            <g:message code="loinc.unitsrequired.label" default="UNITSREQUIRED"/>
            <span class="unitsrequired">${loincInstance.unitsrequired}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.submittedUnits}">
        <li>
            <g:message code="loinc.submittedUnits.label" default="SUBMITTED_UNITS"/>
            <span class="submittedUnits">${loincInstance.submittedUnits}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.relatednames2}">
        <li>
            <g:message code="loinc.relatednames2.label" default="RELATEDNAMES2"/>
            <span class="relatednames2">${loincInstance.relatednames2}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.shortname}">
        <li>
            <g:message code="loinc.shortname.label" default="SHORTNAME"/>
            <span class="shortname">${loincInstance.shortname}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.orderObs}">
        <li>
            <g:message code="loinc.orderObs.label" default="ORDER_OBS"/>
            <span class="orderObs">${loincInstance.orderObs}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.cdiscCommonTests}">
        <li>
            <g:message code="loinc.cdiscCommonTests.label" default="CDISC_COMMON_TESTS"/>
            <span class="cdiscCommonTests">${loincInstance.cdiscCommonTests}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.hl7FieldSubfieldId}">
        <li>
            <g:message code="loinc.hl7FieldSubfieldId.label" default="HL7_FIELD_SUBFIELD_ID"/>
            <span class="hl7FieldSubfieldId">${loincInstance.hl7FieldSubfieldId}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.externalCopyrightNotice}">
        <li>
            <g:message code="loinc.externalCopyrightNotice.label" default="EXTERNAL_COPYRIGHT_NOTICE"/>
            <span class="externalCopyrightNotice">${loincInstance.externalCopyrightNotice}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.exampleUnits}">
        <li>
            <g:message code="loinc.exampleUnits.label" default="EXAMPLE_UNITS"/>
            <span class="exampleUnits">${loincInstance.exampleUnits}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.longCommonName}">
        <li>
            <g:message code="loinc.longCommonName.label" default="LONG_COMMON_NAME"/>
            <span class="longCommonName">${loincInstance.longCommonName}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.unitsAndRange}">
        <li>
            <g:message code="loinc.unitsAndRange.label" default="UnitsAndRange"/>
            <span class="unitsAndRange">${loincInstance.unitsAndRange}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.documentSection}">
        <li>
            <g:message code="loinc.documentSection.label" default="DOCUMENT_SECTION"/>
            <span class="documentSection">${loincInstance.documentSection}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.exampleUcumUnits}">
        <li>
            <g:message code="loinc.exampleUcumUnits.label" default="EXAMPLE_UCUM_UNITS"/>
            <span class="exampleUcumUnits">${loincInstance.exampleUcumUnits}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.exampleSiUcumUnits}">
        <li>
            <g:message code="loinc.exampleSiUcumUnits.label" default="EXAMPLE_SI_UCUM_UNITS"/>
            <span class="exampleSiUcumUnits">${loincInstance.exampleSiUcumUnits}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.statusReason}">
        <li>
            <g:message code="loinc.statusReason.label" default="STATUS_REASON"/>
            <span class="statusReason">${loincInstance.statusReason}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.statusText}">
        <li>
            <g:message code="loinc.statusText.label" default="STATUS_TEXT"/>
            <span class="statusText">${loincInstance.statusText}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.changeReasonPublic}">
        <li>
            <g:message code="loinc.changeReasonPublic.label" default="CHANGE_REASON_PUBLIC"/>
            <span class="changeReasonPublic">${loincInstance.changeReasonPublic}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.commonTestRank}">
        <li>
            <g:message code="loinc.commonTestRank.label" default="COMMON_TEST_RANK"/>
            <span class="commonTestRank">${loincInstance.commonTestRank}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.commonOrderRank}">
        <li>
            <g:message code="loinc.commonOrderRank.label" default="COMMON_ORDER_RANK"/>
            <span class="commonOrderRank">${loincInstance.commonOrderRank}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.commonSiTestRank}">
        <li>
            <g:message code="loinc.commonSiTestRank.label" default="COMMON_SI_TEST_RANK"/>
            <span class="commonSiTestRank">${loincInstance.commonSiTestRank}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.hl7AttachmentStructure}">
        <li>
            <g:message code="loinc.hl7AttachmentStructure.label" default="HL7_ATTACHMENT_STRUCTURE"/>
            <span class="hl7AttachmentStructure">${loincInstance.hl7AttachmentStructure}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.externalCopyrightLink}">
        <li>
            <g:message code="loinc.externalCopyrightLink.label" default="EXTERNAL_COPYRIGHT_LINK"/>
            <span class="externalCopyrightLink">${loincInstance.externalCopyrightLink}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.panelType}">
        <li>
            <g:message code="loinc.panelType.label" default="PanelType"/>
            <span class="panelType">${loincInstance.panelType}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.askAtOrderEntry}">
        <li>
            <g:message code="loinc.askAtOrderEntry.label" default="AskAtOrderEntry"/>
            <span class="askAtOrderEntry">${loincInstance.askAtOrderEntry}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.associatedObservations}">
        <li>
            <g:message code="loinc.associatedObservations.label" default="AssociatedObservations"/>
            <span class="associatedObservations">${loincInstance.associatedObservations}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.versionFirstReleased}">
        <li>
            <g:message code="loinc.versionFirstReleased.label" default="VersionFirstReleased"/>
            <span class="versionFirstReleased">${loincInstance.versionFirstReleased}</span>
        </li>
    </g:if>
    <g:if test="${loincInstance.validHL7AttachmentRequest}">
        <li>
            <g:message code="loinc.validHL7AttachmentRequest.label" default="ValidHL7AttachmentRequest"/>
            <span class="validHL7AttachmentRequest">${loincInstance.validHL7AttachmentRequest}</span>
        </li>
    </g:if>
</ol>
</body>
</html>