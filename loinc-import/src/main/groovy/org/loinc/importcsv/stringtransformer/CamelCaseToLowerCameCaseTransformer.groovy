package org.loinc.importcsv.stringtransformer

import groovy.transform.CompileStatic

@CompileStatic
class CamelCaseToLowerCameCaseTransformer implements StringTransformer {

    boolean isCamelCase(String word) {
        char[] arr = word.toCharArray()
        return (arr[0] == arr[0].toUpperCase() && arr[1] != arr[1].toUpperCase() )
    }

    @Override
    String transform(String word) {
        if ( word?.size() >= 2 ) {
            if (isCamelCase(word)) {
                char[] arr = word.toCharArray()
                char[] line = new char[arr.length]
                for (int i = 0; i < arr.length; i++) {
                    char c = arr[i]
                    line[i] = ( i == 0 ) ? c.toLowerCase() as char : c
                }
                return new String(line)
            }
        }
        word
    }

}
