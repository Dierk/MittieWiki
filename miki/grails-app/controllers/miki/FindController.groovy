package miki

class FindController {

    def cookService

    def index = { redirect action: list }

    Closure pageRef = {target -> createLink(controller: target, action:'') }

    def list = {
        def result = new LinkedList()
        for (page in cookService.listPageNames()) {
            def text = cookService.getRawText(page)
            text.eachMatch(~/.*(${params.term})(.*)/) { match, term, note ->
                result << [page: page, note: cookService.cook(match, pageRef)]
            }
        }
        [term: params.term, mentions: result.sort{it.page}]
    }
}
