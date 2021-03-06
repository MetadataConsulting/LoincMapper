package org.loinc.importcsv

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.loinc.LoincImpl
import org.loinc.entities.Loinc
import org.loinc.entities.LoincImportProcessorService
import org.modelcatalogue.stringtransformer.AllCapsToLowerCamelCaseTransformer
import org.modelcatalogue.stringtransformer.CamelCaseToLowerCameCaseTransformer
import org.modelcatalogue.stringtransformer.DuplicatedDoubleQuotesToOneDoubleQuoteTransformer
import org.modelcatalogue.stringtransformer.LeadingTrailingDoubleQuotesRemovalTransformer
import org.modelcatalogue.stringtransformer.NotLettersOrDigitsRemovalTransformer
import org.modelcatalogue.stringtransformer.ReservedWordsTransformer
import org.modelcatalogue.stringtransformer.SnakeCaseToLowerCamelCaseTransformer
import org.modelcatalogue.stringtransformer.StringTransformer

@Slf4j
@CompileStatic
class ImportLoincService implements GrailsConfigurationAware, LoincImportProcessorService {

    boolean nullIfValueBlank
    List<StringTransformer> headerTransformers = [
            new LeadingTrailingDoubleQuotesRemovalTransformer(),
            new DuplicatedDoubleQuotesToOneDoubleQuoteTransformer(),
            new SnakeCaseToLowerCamelCaseTransformer(),
            new AllCapsToLowerCamelCaseTransformer(),
            new CamelCaseToLowerCameCaseTransformer(),
            new ReservedWordsTransformer(),
            new NotLettersOrDigitsRemovalTransformer(),
    ] as List<StringTransformer>
    List<StringTransformer> valueTransformers = [
            new LeadingTrailingDoubleQuotesRemovalTransformer(),
            new DuplicatedDoubleQuotesToOneDoubleQuoteTransformer(),
    ] as List<StringTransformer>


    @Override
    void setConfiguration(Config co) {
        this.nullIfValueBlank = co.getProperty('loinic.csv.nullIfValueBlank', Boolean, true)
    }

    @Override
    int processInputStream(InputStream inputStream, Integer batchSize, Closure cls) {
        int processed = 0
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        List<String> headerPropertyList
        List<String> lines = []
        String line
        for ( int ln = 0; (line = br.readLine()) != null; ln++ ) {
            if ( ln == 0 ) {
                headerPropertyList = headerLineToPropertiesList(line)
            } else {
                lines << line
                if ( lines.size() == batchSize ) {
                    log.debug('processing batch at line #{}', ln)
                    List<Loinc> loincList = loincListWithLines(headerPropertyList, lines)
                    cls(loincList)
                    processed += lines.size()
                    lines.clear()
                }
            }
        }
        if ( !lines.isEmpty() ) {
            log.debug('processing last batch')
            List<Loinc> loincList = loincListWithLines(headerPropertyList, lines)
            cls(loincList)
            processed += lines.size()
        }
        processed
    }

    List<Loinc> loincListWithLines(List<String> headerPropertyList, List<String> lines) {
        lines.collect { String line ->
            List<String> values = lineValues(line)
            loincWithLine(headerPropertyList, values)
        }
    }

    @CompileDynamic
    List<String> readLines(InputStream inputStream, IntRange lineRange) {
        List<String> lines = []
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        String line
        for ( int ln = 0; (line = br.readLine()) != null && ln <= lineRange.to; ln++ ) {
            if (ln >= lineRange.from) {
                lines << line
            }
        }
        lines
    }

    List<String> headerLineToPropertiesList(String headerLine) {
        processLineWithTransformers(headerLine, headerTransformers)
    }

    List<String> processLineWithTransformers(String line, List<StringTransformer> transformerList) {
        List<String> headers = line.split(/,(?=([^\"]*\"[^\"]*\")*[^\"]*$)/) as List<String>
        for ( StringTransformer transformer : transformerList ) {
            headers = headers.collect { transformer.transform(it) }
        }
        headers
    }

    Loinc loincWithLine(List<String> headerPropertyList, List<String> values) {
        LoincImpl loinc = new LoincImpl()
        for ( int i = 0; i < headerPropertyList.size(); i++ ) {
            String headerPropertyName = headerPropertyList[i]
            Object value = valueAtPosition(values, i)
            populateLoincWithValue(loinc, headerPropertyName, value)
        }
        loinc
    }

    void populateLoincWithValue(LoincImpl loinc, String propertyname, Object value) {
        if ( loinc.hasProperty( propertyname) ) {

            if (value == null) {
                loinc.setProperty(propertyname, null)
                return
            }

            Class propClass = loinc.class.getDeclaredField(propertyname).type
            if ( propClass == Integer) {
                try {
                    Integer intValue = value as Integer
                    loinc.setProperty(propertyname, intValue)
                } catch(NumberFormatException e) {

                }
                return

            }
            loinc.setProperty(propertyname, value)
        }
    }

    Object valueAtPosition(List<String> values, int i) {
        if ( values.size() > i ) {
            Object value = values[i]
            if ( nullIfValueBlank && value == '') {
                return null
            }
            return value
        }
        null
    }

    List<String> lineValues(String line) {
        processLineWithTransformers(line, valueTransformers)
    }
}