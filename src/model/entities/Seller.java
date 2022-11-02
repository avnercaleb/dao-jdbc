package model.entities;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seller {
	
	private Integer id;
	private String name;
	private String email;
	private LocalDate bithDate;
	private Double baseSalary;
}
