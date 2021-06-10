package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Model.Film;
import Model.FilmDAO;
import Model.FilmStore;

/**
 * Servlet implementation class getFilm
 */
@WebServlet("/getFilm")
public class getFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getFilm() {
		super();
		// TODO Auto-generated constructor stub
	}

	String add;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// parameters are contained in the query string or posted form data
		String strId = request.getParameter("getFilm");
		String strName = request.getParameter("getFilm");
		String strFormat = request.getParameter("format");
		String strData = request.getParameter("data");

		Gson gson = new Gson();
		String outputpage = null;

		// call their methods
		Film f = new Film();
		FilmDAO fd = new FilmDAO();
		FilmStore filmstore = new FilmStore();

		// If and else condition used to choice between id and title
		if ("filmid".equals(strData)) {

			// recalling the method from FilmDAO class
			// parseInt function converts string then returns an integer
			f = fd.getFilmByID(Integer.parseInt(strId));
		} else if ("filmname".equals(strData)) {

			f = fd.getFilmByNAME(strName);

		}

		// Choose Format
		if ("json".equals(strFormat)) {

			// Gson is a Java library that can be used to convert Java Objects into their
			// JSON representation
			String Json = gson.toJson(f);

			// setAttribute() method is used to set an attribute to a servlet
			request.setAttribute("json", Json);
			// Sets the content type of the response being sent to the client
			response.setContentType("application/json");
			// Get the json format from json.jsp file
			outputpage = "View/json.jsp";

		} else if ("xml".equals(strFormat)) {

			// setAttribute() method is used to set an attribute to a servlet
			request.setAttribute("film", f);
			// Sets the content type of the response being sent to the client
			response.setContentType("text/xml");
			outputpage = "View/xml-film.jsp";

		} else if ("text".equals(strFormat)) {

			// setAttribute() method is used to set an attribute to a servlet
			response.setContentType("text/plain");
			// Sets the content type of the response being sent to the client
			request.setAttribute("film", f);
			outputpage = "View/String.jsp";

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(outputpage);
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

		add = request.getParameter("postFilm");

	}

}
