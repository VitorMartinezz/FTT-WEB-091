package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.ftt.model.Doctor;
import ec.ftt.util.DBUtil;

public class DoctorDao {

    private Connection connection;

    public DoctorDao() {
        connection = DBUtil.getConnection();
    } //UserDao

    public void addDoctor(Doctor doctor) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO ftt.DOCTOR (ID, NAME, EMAIL, CRM, UNITY) VALUES (?, ?, ?, ?, ?)");
            
            preparedStatement.setLong(1, doctor.getId());
            preparedStatement.setString(2, doctor.getName());
            preparedStatement.setString(3, doctor.getEmail());
            preparedStatement.setString(4, doctor.getCrm());
            preparedStatement.setString(5, doctor.getUnity());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //addUser
    
    public void deleteDoctor(Long id) {
    	
    	Doctor doctor = new Doctor();
    	doctor.setId(id);
    	
    	deleteDoctor(doctor);
    	
    } // deleteUser long

    public void deleteDoctor(Doctor doctor) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM ftt.DOCTOR WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, doctor.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deleteUser

    public void updateDoctor(Doctor doctor) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE ftt.DOCTOR SET NAME=?, " 
                    		                          + "EMAIL=?, " 
                    		                          + "CRM=?, " 
                    		                          + "UNITY=? " 
                                               + "WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getEmail());
            preparedStatement.setString(3, doctor.getCrm());
            preparedStatement.setString(4, doctor.getUnity());
            preparedStatement.setLong(5, doctor.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updateUser

    public List<Doctor> getAllDoctor() {
        
    	List<Doctor> doctorList = new ArrayList<Doctor>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ftt.DOCTOR");
            while (rs.next()) {
                
            	Doctor doctor = new Doctor();
                
            	doctor.setId(rs.getLong("ID"));
            	doctor.setName(rs.getString("NAME"));
            	doctor.setEmail(rs.getString("EMAIL"));
            	doctor.setCrm(rs.getString("CRM"));
            	doctor.setUnity(rs.getString("UNITY"));	
            	doctorList.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorList;
    } //getAllUser

    public Doctor getDoctorById(Long id) {
    	
    	Doctor doctor = new Doctor();
    	doctor.setId(id);
    	
    	return getDoctorById(doctor);
    	
    } // getUserById long
    
    
    	
    public Doctor getDoctorById(Doctor doctor) {

    	Doctor doctorOutput = new Doctor();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from ftt.DOCTOR WHERE ID=?");
            
            preparedStatement.setLong(1, doctor.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	doctorOutput.setId(rs.getLong("ID"));
            	doctorOutput.setName(rs.getString("NAME"));
            	doctorOutput.setEmail(rs.getString("EMAIL"));
            	doctorOutput.setCrm(rs.getString("CRM"));
            	doctorOutput.setUnity(rs.getString("UNITY"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorOutput;
    } //getUserById
    
    public String getDbDate() {

    	String output="";
    	
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT now() NOW");
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	output = rs.getString("NOW");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    } //getDbDate
    
} //UserDao