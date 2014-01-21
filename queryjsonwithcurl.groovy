#!/usr/bin/env groovy
// this is a bash script file that will run quite nicely if you have an installed groovy development kit; the sample groovy source is from Mr Haki's webite.
// a nice tutorial to use curl and groovy's jsonsluper to read json (requires groovy 1.8.0+)
// curl tutorial: http://curl.haxx.se/docs/httpscripting.html
// see: http://groovy.codehaus.org/gapi/groovy/json/JsonSlurper.html
// see: http://mrhaki.blogspot.com/2011/11/grassroots-groovy-reading-json-with.html
// if (responsetext.class=="java.lang.String") 
// if (responsetext.class!="java.lang.String") 

// also: curl http://api.flickr.com/services/feeds/groups_pool.gne?id=675729@N22&lang=en-us&format=json
// but does not seem to return json formatted data

import groovy.json.JsonSlurper;
class JsonConsumer
{
    private final String JSON_URL = "http://www.mrhaki.com/samples/sample.json";
    URL url;
    JsonSlurper jsonSlurper;
    Object result;
    def responsetext;
    public Map jsonResult;

    def static js =
    """{
        "user": 
        {
            "name": "jim",
            "age": 65,
            "interests": ["Cobol", "RPG"]
        }
}""";

    public JsonConsumer()
    {
        responsetext = "";
        url = null;
        jsonSlurper = new JsonSlurper();

    } // end of constructor
    
    public say(txt) {println txt}
    
    public JsonConsumer(address)
    {
        this();
        try 
        { 
            url = new URL(address); 
            InputStream urlStream = null;
            urlStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream));
            result = jsonSlurper.parse(reader);        // use parse() for bufferedReader input
            println result
        } 
        catch(java.net.MalformedURLException x) 
        {
            println "java.net.MalformedURLException"
            // use curl to pull back json formatted data from a URL
            responsetext = address;    //"curl ${address}".execute().text
            if (responsetext==null || responsetext.length()<1) throw new IllegalArgumentException("no data received from ${JSON_URL}")
        
            println "responsetext.class : ${responsetext.class}\n${responsetext}"
            
            result = jsonSlurper.parseText(responsetext);    // just use parse() if bufferedReader input
        } // end of catch
        
    } // end of constructor
    
    public getMap()
    {
        jsonResult = (Map) result;
        return jsonResult;
    }    // end of get
    
    public getResult()
    {
        return result;
    }    // end of method
    
    public static void main(String[] args)
    {
        if (args)
        {    
                JsonConsumer jo = new JsonConsumer(args[0]);
                def data = jo.getMap();
                println data;
                exit(0);
        } // end of if
        
        def mrhaki = "http://www.mrhaki.com/samples/sample.json";
        JsonConsumer jc = new JsonConsumer(mrhaki);
        def jsonResult = jc.getMap();
        Map user = (Map) jsonResult.get("user");
        String name = (String) user.get("name");
        Integer age = (Integer) user.get("age");
        List interests = (List) user.get("interests");
    
        assert name.equals("mrhaki");
        assert age == 38;
        assert interests.size() == 2;
        assert interests.get(0).equals("Groovy");
        assert interests.get(1).equals("Grails"); 

        println "======================="
        println "name="+name
        println "age="+age
        interests.eachWithIndex{v,ix -> println "  interest[${ix}]:${v}"}
        println "======================="

        jc = new JsonConsumer(js);
        jsonResult = jc.getMap();
        user = (Map) jsonResult.get("user");
        name = (String) user.get("name");
        age = (Integer) user.get("age");
        interests = (List) user.get("interests");
        assert name.equals("jim");
        assert age == 65;
        assert interests.size() == 2;
        assert interests.get(0).equals("Cobol");
        assert interests.get(1).equals("RPG"); 


        println "=== done ==="
    } // end of main
        
} // end of class    
