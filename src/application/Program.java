package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(4);
		System.out.println("*** findById ***");
		System.out.println(seller);
		
		System.out.println("\n*** findByDepartment ***");
		Department dep = new Department(2, null);
		List<Seller> sel = sellerDao.findByDepartment(dep);
		for(Seller s : sel) {
			System.out.println(s);		
		}
		
		System.out.println("\n*** finAll ***");
		sel = sellerDao.findAll();
		for(Seller s : sel) {
			System.out.println(s);		
		}
		
		System.out.println("\n*** insert ***");
		Seller newSeller = new Seller(null, "Caleb", "caleb@email.com", new Date(), 4000.00, dep);
		sellerDao.insert(newSeller);
		System.out.println("Inserido! Id criado " + newSeller.getId());
	}
	
}
		
		
		
