package ua.dto.form;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
public class GoodFilter {

	private static final Pattern PEATTERN = Pattern.compile("[0-9]+");

	private String search = "";
	
	private String maxPrice = "";
	
	private String minPrice = "";
	
	private Integer max;
	
	private Integer min;
	
	private List<Integer> manufacturerIds = new ArrayList<>();
	
	private List<Integer> materialIds = new ArrayList<>();
	
	private List<Integer> deliveryIds = new ArrayList<>();
	
	private List<Integer> countryIds = new ArrayList<>();
	
	private List<Integer> subcategoryIds = new ArrayList<>();

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getMaxPrice() {
		return maxPrice;
	}


	public String getMinPrice() {
		return minPrice;
	}

	public void setMaxPrice(String maxPrice) {
		if(PEATTERN.matcher(maxPrice).matches())max = Integer.valueOf(maxPrice);
		this.maxPrice = maxPrice;
	}
	
	public void setMinPrice(String minPrice) {
		if(PEATTERN.matcher(minPrice).matches())min = Integer.valueOf(minPrice);
		this.minPrice = minPrice;
	}
	
	
	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public List<Integer> getManufacturerIds() {
		return manufacturerIds;
	}

	public void setManufacturerIds(List<Integer> manufacturerIds) {
		this.manufacturerIds = manufacturerIds;
	}

	public List<Integer> getMaterialIds() {
		return materialIds;
	}

	public void setMaterialIds(List<Integer> materialIds) {
		this.materialIds = materialIds;
	}

	public List<Integer> getDeliveryIds() {
		return deliveryIds;
	}

	public void setDeliveryIds(List<Integer> deliveryIds) {
		this.deliveryIds = deliveryIds;
	}

	public List<Integer> getCountryIds() {
		return countryIds;
	}

	public void setCountryIds(List<Integer> countryIds) {
		this.countryIds = countryIds;
	}

	public List<Integer> getSubcategoryIds() {
		return subcategoryIds;
	}

	public void setSubcategoryIds(List<Integer> subcategoryIds) {
		this.subcategoryIds = subcategoryIds;
	}

	public static Pattern getPeattern() {
		return PEATTERN;
	}


	
	
	
}
