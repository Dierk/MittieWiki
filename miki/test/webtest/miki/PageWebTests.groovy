package miki

class PageWebTests extends grails.util.WebTest {

    void testSomething() {
        invoke 'SimplePage'
        verifyText 'without any markup'
    }

}