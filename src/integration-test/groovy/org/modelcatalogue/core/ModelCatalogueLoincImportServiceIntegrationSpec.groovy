package org.modelcatalogue.core

import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
class ModelCatalogueLoincImportServiceIntegrationSpec extends Specification {

    ModelCatalogueLoincImportService modelCatalogueLoincImportService

    def "import loinc into dataModel"() {
        when:
        File f = new File('src/integration-test/resources/loinc_2.63_oneline.csv')

        then:
        f.exists()

        when:
        String dataModelName = 'LOINC'
        InputStream inputStream = f.newInputStream()
        modelCatalogueLoincImportService.save(dataModelName, inputStream, 1)

        then:
        true

        cleanup:
        inputStream.close()
    }
}
