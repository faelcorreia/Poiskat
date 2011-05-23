package poiskat;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PoiskatServlet
 */
@WebServlet("/PoiskatServlet")
public class PoiskatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public PoiskatServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MaquinaBusca busca = new MaquinaBusca(getServletContext().getRealPath(
				"indice"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Resultado[] results;
		String consulta = request.getParameter("consulta");
		HttpSession session = request.getSession();

		if (consulta != null) {
			results = busca.processaConsulta(consulta);
			session.setAttribute("resultado", results);
			response.sendRedirect("result.jsp");/*
			out.print("<%@ page language=\"java\" contentType=\"text/html; charset=ISO-8859-1\"\n"
					+ "pageEncoding=\"ISO-8859-1\"%>\n"
					+ "<!DOCTYPE html PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN \"http://www.w3.org/TR/html4/loose.dtd\">\n"
					+ "<html>\n"
					+ "<head>\n"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n"
					+ "<title>Poiskat</title>\n"
					+ "<script type=\"text/javascript\" src=\"js/validaBusca.js\"></script>\n"
					+ "<link href=\"css/result.css\" rel=\"stylesheet\" />\n"
					+ "</head>\n"
					+ "<body>\n"
					+ "<a href=\"localhost:8080/Poiskat/index.jsp\"> <img class=\"bloco\"\n"
					+ "src=\"imagens/logo2.png\" /> </a>\n"
					+ "<form method=\"post\" action=\"PoiskatServlet\"\n"
					+ "onSubmit=\"return validaBusca(this)\">\n"
					+ "<input class=\"cons\" name=\"consulta\" type=\"text\" /> <br />\n"
					+ "<input value=\"Pesquisar\" type=\"submit\" />\n"
					+ "</form>");
			for (int i = 0; i < results.length; i++) {
				out.print("[" + (i + 1) + "] ");
				out.print(results[i].getTitulo() + "<br>");
				out.print(results[i].getUrl() + "<br><br>");
			}*/
		} else {
		}

		out.println("<P>Return to <A HREF=../simpleHTML.html>Form</A>");
		out.close();
	}

}
