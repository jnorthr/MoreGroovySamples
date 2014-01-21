println """
<html>
    <head>
        <title>Example Groovy Servlet</title>
    </head>
    <body>
<div>
<p>The full request looks like this:
<pre>${request}
</pre>
</p>
</div>
Hello from Groovy, ${request.remoteHost}: ${new Date()}
    </body>
</html>
"""
