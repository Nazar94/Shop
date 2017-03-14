package ua.dto.form;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Country;
import ua.entity.Delivery;
import ua.entity.Manufacturer;
import ua.entity.Material;
import ua.entity.Subcategory;

public class GoodForm {

	private int id;
	private String price;
	private String good;
	private Manufacturer manufacturer;
	private Country country;
	private Delivery delivery;
	private Subcategory subcategory;
	private Material material;
	
	private MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Delivery getDelivery() {
		return delivery;
	}
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	public Subcategory getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
	}


	
}
