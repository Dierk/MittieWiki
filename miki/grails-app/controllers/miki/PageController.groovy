package miki

class PageController {

    CookService cookService

    def index = {
        def page = params.page.capitalize()
        if (cookService.isKnown(page)) {
            def text = cookService.cookPage(page)
            render view: 'show', model: [name: page, content: text]
        } else {
            render view: 'create', model: [name: page]
        }
    }

    def edit = {
        File contentFile = cookService.getPageFile(params.page.capitalize())
        def editor = "${grailsApplication.config.miki.command.edit} ${contentFile.absolutePath}".execute()
        editor.waitForProcessOutput()
        redirect url: selfUrl
    }

    def open = {
        "${grailsApplication.config.miki.command.open} ${cookService.pageDir}".execute()
        redirect url: selfUrl
    }

    String getSelfUrl() { "/${params.page.capitalize()}/index" }
}
