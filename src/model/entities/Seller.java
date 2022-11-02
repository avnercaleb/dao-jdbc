package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seller implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String email;
	private Date bithDate;
	private Double baseSalary;
	
	private Department department;
	
	public Seller(){
		
	}

	public Seller(Integer id, String name, String email, Date bithDate, Double baseSalary, Department department) {		
		this.id = id;
		this.name = name;
		this.email = email;
		this.bithDate = bithDate;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", bithDate=" + bithDate + ", baseSalary="
				+ baseSalary + ", department=" + department + "]";
	}
	
	
}
