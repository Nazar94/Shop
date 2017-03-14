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
@Table(name = "delivery")
public class Delivery extends AbstractEntity {

	
	// ��������� ����
		@Id
		// AUTO_INCTEMENT
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		// ��� ���� �� � ������� ���� ����� �� � � ��
		
		@Column(name = "delivery")
	private String delivery;
		
		@OneToMany(mappedBy="delivery")
	private List<Good> goods = new ArrayList<>();

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDelivery() {
			return delivery;
		}

		public void setDelivery(String delivery) {
			this.delivery = delivery;
		}

		public List<Good> getGoods() {
			return goods;
		}

		public void setGoods(List<Good> goods) {
			this.goods = goods;
		}
		
		
}
