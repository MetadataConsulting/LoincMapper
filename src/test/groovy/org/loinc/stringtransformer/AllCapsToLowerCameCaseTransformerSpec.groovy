package org.loinc.stringtransformer

import org.loinc.stringtransformer.AllCapsToLowerCamelCaseTransformer
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class AllCapsToLowerCameCaseTransformerSpec extends Specification {

    @Shared
    AllCapsToLowerCamelCaseTransformer transformer = new AllCapsToLowerCamelCaseTransformer()

    @Unroll("ALL CAPS: #word is lowerCamelCase: #expected")
    def "test transform"(String word, String expected) {

        expect:
        expected == transformer.transform(word)

        where:
        word                        | expected
        'COMPONENT'                 | 'component'
        'PROPERTY'                  | 'property'
        'SYSTEM'                    | 'system'
        'CLASS'                     | 'class'
        'STATUS'                    | 'status'
        'CLASSTYPE'                 | 'classtype'
        'FORMULA'                   | 'formula'
        'SPECIES'                   | 'species'
        'UNITSREQUIRED'             | 'unitsrequired'
        'RELATEDNAMES2'             | 'relatednames2'
        'SHORTNAME'                 | 'shortname'
    }
}