package software.xdev.rabauer.jpa;

import jakarta.persistence.EntityManager;

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

	public List<Customer> doGet()
	{
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
		return (List<Customer>)em.createQuery("SELECT c FROM Customer c").getResultList();
	}
}
