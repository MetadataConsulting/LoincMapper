package org.loinc

class UrlMappings {

    static mappings = {
        "/"(action: 'index', controller: 'loincIndex')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
