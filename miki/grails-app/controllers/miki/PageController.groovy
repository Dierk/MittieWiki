package miki

import grails.util.GrailsConfig

class PageController {

    CookService cookService

    def index = {
        def page = params.page[0].toUpperCase() + params.page[1..-1]
        if (cookService.isKnown(page)) {
            def text = cookService.cookPage(page)
            render view: 'show', model: [name: page, content: text]
        } else {
            render view: 'create', model: [name: page]
        }
    }

    def edit = {
        File contentFile = cookService.getPageFile(capitalize(params.page))
        def editor = "${GrailsConfig.miki.command.edit} ${contentFile.absolutePath}".execute()
        editor.waitForProcessOutput()
        redirect url: selfUrl
    }

    def open = {
        "${GrailsConfig.miki.command.open} ${cookService.pageDir}".execute()
        redirect url: selfUrl
    }
    
    String capitalize(String lower) { lower[0].toUpperCase() + lower[1..-1] }
    
    String getSelfUrl() { "/${capitalize params.page}/index" }
}
