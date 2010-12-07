class UrlMappings {

	static mappings = {

        "/event/$action" (controller: 'event')
        "/find/$action"  (controller: 'find')
    	"/" {
            controller = 'page'
            page = "FrontPage"
        }

        // all other requests go to the page controller, which dispatches to the page
		"/$page/$action?"(controller: 'page')

		"500"(view:'/error')
	}
}
