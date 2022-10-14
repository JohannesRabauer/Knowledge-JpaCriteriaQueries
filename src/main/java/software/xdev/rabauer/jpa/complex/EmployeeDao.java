package software.xdev.rabauer.jpa.complex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import software.xdev.rabauer.jpa.HibernateUtil;
import software.xdev.rabauer.jpa.simple.Customer;
import software.xdev.rabauer.jpa.simple.Customer_;

import java.util.List;


public class EmployeeDao
{

	public void doPut(Employee e)
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}

	public List<Employee> doGetWithCriteria()
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Employee>   criteriaQuery   = criteriaBuilder.createQuery(Employee.class);
		Root<Employee>            employee        = criteriaQuery.from(Employee.class);
		Query           query           = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

}
