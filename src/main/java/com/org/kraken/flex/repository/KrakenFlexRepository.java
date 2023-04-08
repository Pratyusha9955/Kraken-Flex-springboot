package com.org.kraken.flex.repository;

import java.util.List;

import com.org.kraken.flex.model.Outage;
import com.org.kraken.flex.model.SiteInfo;
import com.org.kraken.flex.model.SiteOutage;

public interface KrakenFlexRepository {

	public List<Outage> getOutages();

	public SiteInfo getOutagesBySiteId(String siteId);

	public void createOutagesBySiteId(String siteId, List<SiteOutage> siteOutages);

}
