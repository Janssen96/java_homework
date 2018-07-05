package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Diaochu;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.DiaochuDao;









public class DiaochuDaoImpl extends HibernateDaoSupport implements  DiaochuDao{


	public void deleteBean(Diaochu bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Diaochu bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Diaochu selectBean(String where) {
		List<Diaochu> list = this.getHibernateTemplate().find("from Diaochu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Diaochu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Diaochu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Diaochu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Diaochu> list = session.createQuery("from Diaochu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Diaochu bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
