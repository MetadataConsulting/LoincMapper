package org.modelcatalogue.core

import spock.lang.Specification

class DataModelLoincCommandSpec extends Specification {

    void 'name cannot be null'() {
        given:
        DataModelLoincCommand cmd = new DataModelLoincCommand()

        when:
        cmd.name = null

        then:
        !cmd.validate(['name'])
    }

    void 'csvFile cannot be null'() {
        given:
        DataModelLoincCommand cmd = new DataModelLoincCommand()

        when:
        cmd.csvFile = null

        then:
        !cmd.validate(['csvFile'])
    }
}
