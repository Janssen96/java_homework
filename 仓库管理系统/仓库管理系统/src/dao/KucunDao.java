package dao;

import java.util.List;

import model.Kucun;


public interface KucunDao  {
	
	
	
	public void insertBean(Kucun bean);
	
	public void deleteBean(Kucun bean);
	
	public void updateBean(Kucun bean);

	public Kucun selectBean(String where);
	
	public List<Kucun> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	@SuppressWarnings("unchecked")
	public List execSQL(String SQL);
	
	
}
