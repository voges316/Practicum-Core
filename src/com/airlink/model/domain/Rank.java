package com.airlink.model.domain;

public enum Rank {
	OFFICER("OFFICER"),
	ENLISTED("ENLISTED");
	
	private final String abbrv;
	
	Rank(String abbrv) {
		this.abbrv = abbrv;
	}
	
	@Override
	public String toString() {
		return abbrv;
	}
}
