package com.org.kraken.flex.model;

public class SiteOutage {

	private String id;
	private String begin;
	private String end;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SiteOutage [id=" + id + ", begin=" + begin + ", end=" + end + ", name=" + name + "]";
	}

}
