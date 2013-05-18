<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

		<h1>Send mail page</h1>
		<p>Here you can send a new email.</p>
	
		<form:form method="POST" commandName="mail" action="${pageContext.request.contextPath}/mail/send/processHTML.html">
			<table>
				<tbody>
					<tr>
						<td>From:</td>
						<td><form:input path="from"/></td>
					</tr>
					<tr>
						<td>To:</td>
						<td><form:input path="to"/></td>
					</tr>
					<tr>
						<td>Subject:</td>
						<td><form:input path="subject"/></td>
					</tr>
					<tr>
						<td colspan="2"><input value="Send" type="submit"></td>
					</tr>
				</tbody>
			</table>
		</form:form>

		<p>
			<a href="${pageContext.request.contextPath}/index.html">Home page</a>
		</p>
