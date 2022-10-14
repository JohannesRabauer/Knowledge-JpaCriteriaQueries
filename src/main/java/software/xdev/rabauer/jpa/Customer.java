package software.xdev.rabauer.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;


@Entity
public class Customer
{
	@Id
	@GeneratedValue
	private Long id;

	private String  firstName;
	private String  lastName;
	private Integer age;

	public Customer()
	{

	}

	public Customer(String firstName, String lastName, Integer age)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	@Override public boolean equals(Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || getClass() != o.getClass())
		{
			return false;
		}
		Customer customer = (Customer)o;
		return Objects.equals(id, customer.id) && Objects.equals(firstName, customer.firstName)
			&& Objects.equals(lastName, customer.lastName) && Objects.equals(age, customer.age);
	}

	@Override public int hashCode()
	{
		return Objects.hash(id, firstName, lastName, age);
	}
}
