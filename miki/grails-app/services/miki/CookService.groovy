package miki

import grails.util.GrailsConfig

class CookService {

    static transactional = false

    final String CAP_WORD = '[A-Z][a-z0-9]+'
    final def WIKI_WORD_PATTERN = ~/$CAP_WORD($CAP_WORD)+/
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
