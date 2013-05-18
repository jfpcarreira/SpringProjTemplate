<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<h1>List of teams</h1>
		<p>Here you can see the list of the teams, edit them, remove or
			update.</p>
		<table border="1">
			<thead>
				<tr>
					<th width="10%">id</th>
					<th width="15%">name</th>
					<th width="10%">rating</th>
					<th width="10%">actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="team" items="${teams}">
					<tr>
						<td>${team.id}</td>
						<td>${team.name}</td>
						<td>${team.rating}</td>
						<td>
							<a href="${pageContext.request.contextPath}/team/edit/${team.id}.html">Edit</a>
							<br>
							<a href="${pageContext.request.contextPath}/team/delete/${team.id}.html">Delete</a>
							<br>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>
			<a href="${pageContext.request.contextPath}/index.html">Home page</a>
		</p>
