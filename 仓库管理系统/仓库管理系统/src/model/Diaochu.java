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
@Table(name="t_Diaochu")
public class Diaochu implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int diaochulock;
	
	private Kufang kufang1;//调出库房
	
	private Quyu quyu1;//调出区域
	
	private Kufang kufang2;//调入库房
	
	private Quyu quyu2;//调入区域
	
	private Product product;
	
	private User user;
	
	private int diaodushuliang;
	
	private Date createtime;
	
	private String shenhezhuangtai;//调度单状态 ，未审核， 审核通过 ，审核未通过 ,成功调度
	
	private String rukudanbianhao;//调度单编号
	
	private String shenhefanhui;//审核反馈
	
	private Rukuliyou rukuliyou;
	

	private User shenheuser;
	
	

	


	@ManyToOne
	@JoinColumn(name="shenheuserid")
	public User getShenheuser() {
		return shenheuser;
	}

	public void setShenheuser(User shenheuser) {
		this.shenheuser = shenheuser;
	}

	@ManyToOne
	@JoinColumn(name="quyu1id")
	public Quyu getQuyu1() {
		return quyu1;
	}

	public void setQuyu1(Quyu quyu1) {
		this.quyu1 = quyu1;
	}

	@ManyToOne
	@JoinColumn(name="quyu2id")
	public Quyu getQuyu2() {
		return quyu2;
	}

	public void setQuyu2(Quyu quyu2) {
		this.quyu2 = quyu2;
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

	

	@ManyToOne
	@JoinColumn(name="kufang1id")
	public Kufang getKufang1() {
		return kufang1;
	}

	public void setKufang1(Kufang kufang1) {
		this.kufang1 = kufang1;
	}

	@ManyToOne
	@JoinColumn(name="kufang2id")
	public Kufang getKufang2() {
		return kufang2;
	}

	public void setKufang2(Kufang kufang2) {
		this.kufang2 = kufang2;
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

	public int getDiaodushuliang() {
		return diaodushuliang;
	}

	public void setDiaodushuliang(int diaodushuliang) {
		this.diaodushuliang = diaodushuliang;
	}

	public int getDiaochulock() {
		return diaochulock;
	}

	public void setDiaochulock(int diaochulock) {
		this.diaochulock = diaochulock;
	}

	
	

	
	
	
	
}
