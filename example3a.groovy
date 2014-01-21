
if (!session) {
  session = request.getSession(true)
}

if (!session.counter) {
      session.counter = 1
}

html.html {    // html is implicitly bound to new MarkupBuilder(out)
  head {
      title("Groovy Servlet")
  }
  body {
    p("Hello, ${request.remoteHost}: ${session.counter}! ${new Date()}")
    p("request="+ ${request}  )
    p("context="+ ${context}  )
    p("application="+ ${application}  )
    p("session="+ ${session}  )
    p("params="+ ${params}  )
    p("headers="+ ${headers}  )
  }
}
session.counter = session.counter + 1