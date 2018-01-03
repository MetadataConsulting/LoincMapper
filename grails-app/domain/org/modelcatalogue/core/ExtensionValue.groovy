package org.modelcatalogue.core

class ExtensionValue {
    String name
    String extensionValue

    static belongsTo = [element: CatalogueElement]

    static constraints = {
        name size: 1..255
        extensionValue maxSize: 10000, nullable: true
    }
}