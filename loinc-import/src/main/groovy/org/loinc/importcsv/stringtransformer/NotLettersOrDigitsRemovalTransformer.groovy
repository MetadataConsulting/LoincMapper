package org.loinc.importcsv.stringtransformer

import groovy.transform.CompileStatic

@CompileStatic
class NotLettersOrDigitsRemovalTransformer implements StringTransformer {

    @Override
    String transform(String word) {
        char[] arr = word.toCharArray()
        int size = 0
        for ( int i = 0; i < arr.length; i++ ) {
            if ( isLetterOrDigit(arr[i]) ) {
                size++
            }
        }
        char[] result = new char[size]
        int count = 0
        for ( int i = 0; i < arr.length; i++ ) {
            char c = arr[i]
            if ( isLetterOrDigit(c) ) {
                result[count] = c
                count++
            }
        }
        new String(result)
    }

    private static boolean isLetterOrDigit(char c) {
        (
                (c >= ('a' as char) && c <= ('z' as char)) ||
                (c >= ('A' as char) && c <= ('Z' as char)) ||
                (c >= ('0' as char) && c <= ('9' as char))
        )
    }
}
