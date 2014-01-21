#!/usr/bin/env groovy

/* comments follow ->
In the example, we wrap the code to execute with a closure syntax. In this way, 
when we call evaluate we obtain a closure object. With this closure object 
we can modify its delegate (invocation context) and then call it.

Finally, if we wanted to execute the code using a specific invocation context
(something like Ruby's instance_eval), we just have to modify the delegate object:


http://eriwen.com/groovy/groovy-shell-scripts/ describes how to do it
add #!/usr/bin/env groovy to first line of hello.groovy script
then chmod +x hello.groovy
hello.groovy


so for this script:
RedApple:jim /Users/jim $ cd /Volumes/Data/dev/groovy/test2
RedApple:jim /Volumes/Data/dev/groovy/test2 $ chmod +x shellclosurecontext.groovy 
RedApple:jim /Volumes/Data/dev/groovy/test2 $ shellclosurecontext.groovy 
Hello
RedApple:jim /Volumes/Data/dev/groovy/test2 $ 
// needs permission to access /usr/local/bin/
sudo cp shellclosurecontext.groovy /usr/local/bin/ 

then from *any* command line, an exe groovy script will run like this:
RedApple:jim /Users/jim/Desktop/menustest $ shellclosurecontext.groovy
Caught: java.io.FileNotFoundException: say.script (No such file or directory) // cos it's still in ...test2 folder
    at shellclosurecontext.run(shellclosurecontext.groovy:18)

*/

class Context{
  def say(message){
    println "context version of say : "+message
  }
}

def scriptfilename = "say.script"
//If we have an argument use it
if (args) scriptfilename = args[0]

def shell = new GroovyShell()
def code = new File(scriptfilename).text
code = "{->${code}}"

def context = new Context()

def closure = shell.evaluate(code)
closure.delegate = context
closure()