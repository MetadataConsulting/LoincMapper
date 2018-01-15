package org.snomed.entities;

public interface RefsetMRCMAttributeRange extends RefsetSimple {
    String getRangeConstraint();
    String getAttributeRule();
    String getRuleStrengthId();
    String getContentTypeId();
}
