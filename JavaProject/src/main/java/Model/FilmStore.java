package Model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace ="Model")

public class FilmStore {

	@XmlElementWrapper(name="films")
	@XmlElement(name = "film")
	
	private ArrayList<Film> films;
	
	Film film = new Film();
	
	public void setAllFilm(ArrayList<Film> films) {
		this.films =films;
	}
	public  void setFilm(Film film) {
		this.film = film;
	}
	public Film getFilm() {
		return film;
		
	}
public ArrayList<Film> getFilms(){
	return films;
	
}
}
