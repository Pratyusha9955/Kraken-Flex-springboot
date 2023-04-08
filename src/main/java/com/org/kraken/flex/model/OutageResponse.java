package com.org.kraken.flex.model;

public class OutageResponse<T> {
	
	private T response;

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "OutageResponse [response=" + response + "]";
	}
	
	

}
