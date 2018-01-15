package org.snomed.importtxt

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.modelcatalogue.stringtransformer.StringTransformer
import org.snomed.entities.Refset
import org.snomed.entities.RefsetSimple
import org.snomed.entities.SnomedImportProcessorService

@Slf4j
@CompileStatic
class SnomedImportService implements SnomedImportProcessorService {
    List<StringTransformer> headerTransformers = []
    List<StringTransformer> valueTransformers = []


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
                    List<RefsetSimple> refsetList = refsetWithLines(headerPropertyList, lines)
                    cls(refsetList)
                    processed += lines.size()
                    lines.clear()
                }
            }
        }
        if ( !lines.isEmpty() ) {
            log.debug('processing last batch')
            List<RefsetSimple> refsetList = refsetWithLines(headerPropertyList, lines)
            cls(refsetList)
            processed += lines.size()
        }
        processed
    }

    List<String> headerLineToPropertiesList(String headerLine) {
        processLineWithTransformers(headerLine, headerTransformers)
    }

    List<String> processLineWithTransformers(String line, List<StringTransformer> transformerList) {
        List<String> headers = line.split(/\t/) as List<String>
        for ( StringTransformer transformer : transformerList ) {
            headers = headers.collect { transformer.transform(it) }
        }
        headers
    }

    List<RefsetSimple> refsetWithLines(List<String> headerPropertyList, List<String> lines) {
        lines.collect { String line ->
            List<String> values = lineValues(line)
            refsetWithLine(headerPropertyList, values)
        } as List<RefsetSimple>
    }

    RefsetSimple refsetWithLine(List<String> headerPropertyList, List<String> values) {
        LoincImpl loinc = new LoincImpl()
        for ( int i = 0; i < headerPropertyList.size(); i++ ) {
            String headerPropertyName = headerPropertyList[i]
            Object value = valueAtPosition(values, i)
            populateLoincWithValue(loinc, headerPropertyName, value)
        }
        loinc
    }

    List<String> lineValues(String line) {
        processLineWithTransformers(line, valueTransformers)
    }
}
