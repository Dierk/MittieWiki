<html>
<head>
    <meta name="layout" content="main"/>
    <title>${name}</title>
</head>
<body>
<a href="${createLink(controller:'find', action:'index', params:[term: name])}"><h1>${name}</h1></a>
<pre width="100%">${content}</pre>

<hr style="margin-top:20px">
<FORM METHOD="GET" ACTION="${createLink(controller:'find', action:'index')}">|
    <a href="${name}/edit" accesskey="e">edit</a> |
    <a href="${name}/open" accesskey="o">finder</a> |
    <a href="${createLink(url: 'events')}">events</a> |
    <input type="text" value="" name="term"><input type="submit" value="find">
</FORM>
</body>
</html>
