
import geb.spock.GebReportingSpec
import page.HomePage

/**
 * User: Janakiram Gollapudi
 * Date: 6/23/15
 */
class ShowHomePageSpec extends GebReportingSpec {

    def "Application default page should be label search page(i.e Home page)"() {
        when:
        go "http://localhost:8080/LABEL"

        then:
        to HomePage
    }
}
