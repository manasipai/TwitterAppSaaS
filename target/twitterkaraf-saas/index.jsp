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
</head>
<body>
<h1>Twitter App Karaf</h1>
	<form action="TwitterServlet" method="GET">
	<button type="submit" class="active" name="login" value="Login with Twitter">Login with Twitter</button>
	</form>

</body>
</html>
