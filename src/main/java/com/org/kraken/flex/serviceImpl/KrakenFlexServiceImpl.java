package com.org.kraken.flex.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.kraken.flex.model.Outage;
import com.org.kraken.flex.model.SiteInfo;
import com.org.kraken.flex.model.SiteOutage;
import com.org.kraken.flex.repository.KrakenFlexRepository;
import com.org.kraken.flex.service.KrakenFlexService;

@Service
public class KrakenFlexServiceImpl implements KrakenFlexService {

	@Autowired
	private KrakenFlexRepository krakenFlexRepository;

	@Override
	public List<Outage> getOutages() {
		return krakenFlexRepository.getOutages();
	}

	@Override
	public SiteInfo getOutagesBySiteId(String siteId) {
		return krakenFlexRepository.getOutagesBySiteId(siteId);
	}

	@Override
	public void createOutagesBySiteId(String siteId, List<SiteOutage> siteOutages) {
		krakenFlexRepository.createOutagesBySiteId(siteId, siteOutages);

	}

}
