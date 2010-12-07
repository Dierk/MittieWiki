<html>
<head>
    <meta name="layout" content="main"/>
    <title>${name}</title>
</head>
<body>
<a href="${createLink(controller:'find', action:'list', params:[term: name])}"><h1>${name}</h1></a>
<pre width="100%">${content}</pre>

<hr style="margin-top:20px">
<g:form method="GET" url="[controller:'find', action:'list']">|
    <a href="${createLink(controller:name, action:'edit')}" accesskey="e">edit</a> |
    <a href="${createLink(controller:name, action:'open')}" accesskey="o">finder</a> |
    <a href="${createLink(controller:'event', action:'list')}">events</a> |
    <input type="text" value="" name="term"><input type="submit" value="find">
</g:form>
</body>
</html>
