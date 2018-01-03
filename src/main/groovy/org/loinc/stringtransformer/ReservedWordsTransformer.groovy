package org.loinc.stringtransformer

import groovy.transform.CompileStatic

@CompileStatic
class ReservedWordsTransformer implements StringTransformer {
    Map<String, String> reservedWords = ['class': 'clazz', 'property': 'prop']

    @Override
    String transform(String word) {

        if ( reservedWords.keySet().contains(word) ) {
            return reservedWords[word]
        }

        word
    }
}
