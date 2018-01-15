package org.snomed.entities

import groovy.transform.CompileStatic

@CompileStatic
interface SnomedImportProcessorService {
    int processInputStream(InputStream inputStream, Integer batchSize, Closure cls)
}