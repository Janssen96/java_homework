package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Kucun")
public class Kucun implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private Kufang kufang;
	
	private Product product;
	
	private int shuliang;
	
	private User user;
	
	private Quyu quyu;
	
	private int keshengqingkecun;//可申请出库/或调出数量
	

	

	




	public int getKeshengqingkecun() {
		return keshengqingkecun;
	}

	public void setKeshengqingkecun(int keshengqingkecun) {
		this.keshengqingkecun = keshengqingkecun;
	}

	@ManyToOne
	@JoinColumn(name="quyuid")
	public Quyu getQuyu() {
		return quyu;
	}

	public void setQuyu(Quyu quyu) {
		this.quyu = quyu;
	}

	@ManyToOne
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="kufangid")
	public Kufang getKufang() {
		return kufang;
	}

	public void setKufang(Kufang kufang) {
		this.kufang = kufang;
	}

	@ManyToOne
	@JoinColumn(name="productid")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getShuliang() {
		return shuliang;
	}

	public void setShuliang(int shuliang) {
		this.shuliang = shuliang;
	}

	
	

	
	
	
	
}
