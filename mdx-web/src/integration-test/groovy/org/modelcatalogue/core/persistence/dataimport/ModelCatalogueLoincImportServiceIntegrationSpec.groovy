package org.modelcatalogue.core.persistence.dataimport

import grails.testing.mixin.integration.Integration
import org.modelcatalogue.core.DataModel
import org.modelcatalogue.core.persistence.DataModelGormService
import org.modelcatalogue.core.persistence.dataimport.ModelCatalogueLoincImportService
import spock.lang.Specification

@Integration
class ModelCatalogueLoincImportServiceIntegrationSpec extends Specification {

    ModelCatalogueLoincImportService modelCatalogueLoincImportService

    DataModelGormService dataModelGormService

    def "import loinc into dataModel"() {
        given:
        String dataModelName = 'LOINC'

        expect:
        !dataModelGormService.findByName(dataModelName)

        when:
        File f = new File('src/integration-test/resources/loinc_2.63_oneline.csv')

        then:
        f.exists()

        when:

        InputStream inputStream = f.newInputStream()
        modelCatalogueLoincImportService.save(dataModelName, inputStream, 1)
        DataModel dataModel = dataModelGormService.findByName(dataModelName)

        then:
        dataModel

        cleanup:
        inputStream.close()
    }
}
