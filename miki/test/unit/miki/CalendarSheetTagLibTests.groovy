package miki

import grails.test.mixin.TestFor

@TestFor(CalendarSheetTagLib)
class CalendarSheetTagLibTests {

    void testSheetTag() {
        when:
        def result = applyTemplate('<g:sheet/>')
        then:
        def todayCount  = (result =~ /today/).count
        def workdays    = (result =~ /workday/).count
        def weekendDays = (result =~ /weekend/).count
        assert (result =~ /<tr>/).count == 4 + 1        // 4 weeks plus header row
        assert todayCount == 1
        assert todayCount + workdays + weekendDays == 4 * 7
    }

}