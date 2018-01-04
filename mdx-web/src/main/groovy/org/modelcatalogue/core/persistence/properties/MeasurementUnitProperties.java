package org.modelcatalogue.core.persistence.properties;

public interface MeasurementUnitProperties {
    String getId();
    String getName();
    String getSymbol();
    boolean validate();
}
