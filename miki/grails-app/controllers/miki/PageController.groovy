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
        File contentFile = cookService.getPageFile(params.page)
        def p = "${GrailsConfig.miki.command.edit} ${contentFile.absolutePath}".execute()
        p.waitForProcessOutput()
        redirect action: index, params:params
    }

    def open = {
        "${GrailsConfig.miki.command.open} ${cookService.pageDir}".execute()
        redirect action: index, params:params
    }
}
