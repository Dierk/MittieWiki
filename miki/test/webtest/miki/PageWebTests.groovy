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

    void testTagging() {
        invoke 'SimplePage'
        clickLink 'SimplePage'
        verifyTitle 'Links to SimplePage'
        clickLink 'LinkPage'
    }

    void testEventsPage() {
        File eventsFile = new File('test/pages/EventProvider.wiki')
        if (eventsFile.exists()) eventsFile.delete()
        eventsFile.withWriter('ISO-8859-1') { out ->
            def today = new Date().format('dd.MM.yy')
            out << today << " reference to SimplePage as wiki link\n"
            out << today << " another reference for sorting\n"
        }
        invoke 'SimplePage'
        clickLink 'events'
        verifyTitle 'Events'
        clickLink xpath:'//td[@class="today events"]//a[text()="EventProvider"]'
        verifyTitle 'EventProvider'
    }

    void testFindWithVariants() {
        group(description:'find term "Simple"') {
            invoke 'SimplePage'
            setInputField name: 'term', value: 'Simple'
            clickButton 'find'
        }
        verifyTitle 'Links to Simple'
        verifyText regex:true, 'some content with a link to .*SimplePage'
        group(description:'found pages and links work') {
            clickLink 'LinkPage'
            previousResponse()
            clickLink 'SimplePage'
        }
        group(description:'Backlinks are still working'){
            clickLink 'SimplePage'
            clickLink 'LinkPage'
        }
    }

}