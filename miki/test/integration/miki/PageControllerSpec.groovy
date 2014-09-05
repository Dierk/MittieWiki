package miki

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(PageController)
class PageControllerSpec extends Specification {

    def "index action dispatches to view based on availability of the page"(page, view) {
        given: 'a page controller'
        PageController controller = new PageController()

        when: "page $page is requested"
        controller.params.page = page
        controller.index()

        then: "we should delegate to the $view view"
        controller.modelAndView.viewName == view
        //controller.modelAndView.model.linkedHashMap.name == page
        // todo find out how to get onto this information and re-enable the assertion

        where:
        page            | view
        'SimplePage'    | '/page/show'
        'NoSuchPage'    | '/page/create'
    }

    def "open action calls finder"() {
        given: 'a page controller'
        PageController controller = new PageController()
        controller.params.page = "NoSuchPage"

        when: "open new page is requested"
        controller.open()

        then: "we should delegate to the index view"
        controller.response.status < 400
    }

    def "edit action calls editor"() {
        given: 'a page controller'
        PageController controller = new PageController()
        controller.cookService = new CookService(grailsApplication : controller.grailsApplication)

        when: "open new page is requested"
        controller.params.page = "SimplePage"
        controller.edit()

        then: "we should delegate to the index view"
        controller.response.status < 400
    }

}
