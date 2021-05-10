package ec.ftt.api;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.ExamDao;
import ec.ftt.model.Exam;

/**
 * Servlet implementation class ExamApi
 */
@WebServlet("/ExamApi")
public class ExamApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamApi() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
	      setAccessControlHeaders(resp);
	      resp.setStatus(HttpServletResponse.SC_OK);
	  }
	
	 private void setAccessControlHeaders(HttpServletResponse resp) {
	      resp.setHeader("Access-Control-Allow-Origin", "*");
	      resp.setHeader("Access-Control-Allow-Methods", "GET");
	      resp.setHeader("Access-Control-Allow-Methods", "POST");
	      resp.setHeader("Access-Control-Allow-Methods", "PUT");
	      resp.setHeader("Access-Control-Allow-Methods", "DELETE");
	      resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
	  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setStatus(200); //200 - OK - Padrão (Default)
		setAccessControlHeaders(response);
		String examId = request.getParameter("exam-id");
		ExamDao examDao = new ExamDao();
		
	    if(examId != null) {
	    	long id = Long.valueOf(examId);
	    	
	        Exam exam = examDao.getExamById(id);
	     	Gson gson = new Gson();
	    	response.getWriter().append(gson.toJson(exam));
	    	
	    } else {
	    	
	    	List<Exam> exams = examDao.getAllExams();
	    	Gson gson = new Gson();
	    	response.getWriter().append(gson.toJson(exams));

	    } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response);
		Exam ex = new Exam(
				request.getParameter("exam-id"),
				request.getParameter("exam-study"),
				request.getParameter("exam-type"),
				getDateNow()
				);
		
		ExamDao examDao = new ExamDao();
		
		examDao.addExam(ex);
		
		Gson gson = new Gson();
		
		response.getWriter().append(gson.toJson(ex));
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response);
		response.setContentType("application/json"); //mimeType - https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		Exam ex = new Exam(
				request.getParameter("exam-id"),
				request.getParameter("exam-study"),
				request.getParameter("exam-type"),
				getDateNow()
				);
		ExamDao examDao = new ExamDao();
		
		examDao.updateExam(ex);
		
		Gson gson = new Gson();
		
		response.getWriter().append(gson.toJson(ex));
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response);
		if (request.getParameter("exam-id") == null)
			 response.sendError(407, "Informe o ID do usuário a ser retornado!!!" );
		else {
		Long examId = Long.valueOf(request.getParameter("exam-id"));
		
		ExamDao examDao= new ExamDao();
		
		examDao.deleteExam(examId);
		
		response.getWriter().append(request.getParameter("exam-id") + " User removido");
		}
	}
	
	private String getDateNow() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   return dtf.format(now).toString();  
	}

}
