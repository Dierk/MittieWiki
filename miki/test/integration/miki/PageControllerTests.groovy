package miki

import grails.test.*

class PageControllerTests extends ControllerUnitTestCase {

    void testPageAvailable() {
        assertPageParamDispatchesToView 'SimplePage', 'show'
    }

    void testPageNotAvailable() {
        assertPageParamDispatchesToView 'NoSuchPage','create'
    }

    def assertPageParamDispatchesToView(page,view) {
        PageController controller = new PageController()
        controller.params.page = page
        controller.index()
        assert controller.modelAndView.viewName == view
        assert controller.modelAndView.model.linkedHashMap.name == page
    }
}
