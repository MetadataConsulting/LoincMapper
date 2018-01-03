package org.loinc

import groovy.transform.CompileStatic

@CompileStatic
interface LoincImport {

    void save(InputStream inputStream, Integer batchSize)
}