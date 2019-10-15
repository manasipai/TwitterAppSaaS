<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- author - rimzim thube -->
<style>
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
<title>Invoke Twitter APIs by clicking on the Buttons</title>
</head>
<body>
   <form action="TwitterServlet" method="GET">
		<button type="submit" class="active" name="searchTweetsSF" value="Search Tweets">Search Tweets</button>
		<br/>
		<button type="submit" class="active" name="getMyTweets" value="Get My Last 5 Tweets">Get My Last 5 Tweets</button>
		<input type="hidden" name="oauth_verifier" id="oauth_verifier" value="<%=request.getParameter("oauth_verifier")%>" />
		<input type="hidden" name="oauth_token" id="oauth_token" value="<%=request.getParameter("oauth_token")%>" />
	</form>
	<form action="TwitterServlet" method="POST">
		<button type="submit" class="active" name="tweet" value="Post Tweet">Post Tweet</button><br>
		<input type="hidden" name="oauth_verifier" id="oauth_verifier" value="<%=request.getParameter("oauth_verifier")%>" />
		<input type="hidden" name="oauth_token" id="oauth_token" value="<%=request.getParameter("oauth_token")%>" />
		
	</form>
</body>
</html>