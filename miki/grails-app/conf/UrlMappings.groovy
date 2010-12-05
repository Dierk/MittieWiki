class UrlMappings {

	static mappings = {

        // all requests go to the page controller, which dispatches to the page
		"/$page/$action?"(controller: 'page')

		"/" {
            controller = 'page'
            page = "FrontPage"
        }
		"500"(view:'/error')
	}
}
