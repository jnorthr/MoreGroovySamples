import org.eclipse.jetty.servlet.*
import groovy.servlet.*

import java.io.IOException;

import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Grapes([
    @Grab(group='javax.servlet', module='javax.servlet-api', version='3.0.1'),
    @Grab(group='org.eclipse.jetty.aggregate', module='jetty-all-server', version='8.1.8.v20121106', transitive=false)
])


public class HelloServlet //extends HttpServlet
{
    private String greeting="Hello World";
    public HelloServlet(){}
    public HelloServlet(String greeting)
    {
        this.greeting=greeting;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>"+greeting+"</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
    }
}