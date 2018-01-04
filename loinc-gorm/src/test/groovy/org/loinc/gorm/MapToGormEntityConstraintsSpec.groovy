package org.loinc.gorm

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MapToGormEntityConstraintsSpec extends Specification implements DomainUnitTest<MapToGormEntity> {

    void 'test loinc cannot be null'() {
        when:
        domain.loinc = null

        then:
        !domain.validate(['loinc'])
        domain.errors['loinc'].code == 'nullable'
    }

    void 'test loinc can have a maximum of 10 characters'() {
        when: 'for a string of 11 characters'
        String str = 'a' * 11
        domain.loinc = str

        then: 'loinc validation fails'
        !domain.validate(['loinc'])
        domain.errors['loinc'].code == 'maxSize.exceeded'

        when: 'for a string of 10 characters'
        str = 'a' * 10
        domain.loinc = str

        then: 'loinc validation passes'
        domain.validate(['loinc'])
    }

    void 'test map_to cannot be null'() {
        when:
        domain.mapTo = null

        then:
        !domain.validate(['mapTo'])
        domain.errors['mapTo'].code == 'nullable'
    }


    void 'test mapTo can have a maximum of 10 characters'() {
        when: 'for a string of 11 characters'
        String str = 'a' * 11
        domain.mapTo = str

        then: 'mapTo validation fails'
        !domain.validate(['mapTo'])
        domain.errors['mapTo'].code == 'maxSize.exceeded'

        when: 'for a string of 10 characters'
        str = 'a' * 10
        domain.mapTo = str

        then: 'mapTo validation passes'
        domain.validate(['mapTo'])
    }

    void 'test comment can be null'() {
        when:
        domain.comment = null

        then:
        domain.validate(['comment'])
    }

    void 'test comment can have a text of more than 255'() {
        when: 'for a string of 300 characters'
        String str = 'a' * 256
        domain.comment = str

        then: 'comment validation passes'
        domain.validate(['comment'])
    }

}
