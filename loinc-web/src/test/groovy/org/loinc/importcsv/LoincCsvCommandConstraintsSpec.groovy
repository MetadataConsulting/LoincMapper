package org.loinc.importcsv

import org.loinc.importcsv.LoincCsvCommand
import spock.lang.Specification

class LoincCsvCommandConstraintsSpec extends Specification {

    void 'test csvFile cannot be null'() {
        given:
        LoincCsvCommand cmd = new LoincCsvCommand()

        when:
        cmd.csvFile = null

        then:
        !cmd.validate(['csvFile'])
    }
}
