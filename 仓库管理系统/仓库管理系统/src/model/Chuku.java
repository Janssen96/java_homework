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
@Table(name="t_Chuku")
public class Chuku implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int chukulock;
	
	private Kufang kufang;
	
	private Quyu quyu;//区域
	
	private Product product;
	
	private User user;
	
	private int chukushuliang;
	
	private Date createtime;
	
	private String shenhezhuangtai;//入库单状态 ，未审核， 审核通过 ，审核未通过 ,成功出库
	
	private String bianhao;//出库单编号
	
	private String shenhefanhui;//审核反馈
	
	private Rukuliyou liyou;
	
	private Kucun kucun;
	
	private String jingxiaoshang;//经销商
	
	private String lianxidianhua;//联系电话
	
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

	public String getJingxiaoshang() {
		return jingxiaoshang;
	}

	public void setJingxiaoshang(String jingxiaoshang) {
		this.jingxiaoshang = jingxiaoshang;
	}

	public String getLianxidianhua() {
		return lianxidianhua;
	}

	public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
	}

	@ManyToOne
	@JoinColumn(name="kucunid")
	public Kucun getKucun() {
		return kucun;
	}

	public void setKucun(Kucun kucun) {
		this.kucun = kucun;
	}

	public int getChukulock() {
		return chukulock;
	}

	public void setChukulock(int chukulock) {
		this.chukulock = chukulock;
	}

	public int getChukushuliang() {
		return chukushuliang;
	}

	public void setChukushuliang(int chukushuliang) {
		this.chukushuliang = chukushuliang;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	@ManyToOne
	@JoinColumn(name="liyouid")
	public Rukuliyou getLiyou() {
		return liyou;
	}

	public void setLiyou(Rukuliyou liyou) {
		this.liyou = liyou;
	}

	public String getShenhefanhui() {
		return shenhefanhui;
	}

	public void setShenhefanhui(String shenhefanhui) {
		this.shenhefanhui = shenhefanhui;
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
