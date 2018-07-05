package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Mingxi;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.MingxiDao;









public class MingxiDaoImpl extends HibernateDaoSupport implements  MingxiDao{


	public void deleteBean(Mingxi bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Mingxi bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Mingxi selectBean(String where) {
		List<Mingxi> list = this.getHibernateTemplate().find("from Mingxi " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Mingxi "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Mingxi> selectBeanList(final int start,final int limit,final String where) {
		return (List<Mingxi>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Mingxi> list = session.createQuery("from Mingxi "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Mingxi bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
