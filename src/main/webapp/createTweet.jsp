<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link href="./twitterCSS.css" rel="stylesheet" type="text/css">  -->
<!-- author - rimzim thube -->

<title>Create Tweet</title>
</head>
<body>
<% if (request.getAttribute("status") == null) { %>
		<h3>Tweets Here!</h3>
		<form action="TwitterServlet" method="POST">
			<textarea class="tweet-input" name="status" value=""></textarea>
			<button type="submit" class="active" name="createTweet" value="tweet">Tweet</button>
			<input type="hidden" name="oauth_verifier" id="oauth_verifier" value="<%=request.getParameter("oauth_verifier")%>" />
			<input type="hidden" name="oauth_token" id="oauth_token" value="<%=request.getParameter("oauth_token")%>" />
		</form>
		
	
		<table>
		<tr>
		<td>
		<% } else {
		String status = request.getAttribute("status").toString(); %>
		Tweet " <%=status%> " is posted to Twitter!
			<% } %>
		</td>
		</tr>
		</table>

	
	<%
   String homePage = "twitterAPICalls.jsp";
	%>
	<br>
	<div>
	<table>
	<tr>
	<td>
	<button class="active"  value="HomePage" onClick="JavaScript:window.location='<%= homePage %>';">HomePage</button>
	</td>
	</tr>
	</table>
	</div>
	
	
</body>
</html>