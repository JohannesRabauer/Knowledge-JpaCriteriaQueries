package software.xdev.rabauer.jpa.simple;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.xdev.rabauer.jpa.HibernateUtil;

import java.util.List;

class CustomerDaoTest
{
	@AfterEach
	void clearDB()
	{
		HibernateUtil.shutdown();
	}

	@Test
	void test_inserting_simple_customer()
	{
		Customer c = new Customer("Tester", "Joe", 10);
		Assertions.assertDoesNotThrow(() -> new CustomerDao().doPut(c));
	}

	@Test
	void test_inserting_and_reading_simple_customer_with_JPQL()
	{
		Customer c = new Customer("Tester", "Joe", 10);
		CustomerDao customerDao = new CustomerDao();
		customerDao.doPut(c);

		List<Customer> customers = customerDao.doGetWithJPQL();
		Assertions.assertEquals(1, customers.size());
		Assertions.assertEquals(c, customers.get(0));
	}

	@Test
	void test_inserting_and_reading_simple_customer_with_criteria()
	{
		Customer c = new Customer("Tester", "Joe", 10);
		CustomerDao customerDao = new CustomerDao();
		customerDao.doPut(c);

		List<Customer> customers = customerDao.doGetWithCriteria();
		Assertions.assertEquals(1, customers.size());
		Assertions.assertEquals(c, customers.get(0));
	}

	@Test
	void test_queriing_custer_with_min_age()
	{
		Customer c1 = new Customer("Joe",  "Tester", 10);
		Customer c2 = new Customer("Jyll", "Tester", 40);
		Customer c3 = new Customer("Jack", "Tester", 50);
		CustomerDao customerDao = new CustomerDao();
		customerDao.doPut(c1);
		customerDao.doPut(c2);
		customerDao.doPut(c3);

		List<Customer> customers = customerDao.doGetWithMinimumAge(40);
		Assertions.assertEquals(2, customers.size());
		Assertions.assertFalse(customers.contains(c1));
		Assertions.assertTrue (customers.contains(c2));
		Assertions.assertTrue (customers.contains(c3));
	}

	@Test
	void test_queriing_custer_with_min_age_typed()
	{
		Customer c1 = new Customer("Joe",  "Tester", 10);
		Customer c2 = new Customer("Jyll", "Tester", 40);
		Customer c3 = new Customer("Jack", "Tester", 50);
		CustomerDao customerDao = new CustomerDao();
		customerDao.doPut(c1);
		customerDao.doPut(c2);
		customerDao.doPut(c3);

		List<Customer> customers = customerDao.doGetWithMinimumAgeTyped(40);
		Assertions.assertEquals(2, customers.size());
		Assertions.assertFalse(customers.contains(c1));
		Assertions.assertTrue (customers.contains(c2));
		Assertions.assertTrue (customers.contains(c3));
	}

	@Test
	void test_queriing_customer_first_names()
	{
		Customer c1 = new Customer("Joe",  "Tester", 10);
		Customer c2 = new Customer("Jyll", "Tester", 40);
		Customer c3 = new Customer("Jack", "Tester", 50);
		CustomerDao customerDao = new CustomerDao();
		customerDao.doPut(c1);
		customerDao.doPut(c2);
		customerDao.doPut(c3);

		List<String> customerFirstNames = customerDao.doGetFirstNames();
		Assertions.assertEquals(3, customerFirstNames.size());
		Assertions.assertAll(
			() -> Assertions.assertTrue(customerFirstNames.contains("Joe")),
			() -> Assertions.assertTrue(customerFirstNames.contains("Jyll")),
			() -> Assertions.assertTrue(customerFirstNames.contains("Jack"))
		);
	}

	@Test
	void test_queriing_group()
	{
		Customer c1 = new Customer("Joe",  "Tester", 10);
		Customer c2 = new Customer("Jyll", "Tester", 40);
		Customer c3 = new Customer("Jack", "Testerich", 50);
		CustomerDao customerDao = new CustomerDao();
		customerDao.doPut(c1);
		customerDao.doPut(c2);
		customerDao.doPut(c3);

		List<Object[]> customerFirstNames = customerDao.doGetGroupWithLastName();
		Assertions.assertEquals(2, customerFirstNames.size());
		Assertions.assertEquals("Tester", customerFirstNames.get(0)[0]);
		Assertions.assertEquals(25.0, customerFirstNames.get(0)[1]);
		Assertions.assertEquals("Testerich", customerFirstNames.get(1)[0]);
		Assertions.assertEquals(50.0, customerFirstNames.get(1)[1]);
	}
}
