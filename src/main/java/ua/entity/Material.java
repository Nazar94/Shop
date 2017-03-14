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

//������ �� �� ������� ��
	@Entity
	//��� ���� �� � ������� ���� ����� �� � � ��
	@Table(name = "material")
public class Material extends AbstractEntity {

		@Id
		//AUTO_INCTEMENT
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		//��� ���� �� � ������� ���� ����� �� � � ��
		@Column(name="material")
		private String material;
		
		
		@OneToMany(mappedBy="material")
		private List<Good> goods = new ArrayList<>();


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getMaterial() {
			return material;
		}


		public void setMaterial(String material) {
			this.material = material;
		}


		public List<Good> getGoods() {
			return goods;
		}


		public void setGoods(List<Good> goods) {
			this.goods = goods;
		}
		
		
		
}
