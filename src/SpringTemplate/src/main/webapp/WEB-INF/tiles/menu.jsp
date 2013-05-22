<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<p>
	${message}<br/>
	<sec:authorize access="! authenticated">
		<a href="${pageContext.request.contextPath}/login.htm">Login</a> <br />
	</sec:authorize>
	<sec:authorize access="fullyAuthenticated">
		<a href="${pageContext.request.contextPath}/logout">Logout</a> <br />
	</sec:authorize>
	<a href="${pageContext.request.contextPath}/team/list.htm">Team list</a> <br />
	<a href="${pageContext.request.contextPath}/team/add.htm">Add new team</a> <br />
	<a href="${pageContext.request.contextPath}/secured/mail/send.htm">Send mail</a> <br />
	<a href="${pageContext.request.contextPath}/secured/mail/sendHTML.htm">Send html mail</a> <br />
</p>
