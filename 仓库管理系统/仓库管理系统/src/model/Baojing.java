package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_Baojing")
public class Baojing implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String content;
	

	
	
	

	




	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
