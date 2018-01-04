package org.loinc.importcsv.stringtransformer

import groovy.transform.CompileStatic

@CompileStatic
class SnakeCaseToLowerCamelCaseTransformer implements StringTransformer {

    Closure toLowerCaseClosure = { char c ->
        c.toLowerCase()
    }

    Closure toUpperCaseClosure = { char c ->
        c.toUpperCase()
    }

    boolean isSnakeCase(String word) {
        char[] headerLineChars = word.toCharArray()
        int numberOfUnderscore = word.count('_')
        return !( numberOfUnderscore  == 0 && headerLineChars[0] == headerLineChars[0].toUpperCase() )
    }

    @Override
    String transform(String word) {
        char[] headerLineChars = word.toCharArray()
        int numberOfUnderscore = word.count('_')
        if ( !isSnakeCase(word) ) {
            return word
        }
        int size = headerLineChars.length - numberOfUnderscore
        char[] line = new char[size]
        int count = 0
        Closure nextCls = toLowerCaseClosure
        for ( int i = 0; i < headerLineChars.length; i++ ) {
            char c = headerLineChars[i]
            if ( c == '_' ) {
                nextCls = toUpperCaseClosure
                continue
            }

            line[count] = nextCls(c) as char
            nextCls = toLowerCaseClosure
            count++
        }
        new String(line)
    }
}
