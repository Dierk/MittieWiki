package miki

class EventController {

    def cookService
    def pageController

    Closure pageRef = {target -> createLink(controller: target, action:'') }

    def list = {
        def result = new LinkedList()
        for (page in cookService.listPageNames()) {
            def text = cookService.getRawText(page)
            text.eachMatch(~/(\d\d\.\d\d\.\d\d)(.*)/) { match, date, note ->
                def day = Date.parse('dd.MM.yy', date)
                result << [
                        page: page,
                        date: day,
                        note: cookService.cook(note, pageRef)
                ]
            }
        }
        def max = [20,result.size()].min()
        [events: result.sort{it.date}.reverse()[0..<max]]
    }
}
