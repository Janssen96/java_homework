package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_Kufang")
public class Kufang implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int kufanglock;
	
	private String name;//库房名
	
	private String beizhu;//备注
	
	private String mianji;//面积
	
	private int zuidashuliang;//最大数量
	
	private Date createtime;
	
	private String kufangbianhao;//库房编号
	
	private int baojingzuixiaoshuliang;//报警最小数量
	
	private int baojingzuidashuliang;//报警最大数量
	
	private int rushushuliang;//申请入库数量
	
	private int shijirukushuliang;//实际入库数量
	
	

	

	




	public int getShijirukushuliang() {
		return shijirukushuliang;
	}

	public void setShijirukushuliang(int shijirukushuliang) {
		this.shijirukushuliang = shijirukushuliang;
	}

	public int getRushushuliang() {
		return rushushuliang;
	}

	public void setRushushuliang(int rushushuliang) {
		this.rushushuliang = rushushuliang;
	}


	public int getBaojingzuixiaoshuliang() {
		return baojingzuixiaoshuliang;
	}

	public void setBaojingzuixiaoshuliang(int baojingzuixiaoshuliang) {
		this.baojingzuixiaoshuliang = baojingzuixiaoshuliang;
	}

	public int getBaojingzuidashuliang() {
		return baojingzuidashuliang;
	}

	public void setBaojingzuidashuliang(int baojingzuidashuliang) {
		this.baojingzuidashuliang = baojingzuidashuliang;
	}

	public String getKufangbianhao() {
		return kufangbianhao;
	}

	public void setKufangbianhao(String kufangbianhao) {
		this.kufangbianhao = kufangbianhao;
	}

	public int getKufanglock() {
		return kufanglock;
	}

	public void setKufanglock(int kufanglock) {
		this.kufanglock = kufanglock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getMianji() {
		return mianji;
	}

	public void setMianji(String mianji) {
		this.mianji = mianji;
	}

	public int getZuidashuliang() {
		return zuidashuliang;
	}

	public void setZuidashuliang(int zuidashuliang) {
		this.zuidashuliang = zuidashuliang;
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
