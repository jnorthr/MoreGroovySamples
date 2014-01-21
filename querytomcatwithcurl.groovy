#!/usr/bin/env groovy

// This simple groovy script allows me now to track active tomcat sessions via munin by calling the manager app:
def env = System.getenv()

def user = env['user'] ?: 'jim'
def password = env['password'] ?: 'DefaultPasswordIfNoArgProvided'
def host = env['host'] ?: '127.0.0.1'
def port = env['ports'] ?: '80'   //'8080' for tomcat

def responsetext = "curl http://$user:$password@$host:$port ".execute().text
println "response : "+responsetext

if (this.args.size() > 0 && this.args[0] == 'config') {
    println 'graph_title Tomcat 2 sessions'
    println 'graph_category tomcat'
    responsetext.eachLine { line ->
    def parts = line.split(':')
        if (parts.size() > 1) {
            println parts[3] + '.label ' + parts[3]
        }
    }
    System.exit(1)
}

responsetext.eachLine { line ->
    def parts = line.split(':')
    if (parts.size() > 1) {
        println parts[3] + '.value ' + parts[2]
    }
}
println "=== done ==="