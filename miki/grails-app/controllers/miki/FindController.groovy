package miki

class FindController {

    CookService cookService

    def list(String term) {
        def result = new LinkedList()
        for (page in cookService.listPageNames()) {
            if(page.contains(term)) {
                result << [page:page, note: 'The page itself, by title']
            }
            def text = cookService.getRawText(page)
            text.eachMatch(~/.*${term}.*/) { match ->
                result << [page: page, note: cookService.cook(match)]
            }
        }
        [term: term, mentions: result.sort{it.page}]
    }
}
