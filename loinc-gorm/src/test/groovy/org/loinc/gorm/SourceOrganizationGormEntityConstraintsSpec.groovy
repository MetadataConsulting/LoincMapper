package org.loinc.gorm

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SourceOrganizationGormEntityConstraintsSpec extends Specification implements DomainUnitTest<SourceOrganizationGormEntity> {

    void 'test id cannot be null'() {
        when:
        domain.id = null

        then:
        !domain.validate(['id'])
        domain.errors['id'].code == 'nullable'
    }

    void 'test id can have a maximum of 255 characters'() {
        when: 'for a string of 11 characters'
        String str = 'a' * 256
        domain.id = str

        then: 'id validation fails'
        !domain.validate(['id'])
        domain.errors['id'].code == 'maxSize.exceeded'

        when: 'for a string of 10 characters'
        str = 'a' * 255
        domain.id = str

        then: 'id validation passes'
        domain.validate(['id'])
    }

    void 'test name can be null'() {
        when:
        domain.name = null

        then:
        domain.validate(['name'])
    }

    void 'test name can have a maximum of 255 characters'() {
        when: 'for a string of 11 characters'
        String str = 'a' * 256
        domain.name = str

        then: 'name validation fails'
        !domain.validate(['name'])
        domain.errors['name'].code == 'maxSize.exceeded'

        when: 'for a string of 10 characters'
        str = 'a' * 255
        domain.name = str

        then: 'name validation passes'
        domain.validate(['name'])
    }

    void 'test url can be null'() {
        when:
        domain.url = null

        then:
        domain.validate(['url'])
    }

    void 'test url can have a maximum of 255 characters'() {
        when: 'for a string of 11 characters'
        String str = 'a' * 256
        domain.url = str

        then: 'url validation fails'
        !domain.validate(['url'])
        domain.errors['url'].code == 'maxSize.exceeded'

        when: 'for a string of 10 characters'
        str = 'a' * 255
        domain.url = str

        then: 'url validation passes'
        domain.validate(['url'])
    }

    void 'test copyright can be null'() {
        when:
        domain.copyright = null

        then:
        domain.validate(['copyright'])
    }

    void 'test copyright can have a text of more than 255'() {
        when: 'for a string of 300 characters'
        String str = 'a' * 256
        domain.copyright = str

        then: 'copyright validation passes'
        domain.validate(['copyright'])
    }

    void 'test termsOfUse can be null'() {
        when:
        domain.termsOfUse = null

        then:
        domain.validate(['termsOfUse'])
    }

    void 'test termsOfUse can have a text of more than 255'() {
        when: 'for a string of 300 characters'
        String str = 'a' * 256
        domain.termsOfUse = str

        then: 'termsOfUse validation passes'
        domain.validate(['termsOfUse'])
    }
}
