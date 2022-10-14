package software.xdev.rabauer.jpa.complex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.xdev.rabauer.jpa.HibernateUtil;

import java.util.List;


public class EmployeeDaoTest
{
	@AfterEach
	void clearDB()
	{
		HibernateUtil.shutdown();
	}

	@Test
	void test_new_employee_no_task()
	{
		Employee e = new Employee("Joe");
		EmployeeDao employeeDao = new EmployeeDao();
		employeeDao.doPut(e);
	}

	@Test
	void test_new_employee_with_task()
	{
		Employee e = new Employee("Joe");
		Task t = new Task("Test stuff");
		e.getTasks().add(t);
		new TaskDao().doPut(t);
		EmployeeDao employeeDao = new EmployeeDao();
		employeeDao.doPut(e);
	}

	@Test
	void test_get_employee_no_join()
	{
		Employee e = new Employee("Joe");
		Task t = new Task("Test stuff");
		e.getTasks().add(t);
		new TaskDao().doPut(t);
		EmployeeDao employeeDao = new EmployeeDao();
		employeeDao.doPut(e);

		List<Employee> employees = employeeDao.doGetWithCriteria();
		Assertions.assertEquals(1, employees.size());
		Assertions.assertEquals(1, employees.get(0).getTasks().size());
		employees.get(0).getTasks().forEach(
			task -> Assertions.assertEquals("Test stuff", task.getDescription())
		);
	}
}
