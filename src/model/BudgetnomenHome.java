package model;
// Generated Jun 8, 2017 3:17:03 AM by Hibernate Tools 5.2.3.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Budgetnomen.
 * @see model.Budgetnomen
 * @author Hibernate Tools
 */
public class BudgetnomenHome {

	private static final Log log = LogFactory.getLog(BudgetnomenHome.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void persist(Budgetnomen transientInstance) {
		log.debug("persisting Budgetnomen instance");
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

	public void attachDirty(Budgetnomen instance) {
		log.debug("attaching dirty Budgetnomen instance");
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

	public void attachClean(Budgetnomen instance) {
		log.debug("attaching clean Budgetnomen instance");
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

	public void delete(Budgetnomen persistentInstance) {
		log.debug("deleting Budgetnomen instance");
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

	public Budgetnomen merge(Budgetnomen detachedInstance) {
		log.debug("merging Budgetnomen instance");
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			Budgetnomen result = (Budgetnomen) sessionFactory.getCurrentSession().merge(detachedInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("merge failed", re);
			throw re;
		}
	}

	public Budgetnomen findById(java.lang.Integer id) {
		log.debug("getting Budgetnomen instance with id: " + id);
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			Budgetnomen instance = (Budgetnomen) sessionFactory.getCurrentSession().get("model.Budgetnomen", id);
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

	public List findByExample(Budgetnomen instance) {
		log.debug("finding Budgetnomen instance by example");
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("model.Budgetnomen")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return results;
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("find by example failed", re);
			throw re;
		}
	}
}
