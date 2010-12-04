package miki

import grails.test.*

class CookServiceTests extends GrailsUnitTestCase {

    void testNoLinks() {
        assertCooked '',''
        assertCooked 'nolink','nolink'
        assertCooked 'Nolink','Nolink'
        assertCooked 'Aa','Aa'
    }
    void testLinks() {
        assertCookedHref 'AaAa','AaAa'
        assertCookedHref 'AaAaAa','AaAaAa'
        assertCookedHref 'Y10M12D04','Y10M12D04'
    }

    def assertCooked(raw, cooked) {
        assert new CookService().cook(raw) == cooked
    }
    def assertCookedHref(raw, href) {
        assert new CookService().cook(raw) =~ /href='$href'/
    }
}
