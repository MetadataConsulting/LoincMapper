package org.snomed.entities;

public interface RefsetMRCMAttributeDomain extends RefsetSimple {
    String getDomainIcd();
    String getGrouped();
    String getAttributeCardinality();
    String getAttributeInGroupCardinality();
    String getRuleStrengthId();
    String getContentTypeId();
}
