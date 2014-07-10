<#function cssClass lineReport="">
	<#local class>
		<#if lineReport != "">
			<#if lineReport.fullyCovered>covered<#else>not-covered</#if>
		<#else>
		not-instrumented
		</#if>
	</#local>
	<#return class?trim>
</#function>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		.covered {
			background: lightgreen;
			font-family: monospace;
		}

		.not-covered {
			background: lightpink;
			font-family: monospace;
		}

		.not-instrumented {
			font-family: monospace;
		}

		pre {
			margin: 0;
		}
	</style>
</head>
<body>
<table>
<#list lineReports as lineReport>
	<tr class='${cssClass(lineReport)}'>
		<td>${lineReport_index+1}.</td>
		<td>
			<pre>${lines[lineReport_index]?html}</pre>
		</td>
	</tr>
</#list>
</table>
</body>
</html>
