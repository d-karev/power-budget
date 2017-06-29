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
 * Home object for domain model class Budgetentry.
 * @see model.Budgetentry
 * @author Hibernate Tools
 */
public class BudgetentryHome {

	private static final Log log = LogFactory.getLog(BudgetentryHome.class);

	private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void persist(Budgetentry transientInstance) {
		log.debug("persisting Budgetentry instance");
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

	public void attachDirty(Budgetentry instance) {
		log.debug("attaching dirty Budgetentry instance");
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

	public void attachClean(Budgetentry instance) {
		log.debug("attaching clean Budgetentry instance");
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

	public void delete(Budgetentry persistentInstance) {
		log.debug("deleting Budgetentry instance");
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

	public Budgetentry merge(Budgetentry detachedInstance) {
		log.debug("merging Budgetentry instance");
		sessionFactory.getCurrentSession().beginTransaction();
		try {			
			Budgetentry result = (Budgetentry) sessionFactory.getCurrentSession().merge(detachedInstance);			
			sessionFactory.getCurrentSession().getTransaction().commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("merge failed", re);
			throw re;
		}
	}

	public Budgetentry findById(java.lang.Integer id) {
		log.debug("getting Budgetentry instance with id: " + id);
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			Budgetentry instance = (Budgetentry) sessionFactory.getCurrentSession().get("model.Budgetentry", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
				
				instance.setBudgetnomen(loadNomen(instance.getNomenid()));
			}						
			
			sessionFactory.getCurrentSession().getTransaction().commit();
			return instance;
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Budgetentry> findByExample(Budgetentry instance) {
		log.debug("finding Budgetentry instance by example");
		sessionFactory.getCurrentSession().beginTransaction();
		try {
			List<Budgetentry> results = sessionFactory.getCurrentSession().createCriteria("model.Budgetentry")
					.add(Example.create(instance)).list();			
			log.debug("find by example successful, result size: " + results.size());
			
			for (int k = 0; k < results.size(); k++)
				results.get(k).setBudgetnomen(loadNomen(results.get(k).getNomenid()));
			
			sessionFactory.getCurrentSession().getTransaction().commit();
			return results;
		} catch (RuntimeException re) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	private Budgetnomen loadNomen(int id) {
		if (id < 1)
			return null;
		else {
			return (Budgetnomen) sessionFactory.getCurrentSession().get("model.Budgetnomen", id);
		}			
	}
}
