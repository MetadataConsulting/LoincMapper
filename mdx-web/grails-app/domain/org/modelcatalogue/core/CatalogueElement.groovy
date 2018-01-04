package org.modelcatalogue.core

abstract class CatalogueElement {
    // time stamping
    Date dateCreated
    Date lastUpdated

    DataModel dataModel
    String name
    String description
    String modelCatalogueId

    static constraints = {
        modelCatalogueId nullable: true, size: 1..255
        name nullable: false, blank: false, size: 1..255
        description nullable: true, blank: true, maxSize: 20000
        dateCreated bindable: false
        lastUpdated bindable: false
        dataModel nullable: true
    }

    static mapping = {
        tablePerHierarchy false
        description type: 'text'
    }
}