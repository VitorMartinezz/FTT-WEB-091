package ec.ftt.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.DoctorDao;
import ec.ftt.model.Doctor;

/**
 * Servlet implementation class DoctorApi
 */
@WebServlet("/DoctorApi")
public class DoctorApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoctorApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		String doctorId = request.getParameter("doctor-id");
		DoctorDao doctorDao = new DoctorDao();

		if (doctorId != null) {
			long id = Long.valueOf(doctorId);

			Doctor doctor = doctorDao.getDoctorById(id);
			Gson gson = new Gson();
			response.getWriter().append(gson.toJson(doctor));

		} else {

			List<Doctor> doctors = doctorDao.getAllDoctor();
			Gson gson = new Gson();
			response.getWriter().append(gson.toJson(doctors));

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		Doctor d = new Doctor(request.getParameter("doctor-id"), request.getParameter("doctor-name"),
				request.getParameter("doctor-email"), request.getParameter("doctor-crm"),
				request.getParameter("doctor-unity"));

		DoctorDao doctorDao = new DoctorDao();

		doctorDao.addDoctor(d);

		Gson gson = new Gson();
		response.getWriter().append(gson.toJson(d));
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json"); // mimeType -
														// https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		Doctor d = new Doctor(request.getParameter("doctor-id"), request.getParameter("doctor-name"),
				request.getParameter("doctor-email"), request.getParameter("doctor-crm"),
				request.getParameter("doctor-unity"));
		DoctorDao doctorDao = new DoctorDao();

		doctorDao.updateDoctor(d);

		Gson gson = new Gson();
		response.getWriter().append(gson.toJson(d));
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setStatus(418); // 200 - OK - Padrão (Default)

		if (request.getParameter("doctor-id") == null)
			response.sendError(407, "Informe o ID do usuário a ser retornado!!!");
		else {
			Long doctorId = Long.valueOf(request.getParameter("doctor-id"));

			DoctorDao doctorDao = new DoctorDao();

			doctorDao.deleteDoctor(doctorId);

			response.getWriter().append(request.getParameter("doctor-id") + " User removido");
		}
	}
}
