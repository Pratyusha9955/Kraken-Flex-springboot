package com.org.kraken.flex.repositoryImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.kraken.flex.model.Outage;
import com.org.kraken.flex.model.SiteInfo;
import com.org.kraken.flex.model.SiteOutage;
import com.org.kraken.flex.repository.KrakenFlexRepository;

@Repository
public class KrakenFlexRepositoryImpl implements KrakenFlexRepository {

	@Value("${aplicartion.url}")
	private String apiUrl;

	@Value("${api.key}")
	private String apiKey;

	private final static String apiHeader = "x-api-key";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Outage> getOutages() {
		String url = apiUrl + "outages";
		ResponseEntity<Outage[]> apiResponse = restTemplate.exchange(url, HttpMethod.GET, getEntity("body"),
				Outage[].class);
		return Arrays.asList(apiResponse.getBody());
	}

	@Override
	public SiteInfo getOutagesBySiteId(String siteId) {
		String url = apiUrl + "site-info/" + siteId;
		ResponseEntity<SiteInfo> apiResponse = restTemplate.exchange(url, HttpMethod.GET, getEntity("body"),
				SiteInfo.class);
		return apiResponse.getBody();
	}

	@Override
	public void createOutagesBySiteId(String siteId, List<SiteOutage> siteOutages) {
		String url = apiUrl + "site-outages/" + siteId;
		ObjectMapper obj = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = obj.writeValueAsString(siteOutages);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		ResponseEntity<String> apiResponse = restTemplate.postForEntity(url, getEntity(jsonString), String.class);

	}

	private HttpEntity<Object> getEntity(Object obj) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(apiHeader, apiKey);
		HttpEntity<Object> entity = new HttpEntity<>(obj, headers);
		return entity;
	}

}
