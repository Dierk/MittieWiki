package miki

import grails.plugin.spock.UnitSpec

class CookServiceSpec extends UnitSpec {

    CookService service = new CookService(pageDirCache: new File("test/pages"))

    def "content that contains no valid reference should be rendered without anchors"(raw) {
        expect:
        service.cook(raw){it} == raw

        where:
        raw << ['', 'lowercase', 'Nocamelhump', 'ProperPatternButNoSuchPage']
    }

    def "render proper urls as anchors"(raw) {
        expect:
        service.cook(raw){it} =~ /href='${raw.trim()}'/

        where:
        raw << ['http://canoo.com', 'file:///data.txt ', 'https://-_./']
    }

    def "content that points to a wiki page should be rendered with an anchor"(raw) {
        expect:
        service.cook(raw){it} =~ /href='.*$raw'/

        where:
        raw << ['AaAa', 'AaAaAa', 'Y10M12D04']
    }

}
