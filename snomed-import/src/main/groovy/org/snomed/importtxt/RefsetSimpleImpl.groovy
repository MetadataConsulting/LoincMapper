package org.snomed.importtxt

import groovy.transform.CompileStatic
import org.snomed.entities.RefsetSimple

@CompileStatic
class RefsetSimpleImpl implements RefsetSimple {
    String id
    String effectiveTime
    String active
    String moduleId

    @Override
    String getModuleId() {
        return null
    }

    @Override
    String getRefsetId() {
        return null
    }

    @Override
    String getReferencedComponentId() {
        return null
    }
}
