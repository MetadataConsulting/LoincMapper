package org.modelcatalogue.core

class DataElement extends CatalogueElement {
    DataType dataType

    static constraints = {
        dataType nullable: true, fetch: 'join'
    }
}