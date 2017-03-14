package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Означає що це сутність БД
@Entity
//для того що б таблиці мали назву як і в БД
@Table(name = "subcategory")
public class Subcategory extends AbstractEntity {

	// Первинний ключ
	@Id
	// AUTO_INCTEMENT
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// для того що б стовпці мали назву як і в БД
	@Column(name = "subcategory")
	private String subcategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "category")
	private Category category;

	@OneToMany(mappedBy="subcategory")
	private List<Good> goods = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
	
	//category
	
}
