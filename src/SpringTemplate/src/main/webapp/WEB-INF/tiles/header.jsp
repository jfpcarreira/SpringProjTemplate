<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>HEADER</h1>
<sec:authorize access="fullyAuthenticated">
	<h3>User: <sec:authentication property="principal.username" /></h3>
</sec:authorize>
