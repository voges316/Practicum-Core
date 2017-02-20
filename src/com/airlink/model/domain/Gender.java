package com.airlink.model.domain;

public enum Gender {
	MALE("MALE"), 
    FEMALE("FEMALE"),
	UNKNOWN("UNKNOWN");
	
	private final String abbrv;
	
	Gender(String abbrv) {
		this.abbrv = abbrv;
	}
	
	@Override
	public String toString() {
		return abbrv;
	}
}
