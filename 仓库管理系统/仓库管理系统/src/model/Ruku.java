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
@Table(name="t_Ruku")
public class Ruku implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int rukulock;
	
	private Kufang kufang;
	
	private Product product;
	
	private User user;//添加用户
	
	private int rukushuliang;
	
	private Date createtime;
	
	private String shenhezhuangtai;//入库单状态 ，未审核， 审核通过 ，审核未通过 ,成功入库
	
	private String rukudanbianhao;//入库单编号
	
	private String shenhefanhui;//审核反馈
	
	private Rukuliyou rukuliyou;
	
	private Quyu quyu;
	
	private User shenheuser;//审核用户
	
	
	
	

	
	
	

	


	@ManyToOne
	@JoinColumn(name="shenheuserid")
	public User getShenheuser() {
		return shenheuser;
	}

	public void setShenheuser(User shenheuser) {
		this.shenheuser = shenheuser;
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
	@JoinColumn(name="rukuliyouid")
	public Rukuliyou getRukuliyou() {
		return rukuliyou;
	}

	public void setRukuliyou(Rukuliyou rukuliyou) {
		this.rukuliyou = rukuliyou;
	}

	public String getShenhefanhui() {
		return shenhefanhui;
	}

	public void setShenhefanhui(String shenhefanhui) {
		this.shenhefanhui = shenhefanhui;
	}

	public String getRukudanbianhao() {
		return rukudanbianhao;
	}

	public void setRukudanbianhao(String rukudanbianhao) {
		this.rukudanbianhao = rukudanbianhao;
	}

	public int getRukulock() {
		return rukulock;
	}

	public void setRukulock(int rukulock) {
		this.rukulock = rukulock;
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

	@ManyToOne
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRukushuliang() {
		return rukushuliang;
	}

	public void setRukushuliang(int rukushuliang) {
		this.rukushuliang = rukushuliang;
	}

	public String getShenhezhuangtai() {
		return shenhezhuangtai;
	}

	public void setShenhezhuangtai(String shenhezhuangtai) {
		this.shenhezhuangtai = shenhezhuangtai;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
