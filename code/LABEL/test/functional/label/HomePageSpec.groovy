package label

import geb.spock.GebSpec
import page.HomePage

class HomePageSpec extends GebSpec {
    def setup() {
    }

    def cleanup() {
    }

    void "Test the home page renders correctly"() {
        when:"I hit application default url"
        go '/LABEL/'

        then:"I should be on homepage"
        at HomePage
    }
}
