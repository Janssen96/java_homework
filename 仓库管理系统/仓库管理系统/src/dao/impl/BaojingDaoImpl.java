package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Baojing;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.BaojingDao;









public class BaojingDaoImpl extends HibernateDaoSupport implements  BaojingDao{


	public void deleteBean(Baojing bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Baojing bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Baojing selectBean(String where) {
		List<Baojing> list = this.getHibernateTemplate().find("from Baojing " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Baojing "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Baojing> selectBeanList(final int start,final int limit,final String where) {
		return (List<Baojing>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Baojing> list = session.createQuery("from Baojing "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Baojing bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
