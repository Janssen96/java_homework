package dao;

import java.util.List;

import model.Baojing;


public interface BaojingDao  {
	
	
	
	public void insertBean(Baojing bean);
	
	public void deleteBean(Baojing bean);
	
	public void updateBean(Baojing bean);

	public Baojing selectBean(String where);
	
	public List<Baojing> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
