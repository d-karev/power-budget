package model;
// Generated Jun 8, 2017 3:17:03 AM by Hibernate Tools 5.2.3.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class User.
 * @see model.User
 * @author Hibernate Tools
 */
public class UserHome {

	private static final Log log = LogFactory.getLog(UserHome.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		sessionFactory.getCurrentSession().beginTransaction();
		try {			
			sessionFactory.getCurrentSession().persist(transientInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("persist failed", re);			
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			sessionFactory.getCurrentSession().getTransaction().commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("delete failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			User result = (User) sessionFactory.getCurrentSession().merge(detachedInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			User instance = (User) sessionFactory.getCurrentSession().get("model.User", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
			return instance;
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("model.User").add(Example.create(instance))
					.list();
			sessionFactory.getCurrentSession().getTransaction().commit();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("find by example failed", re);
			throw re;
		}
	}
}
