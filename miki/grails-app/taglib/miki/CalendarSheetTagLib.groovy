package miki

import groovy.xml.MarkupBuilder

class CalendarSheetTagLib {

    static Date firstDayInWeek(Date day) {
        day.clearTime()
        return day - day.calendarDate.dayOfWeek
    }

    def sheet = { attributes, body ->
        def var = attributes.var ?: "event"
        def today = new Date()
        def anchorDay = firstDayInWeek(today)
        def calEvents = attributes.events.grep { it.date in (anchorDay - 7 .. anchorDay + 21) }
        def html = new MarkupBuilder(out)
        html.table(cellspacing:'1px') {
            tr {
                for (header in 'Sat Sun Mon Tue Wed Thu Fri'.split()) {
                    th 'class':'events', header
                }
            }
            tbody {
                for (weekOffset in [-7, 0, 7, 14]) {
                    tr {
                        for (dayOffset in 0..6) {
                            def date = anchorDay + weekOffset + dayOffset
                            def style = (date == today) ? 'today' : (dayOffset < 2) ? 'weekend' : 'workday'
                            td('class':"$style events") {
                                p 'class':'events', date.format('dd.MM.yy')
                                def dayEvents = calEvents.grep { evt-> evt.date == date }
                                for (event in dayEvents) {
                                    out << body((var):event)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
