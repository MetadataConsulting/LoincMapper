package org.modelcatalogue.core;

public interface MeasurementUnitProperties {
    String getId();
    String getName();
    String getSymbol();
    boolean validate();
}
