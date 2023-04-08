package com.org.kraken.flex.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class SiteInfo {
	
	private String id;
	private String name;
	@JsonInclude(value = Include.NON_NULL)
	private List<SiteInfo> devices;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SiteInfo> getDevices() {
		return devices;
	}
	public void setDevices(List<SiteInfo> devices) {
		this.devices = devices;
	}
	@Override
	public String toString() {
		return "SiteInfo [id=" + id + ", name=" + name + ", devices=" + devices + "]";
	}
	
	

}
