<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<!DOCTYPE html>

<html>
	<head>
		<title> <tiles:insertAttribute name="title"/> </title>
		<meta charset='utf-8'>
	</head>
	<body>

		<table border="1">
		    <tr>
        		<td height="30" colspan="2"><tiles:insertAttribute name="header"/> </td>
		    </tr>
		    <tr>
        		<td height="250"><tiles:insertAttribute name="menu" /></td>
        		<td width="350"><tiles:insertAttribute name="body" /></td>
		    </tr>
    		<tr>
        		<td height="30" colspan="2"><tiles:insertAttribute name="footer"/> </td>
		    </tr>
		</table>

	</body>
</html>
