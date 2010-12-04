package miki

import grails.plugin.spock.UnitSpec

class CookServiceSpec extends UnitSpec {

    def "content that contains no wiki word should be rendered without anchors"() {
        expect:
        new CookService().cook(raw) == raw

        where:
        raw << ['', 'nolink', 'Nolink', 'Aa']
    }

    def "content that contains a wiki word like should be rendered with an anchor"() {
        expect:
        new CookService().cook(raw) =~ /href='$raw'/

        where:
        raw << ['AaAa', 'AaAaAa', 'Y10M12D04']
    }

}
