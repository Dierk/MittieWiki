package miki

import grails.util.GrailsConfig

class PageController {

    def cookService

    def index = {
        def pageDir = new File(GrailsConfig.miki.pages.dir)
        def contentFile = new File(pageDir, params.page + '.wiki')
        if (contentFile.exists()) {
            def text = cookService.cook(contentFile.text)
            render view: 'show', model: [name: params.page, content: text]
        } else {
            render view: 'create', model: [name: params.page]
        }
    }
}
