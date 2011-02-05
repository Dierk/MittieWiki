package miki

class EventController {

    CookService cookService

    Closure pageRef = {target -> createLink(controller: target, action:'') }

    def list = {
        def events = new LinkedList()
        for (page in cookService.listPageNames()) {
            def text = cookService.getRawText(page)
            text.eachMatch(~/(\d\d\.\d\d\.\d\d)(.*)/) { match, date, note ->
                def day = Date.parse('dd.MM.yy', date)
                events << [
                        page: page,
                        date: day,
                        note: cookService.cook(note, pageRef)
                ]
            }
        }
        [events: events]
    }
}
