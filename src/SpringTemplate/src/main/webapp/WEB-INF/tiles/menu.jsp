<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">
  </script>

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

	<button type='button' onclick='RestGet()'>TESTE JSON</button>
</p>

<script>
	// Não funciona
	var RestGet = function() {
		$.ajax({
	        type: 'GET',
	        url:  prefix + '/SpringTemplate/team/3',
	        dataType: 'json',
	        async: true,
	        success: function(result) {
	            alert('ID: ' + team.id
		                + '\nNome : ' + team.name
		                + '\nNota: ' + team.rating);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.status + ' ' + jqXHR.responseText);
	        }
	   });
	}
</script>
