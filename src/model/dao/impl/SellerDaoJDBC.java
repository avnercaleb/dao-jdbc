package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	
	public SellerDaoJDBC(Connection conn) {		
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dp = invokeDep(rs);				
				Seller seller = invokeSeller(rs, dp);				
				
				return seller;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException("Erro: " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);			
		}
	}
		
	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Seller> result = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department department = map.get(rs.getInt("DepartmentId"));
				if(department == null) {
					department = invokeDep(rs);
					map.put(rs.getInt("DepartmentId"), department);
				}
												
				Seller seller = invokeSeller(rs, department);				
				result.add(seller);				
			}
			return result;
		}
		catch(SQLException e) {
			throw new DbException("Erro: " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);			
		}
			
	}
		
		
	
	
	@Override
	public List<Seller> findByDepartment(Department dp) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, dp.getId());
			rs = st.executeQuery();
			
			List<Seller> result = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department department = map.get(rs.getInt("DepartmentId"));
				if(department == null) {
					department = invokeDep(rs);
					map.put(rs.getInt("DepartmentId"), department);
				}
												
				Seller seller = invokeSeller(rs, department);				
				result.add(seller);
				
				
			}
			return result;
		}
		catch(SQLException e) {
			throw new DbException("Erro: " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);			
		}
	}
		
		

	private Seller invokeSeller(ResultSet rs, Department dp) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBithDate(rs.getDate("BirthDate"));
		seller.setDepartment(dp);
		return seller;
	}

	private Department invokeDep(ResultSet rs) throws SQLException {
		Department dp = new Department();
		dp.setId(rs.getInt("DepartmentId"));
		dp.setName(rs.getString("DepName"));
		return dp;
	}


}
