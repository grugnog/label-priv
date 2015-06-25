/*
 This is the Geb configuration file.
 See: http://www.gebish.org/manual/current/configuration.html
 */


import org.openqa.selenium.*
import org.openqa.selenium.remote.*
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver


driver = {
	def driver =  new FirefoxDriver()	 	
	driver
}

waiting {
	timeout = 30
	retryInterval = 0.5	
}

environments {

	// Automatic driver download code adapted from
	// http://fbflex.wordpress.com/2013/01/06/geb-quickie-automatically-download-drivers-for-chrome-and-internet-explorer/
	environments {

		// run as "grails -Dgeb.env=chrome test-app functional:"
		// See: http://code.google.com/p/selenium/wiki/ChromeDriver
		chrome {
			String chromeDriverVersion = "2.16"

			String chromeDriverZipFileName
			String chromeDriverExecFileName

			if (Platform.current.is(Platform.WINDOWS)) {
				chromeDriverZipFileName = "chromedriver_win32.zip"
				chromeDriverExecFileName = "chromedriver.exe"
			} else if (Platform.current.is(Platform.MAC)) {
				chromeDriverZipFileName = "chromedriver_mac32.zip"
				chromeDriverExecFileName = "chromedriver"
			} else if (Platform.current.is(Platform.LINUX)) {
				chromeDriverZipFileName = "chromedriver_linux64.zip"
				chromeDriverExecFileName = "chromedriver"
			} else {
				throw new RuntimeException("Unsupported operating system [${Platform.current}]")
			}

			String chromeDriverDownloadFullPath = "http://chromedriver.storage.googleapis.com/${chromeDriverVersion}/${chromeDriverZipFileName}"

			File chromeDriverLocalFile = downloadDriver(chromeDriverDownloadFullPath, chromeDriverExecFileName, 'zip')

			System.setProperty('webdriver.chrome.driver', chromeDriverLocalFile.absolutePath)
			driver = { new ChromeDriver() }
		}

		ff {			
			driver = { new FirefoxDriver() }
		}

		// run as "grails -Dgeb.env=ie test-app functional:"
		// See: https://code.google.com/p/selenium/wiki/InternetExplorerDriver
		ie {
			String ieDriverVersion = "2.46.0"
			String ieDriverVersionMajor = ieDriverVersion.substring(0, ieDriverVersion.lastIndexOf('.'))

			String ieDriverZipFileName = "IEDriverServer_Win32_${ieDriverVersion}.zip"

			String ieDriverDownloadFullPath = "http://selenium-release.storage.googleapis.com/${ieDriverVersionMajor}/${ieDriverZipFileName}"

			File ieDriverLocalFile = downloadDriver(ieDriverDownloadFullPath, "IEDriverServer.exe", 'zip')

			System.setProperty('webdriver.ie.driver', ieDriverLocalFile.absolutePath)
			driver = {
					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();				
					caps.setCapability("ignoreProtectedModeSettings", true);
					caps.setCapability("ignoreZoomSetting", true);
					new InternetExplorerDriver(caps);				 
			}
		}
	
	}
}

private File downloadDriver(String driverDownloadFullPath, String driverFilePath, String archiveFileExtension) {
	File destinationDirectory = new File("target/drivers")
	if (!destinationDirectory.exists()) {
		destinationDirectory.mkdirs()
	}

	File driverFile = new File("${destinationDirectory.absolutePath}/${driverFilePath}")

	String localArchivePath = "target/driver.${archiveFileExtension}"

	if (!driverFile.exists()) {
		def ant = new AntBuilder()
		ant.get(src: driverDownloadFullPath, dest: localArchivePath)

		if (archiveFileExtension == "zip") {
			ant.unzip(src: localArchivePath, dest: destinationDirectory)
		} else {
			ant.untar(src: localArchivePath, dest: destinationDirectory, compression: 'bzip2')
		}

		ant.delete(file: localArchivePath)
		ant.chmod(file: driverFile, perm: '700')
	}

	return driverFile
}
