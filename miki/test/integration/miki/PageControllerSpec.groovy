package miki

import grails.plugin.spock.ControllerSpec

class PageControllerSpec extends ControllerSpec {

    def "index action dispatches to view based on availability of the page"() {
        given:
        PageController controller = new PageController()

        when:
        controller.params.page = page
        controller.index()

        then:
        controller.modelAndView.viewName == view
        controller.modelAndView.model.linkedHashMap.name == page

        where:
        page            | view
        'SimplePage'    | 'show'
        'NoSuchPage'    | 'create'
    }

}
