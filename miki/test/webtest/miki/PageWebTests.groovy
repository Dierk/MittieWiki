package miki

import java.lang.reflect.Method

class PageWebTests extends grails.util.WebTest {

    static def suite() {
        def self = new PageWebTests()

        boolean foundTest = false

        self.class.methods.sort {m -> m.name}.each {Method method ->
            def methodName = method.name
            if (methodName.startsWith('test') && self.shouldRunTest(methodName, self.class.name)) {
                if (!foundTest) {
                    self.webTestMethodIfExists("classSetUp")
                    foundTest = true
                }
                self.webTestMethod(method, true)
            }
        }
        if (foundTest) {
            self.webTestMethodIfExists("classTearDown")
        }
    }

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

    void testFindByPageTile() {
        def unReferencedPageName = 'AaAa'
        group(description:'find a page by title that referenced from no other page') {
            invoke 'SimplePage'
            setInputField name: 'term', value: unReferencedPageName
            clickButton 'find'
        }
        verifyTitle "Links to $unReferencedPageName"
        verifyText unReferencedPageName
        verifyText 'AaAa.*?page itself',  regex:true, description: 'full title match'
        verifyText 'AaAaAa.*?page itself',regex:true, description: 'page title submatch'
    }

}