package software.xdev.rabauer.jpa.complex;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;


@Entity
public class Task {
	@Id
	@GeneratedValue
	private long id;
	private String description;

	@ManyToMany
	private Set<Employee> employees;

	public Task()
	{
	}

	public Task(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}
}
