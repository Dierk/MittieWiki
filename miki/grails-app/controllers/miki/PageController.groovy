package miki

import grails.util.GrailsConfig

class PageController {

    CookService cookService

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
        def p = (GrailsConfig.miki.command.edit.toString() + ' '+ contentFile.absolutePath).execute()
        p.waitForProcessOutput()
        redirect action: index, params:params
    }

    def open = {
        (GrailsConfig.miki.command.open.toString() + ' ' + cookService.getPageDir()).execute()
        redirect action: index, params:params
    }
}
