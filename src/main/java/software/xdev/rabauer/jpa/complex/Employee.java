package software.xdev.rabauer.jpa.complex;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Employee
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private String name;
	@ManyToMany
	private Set<Task> tasks;

	public Employee(String name)
	{
		this();
		this.name = name;
	}

	public Employee()
	{
		this.tasks = new HashSet<>();
	}

	public String getName()
	{
		return name;
	}

	public Set<Task> getTasks()
	{
		return tasks;
	}
}
