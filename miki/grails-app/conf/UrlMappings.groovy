class UrlMappings {

	static mappings = {

        "/events"       (controller: 'event', action:'list')
        "/find/$action" (controller: 'find',  action:'list')
    	"/" {
            controller = 'page'
            page = "FrontPage"
        }

        // all other requests go to the page controller, which dispatches to the page
		"/$page/$action?"(controller: 'page')

		"500"(view:'/error')
	}
}
