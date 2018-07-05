package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Quyu")
public class Quyu implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int quyulock;
	
	private Kufang kufang;

	private String quyuming;
	
	

	




	

	

	

	public int getQuyulock() {
		return quyulock;
	}

	public void setQuyulock(int quyulock) {
		this.quyulock = quyulock;
	}

	public String getQuyuming() {
		return quyuming;
	}

	public void setQuyuming(String quyuming) {
		this.quyuming = quyuming;
	}

	@ManyToOne
	@JoinColumn(name="kufangid")
	public Kufang getKufang() {
		return kufang;
	}

	public void setKufang(Kufang kufang) {
		this.kufang = kufang;
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
