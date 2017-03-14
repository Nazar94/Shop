package ua.dto.form;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryFilter {
	private String search = "";
	private List<Integer> categoryIds=new ArrayList<>();
	
	private String subcategory= "";
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public List<Integer> getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	

	
	
}
