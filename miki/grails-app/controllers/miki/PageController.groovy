package miki

import grails.util.GrailsConfig

class PageController {

    def cookService

    def index = {
        def page = params.page
        File contentFile = getRawFile(page)
        if (contentFile.exists()) {
            def raw = contentFile.getText("ISO-8859-1")
            def text = cookService.cook(raw)
            render view: 'show', model: [name: page, content: text]
        } else {
            render view: 'create', model: [name: page]
        }
    }

    File getRawFile(page) {
        def pageDir = new File(GrailsConfig.miki.pages.dir)
        def contentFile = new File(pageDir, page + '.wiki')
        return contentFile
    }

    def edit = {
        File contentFile = getRawFile(params.page)
        def p = "mate -w $contentFile.absolutePath".execute()
        p.waitForProcessOutput()
        redirect action: index, params:[page:params.page]
    }
}
