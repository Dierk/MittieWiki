<html>
    <head>
        <meta name="layout" content="main" />
        <title>Events</title>
    </head>
    <body>
    <h1>Events</h1>

    <g:sheet events="${events}" var="event">
        <a href="${createLink(controller:event.page, action:'')}" title="${event.note}">${event.page}</a>
    </g:sheet>

    <h1>Upcoming:</h1>
    <table style="font-size:10px;">
        <% def today = new Date()%>
        <g:each in="${ events.grep{ it.date >= today }.sort{ it.date } }" var="event">
            <tr><td nowrap valign="top"><tt>${event.date.format('EEE dd.MM.yy')}</tt></td>
                <td valign="top"><a href="${createLink(controller:event.page, action:'')}">${event.page}</a> </td>
                <td>${event.note}</td>
            </tr>
        </g:each>
    </table>
    </body>
</html>
