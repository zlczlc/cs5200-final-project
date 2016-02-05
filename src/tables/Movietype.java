package tables;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the movietype database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Movietype.findAll", query="SELECT s FROM Movietype s"),
@NamedQuery(name="Movietype.findOne", query="SELECT s FROM Movietype s where s.type=:name")})
public class Movietype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String type;

	//bi-directional many-to-many association to Movie
	
	@ManyToMany
	(mappedBy="movietypes")
	private List<Movie> movies;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="movietypes")
	private List<User> users;

	public Movietype() {
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}