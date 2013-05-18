<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

		<h1>Add team page</h1>
		<p>Here you can add a new team.</p>
	
		<form:form method="POST" commandName="team" action="${pageContext.request.contextPath}/team/add/process.html">
			<table>
				<tbody>
					<tr>
						<td>Name:</td>
						<td><form:input path="name"/></td>
					</tr>
					<tr>
						<td>Rating:</td>
						<td><form:input path="rating"/></td>
					</tr>
					<tr>
						<td colspan="2"><input value="Add" type="submit"></td>
					</tr>
				</tbody>
			</table>
		</form:form>

		<p>
			<a href="${pageContext.request.contextPath}/index.html">Home page</a>
		</p>
