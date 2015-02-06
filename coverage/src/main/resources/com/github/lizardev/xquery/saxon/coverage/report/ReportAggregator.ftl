<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        <#include "css/style.css" parse=false>
    </style>
</head>
<body>
<table>
<#list reportFiles as reportFile>
        <tr>
            <td>
                <a href="file:///${reportFile.path}">${reportFile.name}</a>
            </td>
        </tr>
</#list>
</table>
</body>
</html>