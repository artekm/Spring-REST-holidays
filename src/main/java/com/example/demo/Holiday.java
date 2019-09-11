package com.example.demo;

import java.time.LocalDate;

public class Holiday {
	private LocalDate ISO;
	private String name;

	public Holiday() {
	}

	public Holiday(LocalDate iSO, String name) {
		ISO = iSO;
		this.name = name;
	}

	public LocalDate getISO() {
		return ISO;
	}

	public void setISO(LocalDate iSO) {
		ISO = iSO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
