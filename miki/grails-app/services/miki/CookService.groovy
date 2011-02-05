package miki

import grails.util.GrailsConfig

class CookService {

    static transactional = false

    static final String CAP_WORD    = '[A-Z][a-z0-9]+'
    static final WIKI_WORD_PATTERN  = ~/$CAP_WORD($CAP_WORD)+/
    static final URI_PATTERN        = /[.a-zA-Z0-9\/\-@&?=:_;~+!#$%^*()\[\]{}]+/
    static final PROTOCOLS          = /(http|https|mailto|ftp|file)/
    static final URL_PATTERN        = ~/$PROTOCOLS:$URI_PATTERN/

    List<String> listPageNames() {
        def pageFiles = pageDir.listFiles().grep { it.name.endsWith('.wiki') }
        pageFiles.collect { it.name - '.wiki' }
    }

    String cookPage(String page, Closure pageRef) {
        cook getRawText(page), pageRef
    }

    /**
     * @param pageRef Strategy on how to make a proper href to a wiki page
     */
    String cook(String rawContent, Closure pageRef) {
        String result = rawContent.replaceAll(WIKI_WORD_PATTERN) { name, rest ->
            isKnown(name) ? "<a href='${pageRef(name)}'>$name</a>" : name
        }
        result = result.replaceAll(URL_PATTERN) { match, rest ->
            "<a href='$match'>$match</a>"
        }
        return result
    }

    File getPageFile(String page) {
        new File(pageDir, page + '.wiki')
    }

    String getRawText(String page) {
        getPageFile(page).getText("ISO-8859-1")
    }

    boolean isKnown(String page) {
        getPageFile(page).exists()
    }

    File pageDirCache // allow override in unit tests (not integration test)
    File getPageDir() {
        if (! pageDirCache) pageDirCache = new File(GrailsConfig.miki.pages.dir)
        pageDirCache
    }
}
