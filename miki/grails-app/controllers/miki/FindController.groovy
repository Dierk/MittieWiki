package miki

class FindController {

    def cookService

    def index = { redirect action: list }

    def list = {
        def result = new LinkedList()
        for (page in cookService.listPageNames()) {
            def text = cookService.getRawText(page)
            text.eachMatch(~/(${params.term})(.*)/) { match, term, note ->
                result << [page:page,  note: cookService.cook(match)]
            }
        }
        [term: params.term, mentions: result.sort{it.page}]
    }
}
