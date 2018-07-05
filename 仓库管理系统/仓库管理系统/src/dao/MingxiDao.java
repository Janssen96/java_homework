package dao;

import java.util.List;

import model.Mingxi;


public interface MingxiDao  {
	
	
	
	public void insertBean(Mingxi bean);
	
	public void deleteBean(Mingxi bean);
	
	public void updateBean(Mingxi bean);

	public Mingxi selectBean(String where);
	
	public List<Mingxi> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
