package miki

class PageWebTests extends grails.util.WebTest {

    void testSimplePage() {
        invoke 'SimplePage'
        verifyText 'without any markup'
    }

    void testLinkedPage() {
        invoke 'LinkPage'
        clickLink 'SimplePage'
        verifyTitle 'SimplePage'
    }

    void testEventsPage() {
        File eventsFile = new File('test/pages/EventProvider.wiki')
        if (eventsFile.exists()) eventsFile.delete()
        eventsFile.withWriter('ISO-8859-1') { out ->
            out << new Date().format('dd.MM.yy') << " first date"
        }
        invoke 'SimplePage'
        clickLink 'events'
        verifyTitle 'Events'
        clickLink xpath:'//td[@class="today events"]//a[text()="EventProvider"]'
        verifyTitle 'EventProvider'
    }

}