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
@Table(name = "price")
public class Price extends AbstractEntity {
	
	// Первинний ключ
				@Id
				// AUTO_INCTEMENT
				@GeneratedValue(strategy = GenerationType.IDENTITY)
				private int id;
				// для того що б стовпці мали назву як і в БД
				@Column(name = "price")
			private int price;
				
				@OneToMany(mappedBy="price")
				private List<Good> goods = new ArrayList<>();

				public int getId() {
					return id;
				}

				public void setId(int id) {
					this.id = id;
				}

				public int getPrice() {
					return price;
				}

				public void setPrice(int price) {
					this.price = price;
				}

				public List<Good> getGoods() {
					return goods;
				}

				public void setGoods(List<Good> goods) {
					this.goods = goods;
				}
				
				
				
				

}
