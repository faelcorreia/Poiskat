<%@page import="java.net.URI"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Poiskat</title>
<script type="text/javascript" src="js/validaBusca.js"></script>
<link href="css/result.css" rel="stylesheet" />
</head>
<body>
	<a href="index.jsp"> <img class="bloco"
		src="imagens/logo2.png" /> </a>
	<form method="post" action="PoiskatServlet"
		onSubmit="return validaBusca(this)">

		<input class="cons" name="consulta" type="text" /> <br /> <input
			value="Pesquisar" type="submit" />
	</form>
	<%@ page import="java.io.*"%>
	<%@ page import="poiskat.Resultado;"%>
	<%
		Resultado[] results = (Resultado[]) session
				.getAttribute("resultado");
		String path;
		int pos;
		if (results != null && results.length != 0) {
			for (int i = 0; i < results.length; i++) {
				pos = results[i].getUrl().indexOf("storage");
				path = results[i].getUrl().substring(pos);
				out.print("<div style=\"color: grey;\">");
				out.print("[" + (i + 1) + "] ");
				out.print("</div>\n");
				out.print("<div style=\"font-weight: bold;\">");
				out.print(results[i].getTitulo() + "<br>");
				out.print("</div>\n");
				out.print("<a href=\"" + path + "\">");
				out.print(results[i].getUrl() + "<br><br>");
				out.print("</a>\n");
			}
		} else {
			out.print("Nenhum resultado encontrado.");
		}
	%>
</body>
</html>