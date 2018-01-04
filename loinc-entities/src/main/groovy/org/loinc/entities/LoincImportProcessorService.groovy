package org.loinc.entities

import groovy.transform.CompileStatic

@CompileStatic
interface LoincImportProcessorService {
    int processInputStream(InputStream inputStream, Integer batchSize, Closure cls)
}