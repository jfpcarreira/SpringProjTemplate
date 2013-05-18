<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

		<h1>Edit team page</h1>
		<p>Here you can edit the existing team.</p>
		<p>${message}</p>
		<form:form method="POST" commandName="team" action="${pageContext.request.contextPath}/team/edit/${team.id}.html">
			<table>
				<tbody>
					<tr>
						<td>Name:</td>
						<td><form:input path="name"></form:input></td>
					</tr>
					<tr>
						<td>Rating:</td>
						<td><form:input path="rating"></form:input></td>
					</tr>
					<tr>
						<td colspan="2"><input value="Edit" type="submit"></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		<p>
			<a href="${pageContext.request.contextPath}/index.html">Home page</a>
		</p>	
