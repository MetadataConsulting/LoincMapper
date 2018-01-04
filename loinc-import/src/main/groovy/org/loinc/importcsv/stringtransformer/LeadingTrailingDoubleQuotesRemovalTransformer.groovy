package org.loinc.importcsv.stringtransformer

class LeadingTrailingDoubleQuotesRemovalTransformer implements StringTransformer {

    @Override
    String transform(String word) {
        String result = word
        if ( word.startsWith('"') ) {
            result = result.substring(1, result.length())
        }
        if ( word.endsWith('"') ) {
            result = result.substring(0, result.length() - 1)
        }
        result
    }
}
