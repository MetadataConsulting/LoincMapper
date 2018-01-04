package org.loinc.read

import groovy.transform.CompileStatic
import org.loinc.gorm.LoincGormEntity
import org.loinc.gorm.LoincGormService
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND

@CompileStatic
class LoincShowController {

    LoincGormService loincGormService

    static allowedMethods = [index: 'GET']

    def index(String loincNum) {
        LoincGormEntity loincInstance = loincGormService.findByLoincNum(loincNum)
        if ( loincInstance == null ) {
            render status: SC_NOT_FOUND
            return
        }

        render view: '/loinc/show', model: [loincInstance: loincInstance]
    }
}