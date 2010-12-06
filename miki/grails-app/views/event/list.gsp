<html>
    <head>
        <meta name="layout" content="main" />
        <title>Events</title>
    </head>
    <body>
    <h1>Events</h1>

    <g:sheet events="${events}" var="event">
        <a href="${event.page}" title="${event.note}">${event.page}</a>
    </g:sheet>

    <table style="margin-top:40px;font-size:10px;">
        <g:each in="${events}" var="event">
            <tr><td nowrap valign="top">${event.date.format('EEE dd.MM.yy')}</td>
                <td valign="top"><a href="${event.page}">${event.page}</a> </td>
                <td>${event.note}</td>
            </tr>
        </g:each>
    </table>
    </body>
</html>
