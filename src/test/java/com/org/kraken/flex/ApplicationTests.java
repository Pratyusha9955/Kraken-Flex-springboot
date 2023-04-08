package com.org.kraken.flex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.org.kraken.flex.controller.KrakenFlexController;
import com.org.kraken.flex.model.Outage;
import com.org.kraken.flex.model.SiteInfo;
import com.org.kraken.flex.model.SiteOutage;

@SpringBootTest
class ApplicationTests {

	@Autowired
	KrakenFlexController obj;

	@Test
	void getOutagesTest() {
		List<Outage> response = (List<Outage>) obj.getOutages("").getBody();
		assertTrue(response.size() > 1);
	}

	@Test
	void getOutagesStatusOkTest() {
		int code = obj.getOutages("").getStatusCode().value();
		assertEquals(200, code);
	}
	
	@Test
	void getOutagesfilterTest() {
		List<Outage> response = (List<Outage>) obj.getOutages("date").getBody();
		assertTrue(response.size() > 1);
	}

	@Test
	void getOutagesFilterStatusOkTest() {
		int code = obj.getOutages("date").getStatusCode().value();
		assertEquals(200, code);
	}
	
	@Test
	void getOutagesfilterNoidTest() {
		List<Outage> response = (List<Outage>) obj.getOutages("noid").getBody();
		assertTrue(response.size() > 1);
	}

	@Test
	void getOutagesFilterStatusOknoIdTest() {
		int code = obj.getOutages("noid").getStatusCode().value();
		assertEquals(200, code);
	}

	@Test
	void getOutagesbySiteTest() {
		SiteInfo response = obj.getOutagesBySiteId("norwich-pear-tree").getBody();
		assertTrue(response.getDevices().size() > 1);
	}

	@Test
	void getOutagesBySiteStatusOkTest() {
		int code = obj.getOutagesBySiteId("norwich-pear-tree").getStatusCode().value();
		assertEquals(200, code);
	}

	@Test
	void createOutagesbySiteThrowTest() {
		assertThrows(Exception.class, () -> obj.createOutagesBySiteId("norwich-pear-tree", null));
	}

	@Test
	void createOutagesbySiteTest() {
		int code = obj.createOutagesBySiteId("norwich-pear-tree", getRequestBody()).getStatusCode().value();
		assertEquals(201, code);
	}

	private List<SiteOutage> getRequestBody() {
		SiteOutage request = new SiteOutage();
		request.setBegin("2022-11-13T02:16:38.905Z");
		request.setId("002b28fc-283c-47ec-9af2-ea287336dc1b");
		request.setName("Battery 1");
		request.setEnd("2022-11-13T02:16:38.905Z");
		ArrayList<SiteOutage> list = new ArrayList<SiteOutage>();
		list.add(request);
		return list;
	}

}
