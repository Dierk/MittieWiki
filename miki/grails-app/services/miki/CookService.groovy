package miki

import grails.util.GrailsConfig

class CookService {

    static transactional = false

    static final String CAP_WORD = '[A-Z][a-z0-9]+'
    static final WIKI_WORD_PATTERN = ~/$CAP_WORD($CAP_WORD)+/

    static final URI_PATTERN = /[.a-zA-Z0-9\/\-@&?=:_;~+!#$%^*()\[\]{}]+/
    static final PROTOCOLS = /(http|https|mailto|ftp|file)/
    static final URL_PATTERN = /$PROTOCOLS:$URI_PATTERN/

    File pageDir

    List<String> listPageNames() {
        def dir = getPageDir()
        def pageFiles = dir.listFiles().grep {it.name.endsWith('.wiki')}
        pageFiles.collect { it.name - '.wiki'}
    }

    String cookPage(String page) {
        return cook(getRawText(page))
    }

    String cook(String rawContent) {
        String result = rawContent.replaceAll(WIKI_WORD_PATTERN) { name, rest ->
            isKnown(name) ? "<a href='/miki/$name'>$name</a>" : name
        }
        result = result.replaceAll(URL_PATTERN) { match, rest ->
            "<a href='$match'>$match</a>"
        }

        return result
    }

    File getRawFile(String page) {
        new File(getPageDir(), page + '.wiki')
    }

    String getRawText(String page) {
        getRawFile(page).getText("ISO-8859-1")
    }

    boolean isKnown(String page) {
        getRawFile(page).exists()
    }

    // allow overwrite from unit test
    File getPageDir() {
        if (!pageDir) pageDir = new File(GrailsConfig.miki.pages.dir)
        return pageDir
    }
}
