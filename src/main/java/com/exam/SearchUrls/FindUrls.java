package com.exam.SearchUrls;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@SuppressWarnings("deprecation")
public class FindUrls {

	private int urlsFound = 0;
	private List<String> allUrls = new ArrayList<String>();
	private List<String> allErrors = new ArrayList<String>();

	public List<String> getAllUrls() {
		return allUrls;
	}

	public List<String> getAllErrors() {
		return allErrors;
	}

	public void FindUrlsRecursively(String Url) {

		try {

			Document doc = Jsoup.connect(Url).get();

			Elements allLinks = doc.getElementsByTag("a");
			allLinks.forEach(link -> {

				if (urlsFound == 50) {
					return;
				}

				String urlValue = link.attr("href");
				if (!allUrls.contains(urlValue) && IsUrlValid(urlValue)) {
					allUrls.add(urlValue);
					urlsFound++;
					FindUrlsRecursively(urlValue);
				}
			});

		} catch (Exception e) {
			allErrors.add(e.getMessage());
		}
	}

	private Boolean IsUrlValid(String Url) {
		String[] schemes = {"http","https"};
		UrlValidator urlValidator = new UrlValidator(schemes);
		return urlValidator.isValid(Url); 
	}
}
