<html>
    <head>
        <title>Example Groovy Servlet 2 - example2.gtpl</title>
    </head>
    <body>

<div>
<p>The request version looks like this:
<pre>
<%= request.getAttribute('version') %>
</pre>
</p>
</div>

<div>
<p>The full request looks like this:
<pre>
${request}
</pre>
</p>
</div>


Hello from Groovy, ${request.remoteHost}: ${new Date()}
    </body>
</html>

