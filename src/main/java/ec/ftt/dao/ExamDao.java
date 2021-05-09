package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ec.ftt.model.Exam;
import ec.ftt.util.DBUtil;

public class ExamDao {

    private Connection connection;

    public ExamDao() {
        connection = DBUtil.getConnection();
    } //UserDao

    public void addExam(Exam exam) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO ftt.EXAM (ID, STUDY, TYPE, DATE) VALUES (?, ?, ?, ?)");
            
            preparedStatement.setLong(1, exam.getId());
            preparedStatement.setString(2, exam.getStudy());
            preparedStatement.setString(3, exam.getType());
            preparedStatement.setString(4, exam.getDate());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //addUser
    
    public void deleteExam(Long id) {
    	
    	Exam exam = new Exam();
    	exam.setId(id);
    	
    	deleteExam(exam);
    	
    } // deleteUser long

    public void deleteExam(Exam exam) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM ftt.EXAM WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, exam.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deleteUser

    public void updateExam(Exam exam) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE ftt.EXAM SET STUDY=?, " 
                    		                          + "TYPE=?, " 
                    		                          + "DATE=? " 
                                               + "WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setString(1, exam.getStudy());
            preparedStatement.setString(2, exam.getType());
            preparedStatement.setString(3, exam.getDate());
            preparedStatement.setLong(4, exam.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updateUser

    public List<Exam> getAllExams() {
        
    	List<Exam> examList = new ArrayList<Exam>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ftt.EXAM");
            while (rs.next()) {
                
            	Exam exam = new Exam();
                
            	exam.setId(rs.getLong("ID"));
            	exam.setStudy(rs.getString("STUDY"));
            	exam.setType(rs.getString("TYPE"));
            	exam.setDate(rs.getString("DATE"));
            	examList.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return examList;
    } //getAllUser

    public Exam getExamById(Long id) {
    	
    	Exam exam = new Exam();
    	exam.setId(id);
    	
    	return getExamById(exam);
    	
    } // getUserById long
    
    
    	
    public Exam getExamById(Exam exam) {

    	Exam ExamOutput = new Exam();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from ftt.EXAM WHERE ID=?");
            
            preparedStatement.setLong(1, exam.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	ExamOutput.setId(rs.getLong("ID"));
            	ExamOutput.setStudy(rs.getString("STUDY"));
            	ExamOutput.setType(rs.getString("TYPE"));
            	ExamOutput.setDate(rs.getString("DATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ExamOutput;
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