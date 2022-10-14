package software.xdev.rabauer.jpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomerDaoTest
{
	@AfterEach
	public void clearDB()
	{
		HibernateUtil.shutdown();
	}

	@Test
	public void test_inserting_simple_customer()
	{
		Customer c = new Customer("Tester", "Joe", 10);
		new CustomerDao().doPut(c);
	}

	@Test
	public void test_inserting_and_reading_simple_customer()
	{
		Customer c = new Customer("Tester", "Joe", 10);
		CustomerDao customerDao = new CustomerDao();
		customerDao.doPut(c);

		List<Customer> customers = customerDao.doGet();
		Assertions.assertEquals(1, customers.size());
		Assertions.assertEquals(c, customers.get(0));
	}
}
