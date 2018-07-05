package dao;

import java.util.List;

import model.Quyu;


public interface QuyuDao  {
	
	
	
	public void insertBean(Quyu bean);
	
	public void deleteBean(Quyu bean);
	
	public void updateBean(Quyu bean);

	public Quyu selectBean(String where);
	
	public List<Quyu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
