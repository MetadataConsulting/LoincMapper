package org.loinc.gorm

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class MapToGormEntity implements Serializable {

    String loinc
    String mapTo
    String comment

    static constraints = {
        loinc nullable: false, maxSize: 10
        mapTo nullable: false, maxSize: 10
        comment nullable: true
    }

    static mapping = {
        table 'map_to'
        version false
        id generator: 'assigned', composite: ['loinc', 'mapTo']
        mapTo column: 'map_to'
        comment type: 'text'
    }
}