package gov.gsa.sst.eoffer.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.microsoft.playwright.ElementHandle;

import gov.gsa.eOffer.base.BaseTest;

public class eOfferHomePageTest extends BaseTest {

	@Test
	public void eOfferHomePageTitleTest() {
		String actualTitle = eofferHomePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, "eOffer/eMod Home");
	}

	@Test
	public void eOfferHomePageURLTest() {
		String actualURL = eofferHomePage.getHomePageURL();
		Assert.assertEquals(actualURL, "https://eoffer-test.fas.gsa.gov/");
	}

	@Test
	public void verifyeOfferHomePageLinks() {

		List<ElementHandle> links = page.querySelectorAll("a");
		List<String> failedLinks = new ArrayList<>();
		System.out.println("No. of links are " + links.size());

		for (ElementHandle link : links) {
			String url = link.getAttribute("href");
			if (!eofferHomePage.isValidLink(url)) {
				failedLinks.add(url);
			}
		}

		// Asserting that there are no failed links
		assert failedLinks.isEmpty() : "Some links are broken.";

	}

	@Test
	public void selectPopupCheckbox() {
		ElementHandle checkboxElement = page.querySelector("input[id='ack']");
		ElementHandle ackKnowledgeBtn = page.querySelector("button[id='ackButton']");

		boolean isChecked = checkboxElement.isChecked();

		if (!isChecked) {
			checkboxElement.click();
		}

		ackKnowledgeBtn.click();
	}

}
