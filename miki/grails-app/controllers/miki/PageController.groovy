package miki

import grails.util.GrailsConfig

class PageController {

    def index = {
        def pageDir = new File(GrailsConfig.miki.pages.dir)
        def contentFile = new File(pageDir, params.page + '.wiki')
        if (contentFile.exists()) {
            render view: 'show', model: [name: params.page, content: contentFile.text]
        } else {
            render view: 'create', model: [name: params.page]
        }
    }
}
