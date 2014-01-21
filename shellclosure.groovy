#!/usr/bin/env groovy
/*

http://eriwen.com/groovy/groovy-shell-scripts/  has details
chmod +x shellclosure.groovy
sudo cp shellclosure.groovy /usr/local/bin  // makes this script available on command line in any dir

don't forget that you can easily execute Groovy one-liners with: groovy -e "groovycode"
50.times{ new File(basedir/newDir${it+1}).mkdir() }

*/

def say(message){
  println message
}

def shell = new GroovyShell()
def code = '5.times{ new File("./newDir${it+1}").mkdir() }'

// now turn code fragment into a closure
code = "{->${code}}"

// now evaluate & execute
def closure = shell.evaluate(code)
closure.delegate=this
closure()