package org.modelcatalogue.core

class DataType extends CatalogueElement {
    String rule

    static constraints = {
        rule nullable: true
    }
}