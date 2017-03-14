package ua.dto.form;

import java.util.List;

import javax.persistence.criteria.Predicate;

public class BasicFilter {
	private String search = "";

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
