package miki

import groovy.xml.MarkupBuilder

class CalendarSheetTagLib {
    def out

    def sheet = { attributes, body ->
        def var = attributes.var ?: "event"
        def day = new Date()
        day.clearTime()
        def firstDayInWeek = day - day.calendarDate.dayOfWeek
        def html = new MarkupBuilder(out)
        html.table(cellspacing:'1px') {
            tr {
                for (header in 'Sat Sun Mon Tue Wed Thu Fri'.split()) th 'class':'events', header
            }
            tbody {
                for (weekOffset in [-7, 0, 7, 14]) {
                    tr {
                        for (dayOffset in 0..6){
                            def date = firstDayInWeek + weekOffset + dayOffset
                            def style = (date == day) ? 'today' : (dayOffset < 2) ? 'weekend' : 'workday'
                            td('class':"$style events") {
                                p 'class':'events', date.format('dd.MM.yy')
                                def dayEvents = attributes.events.grep{evt-> evt.date == date }
                                for (event in dayEvents) out << body((var):event)
                            }
                        }
                    }
                }
            }
        }
    }

}
