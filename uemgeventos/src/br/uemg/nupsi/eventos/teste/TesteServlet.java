package br.uemg.nupsi.eventos.teste;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para testes do banco de dados.
 * 
 */
@WebServlet("/TesteServlet")
public class TesteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@PersistenceContext
	private EntityManager em;
	
    public TesteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		writer.println("<!DOCTYPE html><html><head><title>Servlet de Teste</title></head><body>");
		writer.println("<h1>Servlet de teste</h1>");
		
		try {
			
			writer.println("<p>Tentando conectar no banco de dados...");
			
			em.createQuery("SELECT e FROM Evento e").getResultList();
			
			writer.println("Conectado!</p>");
			
			writer.println("<p>Fazendo uma select...");
			
			
			writer.println("FEITO!</p>");
			
		} catch (Exception e) {
			
			writer.println("Erro! :( ==> " + e.getMessage());
			writer.println("</p>");
			e.printStackTrace(writer);
			
		} finally {
			
			writer.println("</body></html>");
		}
		
		writer.flush();
	}

}
