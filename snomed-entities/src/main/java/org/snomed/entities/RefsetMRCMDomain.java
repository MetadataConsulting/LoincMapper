package org.snomed.entities;

public interface RefsetMRCMDomain extends RefsetSimple {
    String getDomainConstraint();
    String getParentDomain();
    String getProximalPrimitiveConstraint();
    String getProximalPrimitiveRefinement();
    String getDomainTemplateForPrecoordination();
    String getDomainTemplateForPostcoordination();
    String getGuideURL();
}
