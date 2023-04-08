package com.org.kraken.flex.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.kraken.flex.model.Outage;
import com.org.kraken.flex.model.RemainingOutage;
import com.org.kraken.flex.model.SiteInfo;
import com.org.kraken.flex.model.SiteOutage;
import com.org.kraken.flex.service.KrakenFlexService;

@RestController
public class KrakenFlexController {

	private static Logger logger = LoggerFactory.getLogger(KrakenFlexController.class);

	@Autowired
	private KrakenFlexService krakenFlexService;

	@GetMapping(value = "/outages", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getOutages(@RequestParam(required = false) String filter) {
		// OutageResponse<List<Outage>> response = new OutageResponse<>();
		try {
			List<Outage> response = krakenFlexService.getOutages();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
			final Date filterDate = dateFormatter.parse("2022-01-01T00:00:00.000Z");
			if (filter != null && filter.equalsIgnoreCase("date")) {
				List<Outage> filterdResponse = response.stream().filter(outage -> {
					try {
						return dateFormatter.parse(outage.getBegin()).before(filterDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return false;
				}).collect(Collectors.toList());
				logger.info("get outages from get api with date filter {}", filterdResponse);
				return new ResponseEntity<>(filterdResponse, HttpStatus.OK);
			} else if (filter != null && filter.equalsIgnoreCase("noId")) {
				SiteInfo output = krakenFlexService.getOutagesBySiteId("norwich-pear-tree");
				List<String> listOfdeviceId = output.getDevices().stream().map(SiteInfo::getId)
						.collect(Collectors.toList());
				List<Outage> filterNoIdOutages = response.stream().filter(out -> !listOfdeviceId.contains(out.getId()))
						.collect(Collectors.toList());
				logger.info("get outages with noid in siteinfo get api {}", response);
				return new ResponseEntity<>(filterNoIdOutages, HttpStatus.OK);
			} else if (filter != null && filter.equalsIgnoreCase("remainingoutages")) {
				List<Outage> filterdResponse = response.stream().filter(outage -> {
					try {
						return dateFormatter.parse(outage.getBegin()).after(filterDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return false;
				}).collect(Collectors.toList());
				SiteInfo output = krakenFlexService.getOutagesBySiteId("norwich-pear-tree");
				Map<String, String> deviceMap = output.getDevices().stream()
						.collect(Collectors.toMap(SiteInfo::getId, SiteInfo::getName));
				List<RemainingOutage> res = filterdResponse.stream().map(outage -> {
					RemainingOutage remOut = new RemainingOutage();
					remOut.setBegin(outage.getBegin());
					remOut.setEnd(outage.getEnd());
					remOut.setId(outage.getId());
					remOut.setDisplayName(deviceMap.get(outage.getId()));
					return remOut;
				}).collect(Collectors.toList());
				logger.info("get outages from remaining outages {}", res);
				return new ResponseEntity<>(res, HttpStatus.OK);
			} else {
				logger.info("get outages from get api {}", response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping(value = "/site-info/{siteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SiteInfo> getOutagesBySiteId(@PathVariable String siteId) {
		SiteInfo response = krakenFlexService.getOutagesBySiteId(siteId);
		logger.info("get outages by siteid {} from get api {}", siteId, response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/site-outages/{siteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SiteInfo> createOutagesBySiteId(@PathVariable String siteId,
			@RequestBody List<SiteOutage> siteOutages) {
		krakenFlexService.createOutagesBySiteId(siteId, siteOutages);
		logger.info("site outages created successfully for site id {}", siteId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
