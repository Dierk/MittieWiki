package miki

class PageController {

    def cookService

    Closure pageRef = {target -> createLink(controller: target, action:'') }

    def index = {
        def page = params.page
        if (cookService.isKnown(page)) {
            def text = cookService.cookPage(page, pageRef)
            render view: 'show', model: [name: page, content: text]
        } else {
            render view: 'create', model: [name: page]
        }
    }

    def edit = {
        File contentFile = cookService.getRawFile(params.page)
        def p = "mate -w $contentFile.absolutePath".execute()
        p.waitForProcessOutput()
        redirect action: index
    }

    def open = {
        "open ${cookService.getPageDir()}".execute()
        redirect action: index
    }
}
