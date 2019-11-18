package devops;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
			throws ServletException, IOException {
		
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("db_produits_smvc");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
         
        /*User newUser = new User();
        newUser.setEmail("billjoya@gmail.com");
        newUser.setFullname("bill Joy");
        newUser.setPassword("billi");
        entityManager.persist(newUser);*/
        String sql = "SELECT u from User u";
        Query query = entityManager.createQuery(sql);
        ArrayList<User> users =  (ArrayList<User>) query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
		
		response.setContentType("text/html");
		response.setCharacterEncoding( "UTF-8" );
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\" />");
		out.println("<title>Test</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>Users List</p>");
		for (User user : users) {
			out.println("<p>"+user.toString()+"</p>");
		}
		out.println("</body>");
		out.println("</html>");
	}
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}
	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
}