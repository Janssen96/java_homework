package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Quyu;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.QuyuDao;









public class QuyuDaoImpl extends HibernateDaoSupport implements  QuyuDao{


	public void deleteBean(Quyu bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Quyu bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Quyu selectBean(String where) {
		List<Quyu> list = this.getHibernateTemplate().find("from Quyu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Quyu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Quyu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Quyu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Quyu> list = session.createQuery("from Quyu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Quyu bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
