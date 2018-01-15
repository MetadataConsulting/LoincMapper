package org.modelcatalogue.stringtransformer

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class NotLettersOrDigitsRemovalTransformerSpec extends Specification {

    @Shared
    NotLettersOrDigitsRemovalTransformer transformer = new NotLettersOrDigitsRemovalTransformer()

    @Unroll("ALL CAPS: #word is lowerCamelCase: #expected")
    def "test transform"(String word, String expected) {

        expect:
        expected == transformer.transform(word)

        where:
        word        | expected
        '﻿loincNum' | 'loincNum'
        'loincNum'  | 'loincNum'
    }
}
