package org.loinc.stringtransformer

import groovy.transform.CompileStatic

@CompileStatic
class AllCapsToLowerCamelCaseTransformer implements StringTransformer {

    Closure toUpperCaseClosure = { char c ->
        c.toUpperCase()
    }

    boolean isAllCapsCase(String word) {
        word == word.toUpperCase()
    }

    @Override
    String transform(String word) {
        if ( isAllCapsCase(word) ) {
            return word.toLowerCase()
        }
        word
    }

}
