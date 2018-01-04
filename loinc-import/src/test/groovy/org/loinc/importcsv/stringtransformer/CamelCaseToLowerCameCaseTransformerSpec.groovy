package org.loinc.importcsv.stringtransformer

import org.loinc.importcsv.stringtransformer.CamelCaseToLowerCameCaseTransformer
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class CamelCaseToLowerCameCaseTransformerSpec extends Specification {

    @Shared
    CamelCaseToLowerCameCaseTransformer transformer = new CamelCaseToLowerCameCaseTransformer()

    @Unroll("CamelCase: #word is lowerCamelCase: #expected")
    def "test snakeCaseToLetterCaseSeparated"(String word, String expected) {

        expect:
        expected == transformer.transform(word)

        where:
        word                        | expected
        'PanelType'                 | 'panelType'
        'AskAtOrderEntry'           | 'askAtOrderEntry'
        'AssociatedObservations'    | 'associatedObservations'
        'VersionFirstReleased'      | 'versionFirstReleased'
        'ValidHL7AttachmentRequest' | 'validHL7AttachmentRequest'
    }
}
