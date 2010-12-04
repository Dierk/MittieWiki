package miki

class CookService {
    final String CAP_WORD = '[A-Z][a-z0-9]+'
    final def WIKI_WORD_PATTERN = ~/$CAP_WORD($CAP_WORD)+/

    String cook(String rawContent) {
        String result = rawContent.replaceAll(WIKI_WORD_PATTERN) { name, rest ->
            "<a href='$name'>$name</a>"
        }
        return result
    }
}
