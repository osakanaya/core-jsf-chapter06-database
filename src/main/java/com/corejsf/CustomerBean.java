package com.corejsf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

@Named
@RequestScoped
public class CustomerBean {
	@Resource(name ="jdbc/corejsfdb")
	private DataSource ds;
	
	public ResultSet getAll() throws SQLException {
		Connection conn = ds.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Customers");
			
			CachedRowSet crs = new CachedRowSetImpl();
			crs.populate(result);
			
			return crs;
		} finally {
			conn.close();
		}
	}
}
