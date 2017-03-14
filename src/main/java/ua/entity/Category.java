package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Означає що це сутність БД
@Entity
//для того що б таблиці мали назву як і в БД
@Table(name="category")
public class Category extends AbstractEntity {
	@Id
	//AUTO_INCTEMENT
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//для того що б стовпці мали назву як і в БД

	@Column(name="category")
	private String category;
	
	@OneToMany(mappedBy="category")
	private List<Subcategory> subcategory = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Subcategory> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(List<Subcategory> subcategory) {
		this.subcategory = subcategory;
	}
	
	
}
