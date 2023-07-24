package gov.gsa.sst.eoffer.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


import com.microsoft.playwright.Page;

public class eOfferHome {

	private Page page;

	public eOfferHome(Page page) {
		this.page = page;
	}

	public String getHomePageTitle() {
		String title =  page.title();
		System.out.println("Page title: " + title);
		return title;
	}

	public String getHomePageURL() {
		String url =  page.url();
		System.out.println("Page URL: " + url);
		return url;
	}
	
	
	

//	public void verifyBrokenLinks(String linkURl) {
//		List<ElementHandle> links = page.querySelectorAll("a");
//		List<String> failedLinks = new ArrayList<>();
//
//		ExecutorService executor = Executors.newFixedThreadPool(10);
//
//		// Check links concurrently
//		List<Future<Boolean>> futures = new ArrayList<>();
//		for (ElementHandle link : links) {
//			Future<Boolean> future = executor.submit(() -> {
//				String href = link.getAttribute("href");
//				return isValidLink(href);
//			});
//			futures.add(future);
//		}
//
//		for (int i = 0; i < links.size(); i++) {
//			try {
//				boolean isValid = futures.get(i).get();
//				if (!isValid) {
//					failedLinks.add(links.get(i).getAttribute("href"));
//				}
//			} catch (InterruptedException | ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
//		executor.shutdown();
//		try {
//			executor.awaitTermination(10, TimeUnit.MINUTES);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		if (!failedLinks.isEmpty()) {
//			for (String failedUrl : failedLinks) {
//				System.out.println("Broken Link: " + failedUrl);
//			}
//		} else {
//			System.out.println("All links are valid");
//		}
//		
//
//		page.close();
//
//	}

	public boolean isValidLink(String linkUrl) {

		if (linkUrl == null || linkUrl.isEmpty()) {
			return false;
		}

		if (linkUrl.equals("tel:1-866 472-9114") || linkUrl.equals("mailto:eoffer@gsa.gov")) {
			System.out.println("Skipped link: " + linkUrl);
			return true;
		}

		try {
			URL url = new URL(linkUrl);

			// Handle relative links
			if (!url.getProtocol().startsWith("http")) {
				System.out.println("Relative link found: " + linkUrl);
				return true;
			}

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("HEAD");
			connection.setConnectTimeout(5000);

			try {
				int responseCode = connection.getResponseCode();
				if (responseCode >= 400) {
					System.out.println("Broken link: " + linkUrl + " - " + connection.getResponseMessage());
					return false;
				} else {
					System.out.println("Valid link: " + linkUrl + " - " + connection.getResponseMessage());
					return true;
				}
			} finally {
				connection.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	
}
