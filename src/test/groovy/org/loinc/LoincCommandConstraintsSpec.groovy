package org.loinc

import spock.lang.Specification

class LoincCommandConstraintsSpec extends Specification {

    void 'test csvFile cannot be null'() {
        given:
        LoincCommand cmd = new LoincCommand()

        when:
        cmd.csvFile = null

        then:
        !cmd.validate(['csvFile'])
    }
}
