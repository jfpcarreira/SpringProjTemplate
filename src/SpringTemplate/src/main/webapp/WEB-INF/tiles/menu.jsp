<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<p>
	${message}<br>
	<a href="${pageContext.request.contextPath}/login.htm">Login</a>
	<br />
	<a href="${pageContext.request.contextPath}/logout">Logout</a>
	<br />
	<a href="${pageContext.request.contextPath}/team/list.htm">Team list</a>
	<br />
	<a href="${pageContext.request.contextPath}/team/add.htm">Add new team</a>
	<br/>
	<a href="${pageContext.request.contextPath}/secured/mail/send.htm">Send mail</a>
	<br />
	<a href="${pageContext.request.contextPath}/secured/mail/sendhtm.htm">Send html mail</a>
	<br />
</p>
