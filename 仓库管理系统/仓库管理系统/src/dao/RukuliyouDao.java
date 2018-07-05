package dao;

import java.util.List;

import model.Rukuliyou;


public interface RukuliyouDao  {
	
	
	
	public void insertBean(Rukuliyou bean);
	
	public void deleteBean(Rukuliyou bean);
	
	public void updateBean(Rukuliyou bean);

	public Rukuliyou selectBean(String where);
	
	public List<Rukuliyou> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
