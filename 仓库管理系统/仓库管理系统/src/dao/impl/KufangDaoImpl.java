package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Kufang;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.KufangDao;









public class KufangDaoImpl extends HibernateDaoSupport implements  KufangDao{


	public void deleteBean(Kufang bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Kufang bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Kufang selectBean(String where) {
		List<Kufang> list = this.getHibernateTemplate().find("from Kufang " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Kufang "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Kufang> selectBeanList(final int start,final int limit,final String where) {
		return (List<Kufang>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Kufang> list = session.createQuery("from Kufang "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Kufang bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
