package miki

class EventController {

    def cookService

    def index = { redirect action: list }

    def list = {
        def result = new LinkedList()
        for (page in cookService.listPageNames()) {
            def text = cookService.getRawText(page)
            text.eachMatch(~/(\d\d\.\d\d\.\d\d)(.*)/) { match, date, note ->
                def day = Date.parse('dd.MM.yy', date)
                result << [page:page , date: day, note: cookService.cook(note)]
            }
        }
        [events: result.sort{it.date}.reverse()]
    }
}
