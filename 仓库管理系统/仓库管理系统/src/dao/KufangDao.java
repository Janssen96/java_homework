package dao;

import java.util.List;

import model.Kufang;


public interface KufangDao  {
	
	
	
	public void insertBean(Kufang bean);
	
	public void deleteBean(Kufang bean);
	
	public void updateBean(Kufang bean);

	public Kufang selectBean(String where);
	
	public List<Kufang> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
