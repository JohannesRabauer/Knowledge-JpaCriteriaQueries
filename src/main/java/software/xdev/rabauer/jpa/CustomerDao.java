package software.xdev.rabauer.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;


public class CustomerDao
{

	public void doPut(Object c)
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}

	public List<Customer> doGetWithJPQL()
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		return em.createQuery("SELECT c FROM Customer c").getResultList();
	}

	public List<Customer> doGetWithCriteria()
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery   criteriaQuery   = criteriaBuilder.createQuery();
		Root            customer        = criteriaQuery.from(Customer.class);
		Query           query           = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public List<Customer> doGetWithMinimumAge(int minAge)
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
		Root customer = criteriaQuery.from(Customer.class);
		criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(customer.get("age"), minAge));
		Query query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public List<Customer> doGetWithMinimumAgeTyped(int minAge)
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		final CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
		final Root<Customer>          rootCustomer  = criteriaQuery.from(Customer.class);
		criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(rootCustomer.get(software.xdev.rabauer.jpa.Customer_.age), minAge));
		final TypedQuery<Customer> typedQuery    = em.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	public List<String> doGetFirstNames()
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery   criteriaQuery   = criteriaBuilder.createQuery();
		Root<Customer>  customer        = criteriaQuery.from(Customer.class);
		criteriaQuery.select(customer.get("firstName"));
		Query           query           = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public List<Object[]> doGetGroupWithLastName()
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		final CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		final Root<Customer>          rootCustomer  = criteriaQuery.from(Customer.class);
		criteriaQuery.groupBy(rootCustomer.get(software.xdev.rabauer.jpa.Customer_.lastName));
		criteriaQuery.multiselect(
			rootCustomer.get(software.xdev.rabauer.jpa.Customer_.lastName),
			criteriaBuilder.avg(rootCustomer.get(software.xdev.rabauer.jpa.Customer_.age))
		);
		TypedQuery<Object[]> typedQuery = em.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
}
