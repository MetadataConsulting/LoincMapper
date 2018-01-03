package org.loinc.stringtransformer

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class DuplicatedDoubleQuotesToOneDoubleQuoteTransformerSpec extends Specification {

    @Shared
    DuplicatedDoubleQuotesToOneDoubleQuoteTransformer transformer = new DuplicatedDoubleQuotesToOneDoubleQuoteTransformer()

    @Unroll("#word is transformed to: #expected")
    def "test DuplicatedDoubleQuotes to OneDoubleQuote transformer"(String word, String expected) {

        expect:
        expected == transformer.transform(word)

        where:
        word          | expected
        '""Find"" to' | '"Find" to'
        '"Find" to'   | '"Find" to'
    }
}
