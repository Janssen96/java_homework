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
@Table(name="t_Mingxi")
public class Mingxi implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private User user;
	
	private Date createtime;
	
	private String leixing;//明细类型
	
	private String liyou;//理由
	
	private String chanpin;
	
	private int shuliang;
	
	
	private String content;
	
	private String shenheren;//审核人
	
	private String gongyingshang;//供应商
	
	private String jingxiaoshang;//经销商
	
	
	

	




	
	


	public String getShenheren() {
		return shenheren;
	}

	public void setShenheren(String shenheren) {
		this.shenheren = shenheren;
	}

	public String getGongyingshang() {
		return gongyingshang;
	}

	public void setGongyingshang(String gongyingshang) {
		this.gongyingshang = gongyingshang;
	}

	public String getJingxiaoshang() {
		return jingxiaoshang;
	}

	public void setJingxiaoshang(String jingxiaoshang) {
		this.jingxiaoshang = jingxiaoshang;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getShuliang() {
		return shuliang;
	}

	public void setShuliang(int shuliang) {
		this.shuliang = shuliang;
	}

	public String getChanpin() {
		return chanpin;
	}

	public void setChanpin(String chanpin) {
		this.chanpin = chanpin;
	}

	

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}


	public String getLiyou() {
		return liyou;
	}

	public void setLiyou(String liyou) {
		this.liyou = liyou;
	}

	@ManyToOne
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
