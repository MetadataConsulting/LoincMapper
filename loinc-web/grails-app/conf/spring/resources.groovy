import org.loinc.gorm.ImportLoincGormService
import org.loinc.importcsv.ImportLoincService

beans = {
    loincImportProcessorService(ImportLoincService)
    loincImportService(ImportLoincGormService) {
        loincImportProcessorService = ref('loincImportProcessorService')
        loincGormService = ref('loincGormService')
        sessionFactory = ref('sessionFactory')
        messageSource = ref('messageSource')

    }
}
