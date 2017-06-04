package model;
// Generated Jun 5, 2017 1:55:46 AM by Hibernate Tools 5.2.3.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Budgetentry.
 * @see model.Budgetentry
 * @author Hibernate Tools
 */
public class BudgetentryHome {

	private static final Log log = LogFactory.getLog(BudgetentryHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Budgetentry transientInstance) {
		log.debug("persisting Budgetentry instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Budgetentry instance) {
		log.debug("attaching dirty Budgetentry instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Budgetentry instance) {
		log.debug("attaching clean Budgetentry instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Budgetentry persistentInstance) {
		log.debug("deleting Budgetentry instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Budgetentry merge(Budgetentry detachedInstance) {
		log.debug("merging Budgetentry instance");
		try {
			Budgetentry result = (Budgetentry) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Budgetentry findById(java.lang.Integer id) {
		log.debug("getting Budgetentry instance with id: " + id);
		try {
			Budgetentry instance = (Budgetentry) sessionFactory.getCurrentSession().get("model.Budgetentry", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Budgetentry instance) {
		log.debug("finding Budgetentry instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("model.Budgetentry")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
