<!DOCTYPE html>
<html>
<head>
     <link rel="stylesheet" href="css/theme.blue.css" type="text/css"/>
     <link rel="stylesheet" href="css/module-report-index.css" type="text/css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.filtertable.min.js"></script>
    <script src="js/jquery.tablesorter.min.js"></script>
    <script>
    $(document).ready(function()
        {
            $('table').tablesorter({
              theme : 'blue',
            });
            $('table').filterTable({
                minRows: 1
            });
        }
    );
    </script>
</head>
<body>
<table class="tablesorter">
    <thead>
        <tr>
            <th id="moduleColumn">
                Module
            </th>
            <th id="coverageColumn">
                Line coverage
            </th>
        </tr>
    </thead>
    <tbody>
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
    </tbody>
</table>
</body>
</html>