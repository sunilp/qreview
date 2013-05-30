package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Role{
	public String id;
	public String name;
	
	
	public static List<String> findRolesByUser(Connection connection, String id) {
		List<String> list = new ArrayList<String>();
		
		  try{
		  PreparedStatement ps = connection.prepareStatement("select role_id from user_role_xref where username=? ");
		   ps.setString(1, id);
		   ps.execute();
		  ResultSet rs =  ps.getResultSet();
		  
		 while(rs.next()){
			 String roleId = rs.getString(1);
			 list.add(roleId);
		 }
		  System.out.println("executed for user_roles select ["+id+"]" );
		  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return list;
	}
	
	
}