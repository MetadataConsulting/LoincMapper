<html>
    <head>
        <title><g:message code="loinc.import" default="Loinc Import"/></title>
        <meta name="layout" content="main" />
        <style type="text/css">
            form fieldset ol li { list-style-type: none; }
        </style>
    </head>
    <body>
        <div id="content" role="main">
            <g:uploadForm action="uploadLoinc" controller="loinc">
                <fieldset>
                    <ol>
                        <li>
                            <label for="csvFile"><g:message code="loinc.import.file.csv" default="Loinc CSV"/></label>
                            <input type="file" name="csvFile" />
                        </li>
                        <li>
                            <label for="batchSize"><g:message code="loinc.import.batchSize" default="Batch Size"/></label>
                            <select name="batchSize">
                                <option value="10">10</option>
                                <option value="100">100</option>
                                <option value="200">200</option>
                                <option value="500">500</option>
                                <option value="1000">1000</option>
                            </select>
                        </li>
                        <li>
                            <input type="submit" value="${message(code: 'loincImport.import.submit', default: 'Submit')}"/>
                        </li>
                    </ol>
                </fieldset>
            </g:uploadForm>
        </div><!-- /#content -->
    </body>
</html>