package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Product")
public class Product implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int productlock;
	
	private String name;//商品名
	
	private String shengchandizhi;//生成地址
	
	private String gongyingshang;//供应商
	
	private int baojingxiaxian;//报警下限(缺货报警)
	
	private User user;//添加用户
	
	private Date createtime;
	
	private int kucunshuliang;//库存数量
	
	
	
	

	
	
	

	




	

	public int getKucunshuliang() {
		return kucunshuliang;
	}

	public void setKucunshuliang(int kucunshuliang) {
		this.kucunshuliang = kucunshuliang;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getProductlock() {
		return productlock;
	}

	public void setProductlock(int productlock) {
		this.productlock = productlock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShengchandizhi() {
		return shengchandizhi;
	}

	public void setShengchandizhi(String shengchandizhi) {
		this.shengchandizhi = shengchandizhi;
	}

	public String getGongyingshang() {
		return gongyingshang;
	}

	public void setGongyingshang(String gongyingshang) {
		this.gongyingshang = gongyingshang;
	}

	public int getBaojingxiaxian() {
		return baojingxiaxian;
	}

	public void setBaojingxiaxian(int baojingxiaxian) {
		this.baojingxiaxian = baojingxiaxian;
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

	
	

	
	
	
	
}
