package com.org.kraken.flex.model;

public class Outage {
	
	private String id;
	private String begin;
	private String end;
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
	@Override
	public String toString() {
		return "Outage [id=" + id + ", begin=" + begin + ", end=" + end + "]";
	}

}
