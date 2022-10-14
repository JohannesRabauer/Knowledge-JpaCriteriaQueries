package software.xdev.rabauer.jpa.complex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import software.xdev.rabauer.jpa.HibernateUtil;

import java.util.List;


public class TaskDao
{
	public void doPut(Task e)
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}
}
