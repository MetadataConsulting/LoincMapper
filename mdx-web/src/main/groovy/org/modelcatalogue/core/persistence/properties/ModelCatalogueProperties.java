package org.modelcatalogue.core.persistence.properties;

import java.util.List;

public interface ModelCatalogueProperties {
    DataElementProperties getDataElement();
    DataClassProperties getDataClass();
    DataTypeProperties getDataType();
    MeasurementUnitProperties getMeasurementUnit();
    List<MetadataProperties> getMetadataList();
}
