package gov.gsa.eOffer.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;

import gov.gsa.sst.eoffer.factory.PlaywrightFactory;
import gov.gsa.sst.eoffer.pages.eOfferHome;

public class BaseTest {

	
	PlaywrightFactory playwrightFactory;
	protected Page page;
	protected eOfferHome eofferHomePage;
	Properties prop;

	@BeforeTest
	public void setup() {

		playwrightFactory = new PlaywrightFactory();
		prop = playwrightFactory.init_prop();
		page = playwrightFactory.initBrowser(prop);
		eofferHomePage = new eOfferHome(page);
	}
	
	@AfterTest
	public void teardown() {

		page.context().browser().close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
