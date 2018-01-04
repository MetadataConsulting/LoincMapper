package org.loinc.gorm

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class SourceOrganizationGormEntity {
    String id
    String name
    String copyright
    String termsOfUse
    String url

    static constraints = {
        id maxSize: 255, nullable: false
        name maxSize: 255, nullable: true
        copyright nullable: true
        termsOfUse nullable: true
        url maxSize: 255, nullable: true
    }

    static mapping = {
        table 'source_organization'
        version false
        id generator: 'assigned', column: 'copyright_id'
        copyright type: 'text'
        termsOfUse column: 'terms_of_use', type: 'text'
    }
}