<html>
    <head>
        <meta name="layout" content="main" />
        <title>${name}</title>
    </head>
    <body>
    <h1>${name}</h1>
    <pre width="100%">${content}</pre>
    <hr style="margin-top:20px">|
    <a href="${name}/edit" accesskey="e">edit</a> |
    <a href="${name}/open" accesskey="o">finder</a> |
    <a href="${createLink(url:'events')}">events</a> |
    </body>
</html>
