package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_Rukuliyou")
public class Rukuliyou implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int rukuliyoulock;
	
	private String liyou;
	
	private Date createtime;
	
	private String infotype;//入库 出库  调度
	
	
	
	
	

	
	
	

	




	

	

	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public int getRukuliyoulock() {
		return rukuliyoulock;
	}

	public void setRukuliyoulock(int rukuliyoulock) {
		this.rukuliyoulock = rukuliyoulock;
	}

	public String getLiyou() {
		return liyou;
	}

	public void setLiyou(String liyou) {
		this.liyou = liyou;
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
