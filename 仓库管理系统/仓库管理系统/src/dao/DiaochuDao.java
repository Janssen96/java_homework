package dao;

import java.util.List;

import model.Diaochu;


public interface DiaochuDao  {
	
	
	
	public void insertBean(Diaochu bean);
	
	public void deleteBean(Diaochu bean);
	
	public void updateBean(Diaochu bean);

	public Diaochu selectBean(String where);
	
	public List<Diaochu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
