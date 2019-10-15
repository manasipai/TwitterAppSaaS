<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link href="./twitterCSS.css" rel="stylesheet" type="text/css">  -->
<!-- author - rimzim thube -->
<style>
 .tweet-input {
	padding: 8px 10px;
	display: block;
    overflow-y: auto;
    word-wrap: break-word;
    border-color: #99CDE1;
    max-height: 240px;
    min-height: 80px;
    overflow: hidden;
    text-shadow: none;
    width: 500px;
 }
  span {
    float: right;
    padding: 18px 14px;
  }
  .warning {
    color: #a76c00;
  }
  .danger {
    color: #800000;
  }
  button {
    background-color: #ccc;
    border: 1px solid #eee;
    border-radius: 10px;
    color: #fefefe;
    font-size: 18px;
  }
  button:hover {
    background-color: #333;
  }
  button.active {
    background-color: #008cff;
    cursor: pointer;
  }
  
</style>
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