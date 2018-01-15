package org.snomed.entities;

public interface RefsetExtendedMap extends RefsetSimpleMap {
    String getMapGroup();
    String getMapPriority();
    String getMapRule();
    String getMapAdvice();
    String getCorrelationId();
    String getMapCategoryId();
}
