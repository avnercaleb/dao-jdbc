package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department dp = new Department(1, "Books");
		Seller seller = new Seller(21, "Avner", "avnercaleb@email.com", new Date(), 2000.00, dp);
		
		System.out.println(seller);
		System.out.println(dp);
	}

}
