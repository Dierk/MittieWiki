class UrlMappings {

	static mappings = {

        // all requests go to the page controller, which dispatches to the page
		"/$page/$action?"(controller: 'page')

		"/" {
            controller = 'page'
            page = "FrontPage"
        }

        "/events"(controller: 'event', action:'list')
        "/find/$term"(controller: 'find', action:'list')

		"500"(view:'/error')
	}
}
