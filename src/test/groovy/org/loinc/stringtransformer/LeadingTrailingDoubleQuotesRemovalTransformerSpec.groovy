package org.loinc.stringtransformer

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class LeadingTrailingDoubleQuotesRemovalTransformerSpec extends Specification {

    @Subject
    @Shared
    LeadingTrailingDoubleQuotesRemovalTransformer transformer = new LeadingTrailingDoubleQuotesRemovalTransformer()

    @Unroll("#word gets transformed to #expected")
    def "test transform"(String word, String expected) {

        expect:
        expected == transformer.transform(word)

        where:
        word                                        | expected
        '"Study observation"'                       | 'Study observation'
        'Study observation'                         | 'Study observation'
        '"Study observation'                        | 'Study observation'
        '"Changed Property from "Find" to "Hx","'   | 'Changed Property from "Find" to "Hx",'
    }
}
