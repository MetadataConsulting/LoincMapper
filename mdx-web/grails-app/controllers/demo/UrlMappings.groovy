package demo

class UrlMappings {

    static mappings = {
        "/"(action: 'importLoinc', controller: 'dataModelLoincImport')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
