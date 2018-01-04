package org.modelcatalogue.core

class MeasurementUnit extends CatalogueElement {
    String symbol

    static constraints = {
        symbol nullable: true, size: 1..100
    }
}