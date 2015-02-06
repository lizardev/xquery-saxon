<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        <#include "css/module-report-index.css" parse=false>
    </style>
</head>
<body>
<table>
    <tr>
        <th>
            Module
        </th>
        <th>
            Line coverage
        </th>
    </tr>
<#list moduleReportReferences as moduleReportReference>
    <tr>
        <td>
            <a href="${moduleReportReference.path}">${moduleReportReference.name}</a>
        </td>
        <td>
        ${moduleReportReference.lineCoverage?string["0.00"]}
        </td>
    </tr>
</#list>
</table>
</body>
</html>