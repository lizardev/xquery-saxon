<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/module-report-index.css" type="text/css"/>
    <script src="js/jquery-2.1.3.min.js"></script>
    <script src="js/jquery.filtertable.min.js"></script>
    <script src="js/jquery.tablesorter.min.js"></script>
    <script>
    $(document).ready(function()
        {
            $('table').tablesorter();
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
            <th>
                Module
            </th>
            <th>
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