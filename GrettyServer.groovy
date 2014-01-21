import org.mbte.gretty.httpserver.* 
// cannot resolve dependency - looks like arifactory was taken out by bintray.com jnorthr 
@GrabResolver(name='gretty',
  root='http://groovypp.artifactoryonline.com/groovypp/libs-releases-local')
@Grab('org.mbte.groovypp:gretty:0.4.302') 
@GrabConfig(systemClassLoader=true)

public class GrettyServer
{
    def addr = InetAddress.localHost.hostAddress
    def hname = InetAddress.localHost.hostName
    def cname =InetAddress.localHost.canonicalHostName

    GrettyServer server = []
    public GrettyServer()
    {
        server.groovy =
        [
            localAddress: new InetSocketAddress("localhost", 8080),
            defaultHandler:
            {
                response.redirect "/"
            },
            "/:name":
           {
                get
                {
                   response.html = template("index.gpptl", [message: "Hello ${request.parameters['name']}",addr:"${addr}",hname:"${hname}",cname:"${cname}"    ])
                } // end of get
            } // end of
        ] // end of server.groovy 

        server.start()
    } // end of constructor
    
    public static void main(String[] args)
    {
        def gs = new GrettyServer();
    } // end of main    
        
} // end of class