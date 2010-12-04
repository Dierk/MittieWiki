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

}