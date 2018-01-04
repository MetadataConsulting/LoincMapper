<html>
<head>
    <title>LOINC</title>
    <meta name="layout" content="main" />
</head>
<body>

<table>
    <thead>
        <tr>
            <th><g:message code="loinc.id.label" default="LOINC_NUM"/></th>
        </tr>
    </thead>
    <tbody>
        <g:each var="loincInstance" in="${loincInstanceList}">
            <tr>
                <td><span class="loincNum"><g:link controller="loincShow" action="index" params="[loincNum: loincInstance.id]">${loincInstance.id}</g:link></span></td>
            </tr>
        </g:each>
    </tbody>

</table>

<g:paginate controller="loincIndex" action="index" total="${loincCount}" max="${max}" offset="${offset}" />


<g:link controller="loincImport" action="importLoinc">
    <g:message code="loinc.csv.import" default="Import LOINC CSV"/>
</g:link>

</body>
</html>