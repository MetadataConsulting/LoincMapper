package org.loinc.entities;

import java.io.InputStream;

public interface LoincImportService {
    void save(InputStream inputStream, Integer batchSize);
}
