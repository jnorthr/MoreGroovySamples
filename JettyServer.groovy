// http://sysgears.com/articles/embedding-recent-jetty-in-groovy-using-grape/
// the jetty Server is part of the java JDK !
// see: https://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.*
import groovy.servlet.*

@Grapes([
    @Grab(group='javax.servlet', module='javax.servlet-api', version='3.0.1'),
    @Grab(group='org.eclipse.jetty.aggregate', module='jetty-all-server', version='8.1.8.v20121106', transitive=false)
])

def startJetty() {
    def jetty = new Server(9090)

    def context = new ServletContextHandler(jetty, '/', ServletContextHandler.SESSIONS)  // Allow sessions.
    context.resourceBase = '.'  // Look in current dir for Groovy scripts.
    context.addServlet(GroovyServlet, '*.groovy')  // All files ending with .groovy will be served.
    context.addServlet(TemplateServlet, '*.gtpl')  // All files ending with .groovy will be served.
    //context.addServlet(org.eclipse.jetty.servlet.DefaultServlet, "/");
    context.setAttribute('version', '1.0')  // Set an context attribute.

/*
    ResourceHandler resource_handler = new ResourceHandler();
    resource_handler.setDirectoriesListed(true);
    def pageList = ["index.html","example.groovy"]	  
    resource_handler.setWelcomeFiles(pageList);
*/

    jetty.start()
    //jetty.join();
}

println "Starting Jetty, press Ctrl+C to stop."
startJetty()
