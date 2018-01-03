<html>
    <head>
        <title><g:message code="loinc.import" default="Loinc Import"/></title>
        <meta name="layout" content="main" />
        <style type="text/css">
            form fieldset ol li { list-style-type: none; }
        </style>
    </head>
<body>
    <g:if test="${flash.message}">
        <g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"/>
    </g:if>
</body>
</html>