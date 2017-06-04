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
 * Home object for domain model class Budgetperiodic.
 * @see model.Budgetperiodic
 * @author Hibernate Tools
 */
public class BudgetperiodicHome {

	private static final Log log = LogFactory.getLog(BudgetperiodicHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Budgetperiodic transientInstance) {
		log.debug("persisting Budgetperiodic instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Budgetperiodic instance) {
		log.debug("attaching dirty Budgetperiodic instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Budgetperiodic instance) {
		log.debug("attaching clean Budgetperiodic instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Budgetperiodic persistentInstance) {
		log.debug("deleting Budgetperiodic instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Budgetperiodic merge(Budgetperiodic detachedInstance) {
		log.debug("merging Budgetperiodic instance");
		try {
			Budgetperiodic result = (Budgetperiodic) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Budgetperiodic findById(java.lang.Integer id) {
		log.debug("getting Budgetperiodic instance with id: " + id);
		try {
			Budgetperiodic instance = (Budgetperiodic) sessionFactory.getCurrentSession().get("model.Budgetperiodic",
					id);
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

	public List findByExample(Budgetperiodic instance) {
		log.debug("finding Budgetperiodic instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("model.Budgetperiodic")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
