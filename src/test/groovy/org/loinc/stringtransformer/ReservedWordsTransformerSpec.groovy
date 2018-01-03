package org.loinc.stringtransformer

import org.loinc.stringtransformer.ReservedWordsTransformer
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class ReservedWordsTransformerSpec extends Specification {

    @Subject
    @Shared
    ReservedWordsTransformer transformer = new ReservedWordsTransformer()

    def "test transform"() {

        expect:
        expected == transformer.transform(word)

        where:
        word       | expected
        'class'    | 'clazz'
        'property' | 'prop'
        'foo'      | 'foo'
    }
}
