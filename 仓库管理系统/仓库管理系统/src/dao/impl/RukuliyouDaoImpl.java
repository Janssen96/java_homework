package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Rukuliyou;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.RukuliyouDao;









public class RukuliyouDaoImpl extends HibernateDaoSupport implements  RukuliyouDao{


	public void deleteBean(Rukuliyou bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Rukuliyou bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Rukuliyou selectBean(String where) {
		List<Rukuliyou> list = this.getHibernateTemplate().find("from Rukuliyou " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Rukuliyou "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Rukuliyou> selectBeanList(final int start,final int limit,final String where) {
		return (List<Rukuliyou>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Rukuliyou> list = session.createQuery("from Rukuliyou "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Rukuliyou bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
