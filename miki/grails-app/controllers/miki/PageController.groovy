package miki

import grails.util.GrailsConfig

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
        def editor = "${GrailsConfig.miki.command.edit} ${contentFile.absolutePath}".execute()
        editor.waitForProcessOutput()
        redirect url: selfUrl
    }

    def open = {
        "${GrailsConfig.miki.command.open} ${cookService.pageDir}".execute()
        redirect url: selfUrl
    }

    String getSelfUrl() { "/${params.page.capitalize()}/index" }
}
