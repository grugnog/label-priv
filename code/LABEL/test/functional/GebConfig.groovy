/**
 * User: Janakiram Gollapudi
 * Date: 6/23/15
 */

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.ie.InternetExplorerDriver

def driverInstance = new HtmlUnitDriver()
driver = {
    driver.javascriptEnabled = true
    driver
}


environments {

    // run as grails -Dgeb.env=chrome test-app
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        driverInstance = { new ChromeDriver() }
    }

    // run as grails -Dgeb.env=firefox test-app
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
       driverInstance = { new FirefoxDriver() }
    }

    ie {
        driverInstance = { new InternetExplorerDriver() }
    }

}

baseNavigatorWaiting = true
atCheckWaiting = true