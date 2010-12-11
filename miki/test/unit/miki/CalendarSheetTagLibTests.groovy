package miki

class CalendarSheetTagLibTests extends GroovyTestCase {

    void testSheet() {
        given:
        def lib = new CalendarSheetTagLib()
        def out = new StringWriter()
        lib.out = new PrintWriter(out)
        when:
        lib.sheet([:]){ it }
        then:
        def result      = out.buffer.toString()
        def todayCount  = (result =~ /today/).count
        def workdays    = (result =~ /workday/).count
        def weekendDays = (result =~ /weekend/).count
        assert (result =~ /<tr>/).count == 4 + 1        // 4 weeks plus header row
        assert todayCount == 1
        assert todayCount + workdays + weekendDays == 4 * 7
    }

}