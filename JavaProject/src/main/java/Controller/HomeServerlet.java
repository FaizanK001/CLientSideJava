package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

import Model.Film;
import Model.FilmDAO;
import Model.FilmStore;

/**
 * Servlet implementation class HomeServerlet
 */
@WebServlet("/HomeServlet")
public class HomeServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String format;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServerlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Cache-Control", "no-cache");

		// Connect to the Database to get all Films

		PrintWriter pw = response.getWriter();
		format = request.getParameter("data-format");
		System.out.println(format);

		FilmDAO dao = new FilmDAO();
		ArrayList<Film> films = dao.getAllFilms();

		FilmStore fs = new FilmStore();

		// Convert Arraylist to json
		Gson gson = new Gson();

		// empty variable to be used to get address of jsp folder and data used to
		// convert the arraylist to json
		String data = "", address = "";
// json format condtion 
		if (format.equals("json")) {

			data = gson.toJson(films);
			address = "json";
			response.setContentType("application/json");
			request.setAttribute("json", data);

			RequestDispatcher dispatcher = request.getRequestDispatcher("View/" + address + ".jsp");
			dispatcher.forward(request, response);

		}
		// Used JABX to retrieve all film in the xml fromat
		else if (format.equals("xml")) {

			response.setContentType("text/xml");
			fs.setAllFilm(films);
			try {
				JAXBContext con = JAXBContext.newInstance(FilmStore.class);
				Marshaller marshaller = con.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshaller.marshal(fs, pw);
			} catch (Exception e) {
				System.out.print(e);
			}
// String format condition
		} else if (format.equals("text")) {
			address = "allString";
			response.setContentType("text/plain");
			request.setAttribute("film", films);

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("View/" + address + ".jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
