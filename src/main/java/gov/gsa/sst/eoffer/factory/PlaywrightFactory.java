package gov.gsa.sst.eoffer.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;

	Properties prop;

	public Page initBrowser(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser name is :" + browserName);

		playwright = Playwright.create();

		switch (browserName.toLowerCase()) {
		case "chromium":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "safari":
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "chrome":
			browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			break;

		default:
			System.out.println("Please pass the correct browser name.");
			break;
		}

		browserContext = browser.newContext();
		page = browserContext.newPage();
		page.navigate(prop.getProperty("url"));

		return page;
	}

	public Properties init_prop() {

		try {

			FileInputStream inputStream = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			try {
				prop.load(inputStream);
			} catch (IOException e) {

				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return prop;
	}

}
