package ua.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//Означає що це сутність БД
@Entity
//для того що б таблиці мали назву як і в БД
@Table(name = "good")
public class Good {

	
	// Первинний ключ
		@Id
		// AUTO_INCTEMENT
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		// для того що б стовпці мали назву як і в БД
		@Column(name = "good")
		private String good;
		
		@Column(name = "version", nullable = true)
		private Integer version;
		
		private BigDecimal price;
		
		@ManyToOne(fetch = FetchType.LAZY)
		private Manufacturer manufacturer;
		
		@ManyToOne(fetch = FetchType.LAZY)
		private Country country;
		
		@ManyToOne(fetch = FetchType.LAZY)
		private Delivery delivery;
		
		@ManyToOne(fetch = FetchType.LAZY)
		private Subcategory subcategory;
		
		@ManyToMany(mappedBy="goods" ) 
		private List<User> users = new ArrayList<>();
		
		@ManyToOne(fetch = FetchType.LAZY)
		private Material material;

		public Integer getVersion() {
			return version;
		}
		
		public void setVersion(Integer version) {
			this.version = version;
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getGood() {
			return good;
		}

		public void setGood(String good) {
			this.good = good;
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


		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public Material getMaterial() {
			return material;
		}

		public void setMaterial(Material material) {
			this.material = material;
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}


}
