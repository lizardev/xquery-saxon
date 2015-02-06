<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        <#include "css/module-report.css" parse=false>
    </style>
    <script>
        function showOrHideInstructionsRow(instructionsRowId) {
            var instructionsRow = document.getElementById(instructionsRowId);
            if (instructionsRow.style.display == 'table-row') {
                instructionsRow.style.display = 'none';
            } else {
                instructionsRow.style.display = 'table-row';
            }
        }
    </script>
</head>
<body>
<table>
<#list lineReports as lineReport>
    <#if lineReport.coverageCollected>
        <#assign lineCssClass = lineReport.fullyCovered?string("line-covered","line-not-covered")/>
        <#assign instructionsId = "instructions-${lineReport.lineNumber}"/>
        <tr onclick="showOrHideInstructionsRow('${instructionsId}')" class="${lineCssClass}">
            <td class="line-number">
                <pre>${lineReport.lineNumber}.</pre>
            </td>
            <td>
                <pre>${lineReport.sourceCodeLine?html}</pre>
            </td>
        </tr>
        <tr id="${instructionsId}" class="instructions">
            <td class="line-number"></td>
            <td>
                <#list lineReport.instructionReports as instructionReport>
                    <#assign instructionCssClass = instructionReport.covered?string("instruction-covered","instruction-not-covered")/>
                    <pre class="${instructionCssClass}">${instructionReport.instruction?html}</pre>
                </#list>
            </td>
        </tr>
    <#else>
        <tr>
            <td class="line-number">
                <pre>${lineReport.lineNumber}.</pre>
            </td>
            <td>
                <pre>${lineReport.sourceCodeLine?html}</pre>
            </td>
        </tr>
    </#if>
</#list>
</table>
</body>
</html>