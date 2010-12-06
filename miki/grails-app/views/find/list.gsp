<html>
    <head>
        <meta name="layout" content="main" />
        <title>Links to ${term}</title>
    </head>
    <body>
    <h1>Links to ${term}</h1>

    <table cellspacing="12px" style="margin-top:20px;font-size:12px">
        <g:each in="${mentions}" var="event">
            <tr>
                <td valign="top"><a href="../${event.page}">${event.page}</a> </td>
                <td valign="top">${event.note}</td>
            </tr>
        </g:each>
    </table>
    </body>
</html>
