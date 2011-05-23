<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Poiskat</title>
<script type="text/javascript" src="js/validaBusca.js"></script>
<link href="css/main.css" rel="stylesheet" />
</head>
<body>
	<a href="localhost:8080/Poiskat/index.jsp"> <img class="bloco"
		src="imagens/logo.png" /> </a>
	<form method="post" action="PoiskatServlet"
		onSubmit="return validaBusca(this)">

		<input class="cons" name="consulta" type="text" /> <br />
		<input value="Pesquisar" type="submit" />

	</form>
</body>
</html>