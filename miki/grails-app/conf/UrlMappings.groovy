class UrlMappings {

	static mappings = {

        // all requests go to the page controller, which dispatches to the page
		"/$page/edit"(controller: 'page', action: 'edit')
		"/$page"(controller: 'page')

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
