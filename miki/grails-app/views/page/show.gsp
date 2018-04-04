<%@page defaultCodec="text/plain" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${name}</title>
    <style>
        u {text-decoration: underline;}
    </style>
</head>
<body>
<a href="${createLink(controller:'find', action:'list', params:[term: name])}"><h1>${name}</h1></a>
<pre width="100%">${content}</pre>

<hr style="margin-top:20px">
| [Ctrl][Alt]
<a href="${createLink(controller:name, action:'edit')}" accesskey="e"><u>e</u>dit</a> |
<a href="${createLink(controller:name, action:'open')}" accesskey="f"><u>f</u>inder</a> |
<a href="${createLink(controller:'event', action:'list')}" accesskey="v">e<u>v</u>ents</a> |
<g:form method="GET" url="[controller:'find', action:'list']">
    <input type="text" value="" name="term"><input type="submit" value="find">
</g:form>
</body>
</html>
