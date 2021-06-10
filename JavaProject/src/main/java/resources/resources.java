package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Model.Film;
import Model.FilmDAO;

@Path("/filmsList")

public class resources {

	// Return the list of FIlm for applications
	// @Produces annotation is used to specify the MIME media types or
	// representations
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Film> getFilms() {

		// Film class object
		List<Film> films = new ArrayList<Film>();
		//
		films = FilmDAO.getSingletonCam().getAllFilms();

		return films;
	}

	// @Produces annotation is used to specify the MIME media types or
	// representations
	@GET
	@Path("/{filmById}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Film getFilmByID(@PathParam("filmById") int id) {

		Film film = new Film();
		film = FilmDAO.getSingletonCam().getFilmByID(id);
		FilmDAO.getSingletonCam().getFilmByID(id);
		return film;

	}

// Send the film data to the database for the application in json or xml format
	// @Produces annotation is used to specify the MIME media types or
	// representations
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Film addFilm(Film f) {

		Film film = new Film();

		FilmDAO.getSingletonCam().addFilm(f.getId(), f.getTitle(), f.getYear(), f.getDirector(), f.getStars(),
				f.getReview());

		film = FilmDAO.getSingletonCam().getFilmByID(f.getId());

		return film;
	}

// send the request to delete the film by id
	@DELETE
	@Path("/{filmId}")
	public Film DeleteById(@PathParam("filmId") int id) {

		Film film = new Film();

		film = FilmDAO.getSingletonCam().getFilmByID(id);
		FilmDAO.getSingletonCam().deleteById(id);
		return film;
	}

// send the requested to update the film in the application
	// @Produces annotation is used to specify the MIME media types or
	// representations
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Film updateFilm(Film f) {

		Film film = new Film();

		FilmDAO.getSingletonCam().updateFilm(f.getId(), f.getTitle(), f.getYear(), f.getDirector(), f.getStars(),
				f.getReview());

		film = FilmDAO.getSingletonCam().getFilmByID(f.getId());

		return film;
	}

}
