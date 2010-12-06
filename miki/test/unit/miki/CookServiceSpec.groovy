package miki

import grails.plugin.spock.UnitSpec

class CookServiceSpec extends UnitSpec {

    CookService service = new CookService(pageDir: new File('test/pages'))

    def "content that contains no valid reference should be rendered without anchors"() {
        expect:
        service.cook(raw) == raw

        where:
        raw << ['', 'lowercase', 'Nocamelhump', 'ProperPatternButNoSuchPage']
    }

    def "render proper urls as anchors"() {
        expect:
        service.cook(raw) =~ /href='${raw.trim()}'/

        where:
        raw << ['http://canoo.com', 'file:///data.txt ', 'https://-_./']
    }

    def "content that points to a wiki page should be rendered with an anchor"() {
        expect:
        service.cook(raw) =~ /href='.*$raw'/

        where:
        raw << ['AaAa', 'AaAaAa', 'Y10M12D04']
    }

}
