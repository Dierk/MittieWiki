package miki

import grails.plugin.spock.ControllerSpec
import groovy.mock.interceptor.MockFor

class PageControllerSpec extends ControllerSpec {

    def "index action dispatches to view based on availability of the page"() {
        given: 'a page controller'
        PageController controller = new PageController()

        when: "page $page is requested"
        controller.params.page = page
        controller.index()

        then: "we should delegate to the $view view"
        controller.modelAndView.viewName == view
        controller.modelAndView.model.linkedHashMap.name == page

        where:
        page            | view
        'SimplePage'    | 'show'
        'NoSuchPage'    | 'create'
    }

    def "open action calls finder"() {
        given: 'a page controller'
        PageController controller = new PageController()
        def stringMock = new MockFor(String)
        stringMock.demand.execute(1..1) {->
            [waitForProcessOutput: {-> 0 }]
        }

        when: "open new page is requested"
        stringMock.use {
            controller.open()
        }

        then: "we should delegate to the index view"
        controller.response.status == 200

    }

    def "edit action calls editor"() {
        given: 'a page controller'
        PageController controller = new PageController()
        controller.cookService = new CookService()
        def stringMock = new MockFor(String)
        stringMock.demand.plus(1..1) { op -> op }
        stringMock.demand.execute(1..1) {->
            [waitForProcessOutput: {-> 0 }]
        }

        when: "open new page is requested"
        controller.params.page = "SimplePage"
        stringMock.use {
            controller.edit()
        }

        then: "we should delegate to the index view"
        controller.response.status == 200
    }

}
