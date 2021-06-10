package Model;

import java.util.Collection;

public interface FilmInfo {
	 public void addFilm (int id, String title, int year, String director, String stars, String review);
	 public void updateFilm (int id, String title, int year, String director, String stars, String review);
	 public void deleteById(int id);
	 public Film getFilmByNAME(String name);
	 public Collection getAllFilms ();
	
	}