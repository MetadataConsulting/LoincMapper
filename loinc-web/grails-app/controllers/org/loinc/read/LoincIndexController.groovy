package org.loinc.read

import groovy.transform.CompileStatic
import org.loinc.gorm.LoincGormEntity
import org.loinc.gorm.LoincGormService

@CompileStatic
class LoincIndexController {

    LoincGormService loincGormService

    static allowedMethods = [index: 'GET']

    def index(String loincNum) {
        int max = params.int('max') ?: 0
        int offset = params.int('offset') ?: 0
        List<LoincGormEntity> loincInstanceList = loincGormService.findAll(offset, max)
        int total = loincGormService.count()

        render view: '/loinc/index', model: [loincInstanceList: loincInstanceList, loincCount: total]
    }
}