package org.loinc.stringtransformer

import org.loinc.stringtransformer.SnakeCaseToLowerCamelCaseTransformer
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class SnakeCaseToLowerCamelCaseTransformerSpec extends Specification {

    @Shared
    SnakeCaseToLowerCamelCaseTransformer transformer = new SnakeCaseToLowerCamelCaseTransformer()

    @Unroll("snake case #word is letter cased separated #expected")
    def "test snakeCaseToLetterCaseSeparated"(String word, String expected) {

        expect:
        expected == transformer.transform(word)

        where:
        word                     | expected
        'LOINC_NUM'              | 'loincNum'
        'TIME_ASPCT'             | 'timeAspct'
        'SCALE_TYP'              | 'scaleTyp'
        'METHOD_TYP'             | 'methodTyp'
        'CHNG_TYPE'              | 'chngType'
        'EXMPL_ANSWERS'          | 'exmplAnswers'
        'SURVEY_QUEST_TEXT'      | 'surveyQuestText'
        'SURVEY_QUEST_SRC'       | 'surveyQuestSrc'
        'SUBMITTED_UNITS'        | 'submittedUnits'
        'ORDER_OBS'              | 'orderObs'
        'CDISC_COMMON_TESTS'     | 'cdiscCommonTests'
        'HL7_FIELD_SUBFIELD_ID'     | 'hl7FieldSubfieldId'
        'EXTERNAL_COPYRIGHT_NOTICE' | 'externalCopyrightNotice'
        'EXAMPLE_UNITS'             | 'exampleUnits'
        'LONG_COMMON_NAME'          | 'longCommonName'
        'DOCUMENT_SECTION'          | 'documentSection'
        'EXAMPLE_UCUM_UNITS'        | 'exampleUcumUnits'
        'EXAMPLE_SI_UCUM_UNITS'     | 'exampleSiUcumUnits'
        'STATUS_REASON'             | 'statusReason'
        'STATUS_TEXT'               | 'statusText'
        'CHANGE_REASON_PUBLIC'      | 'changeReasonPublic'
        'COMMON_TEST_RANK'          | 'commonTestRank'
        'COMMON_ORDER_RANK'         | 'commonOrderRank'
        'COMMON_SI_TEST_RANK'       | 'commonSiTestRank'
        'HL7_ATTACHMENT_STRUCTURE'  | 'hl7AttachmentStructure'
        'EXTERNAL_COPYRIGHT_LINK'   | 'externalCopyrightLink'
    }
}
