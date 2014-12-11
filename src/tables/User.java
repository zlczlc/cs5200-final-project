package tables;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="User.findAll", query="SELECT s FROM User s"),
@NamedQuery(name="User.findOne", query="SELECT s FROM User s where s.username=:name")})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	@Temporal(TemporalType.DATE)
	private Date dateofbirth;

	private String email;

	private String firstname;

	public List<Event> getEvents() {
		return events;
	}



	public void setEvents(List<Event> events) {
		this.events = events;
	}


	private String lastname;

	private String password;

	
	@ManyToOne
	@JoinColumn(name="zipcode")
	private City city;
	

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="friendship"
		, joinColumns={
			@JoinColumn(name="friend2Id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="friend1Id")
			}
		)
	private List<User> users1 = new ArrayList<User>();

	public City getCity() {
		return city;
	}



	public void setCity(City city) {
		this.city = city;
	}


	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="users1")
	private List<User> users2 =new ArrayList<User>();

	//bi-directional many-to-many association to Concert
	@ManyToMany
	@JoinTable(
		name="preferconcert"
		, joinColumns={
			@JoinColumn(name="Username")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ConcertId")
			}
		)
	private List<Concert> concerts;

	//bi-directional many-to-many association to Game
	@ManyToMany
	@JoinTable(
		name="prefergame"
		, joinColumns={
			@JoinColumn(name="Username")
			}
		, inverseJoinColumns={
			@JoinColumn(name="GameId")
			}
		)
	private List<Game> games;

	//bi-directional many-to-many association to Gametype
	@ManyToMany
	@JoinTable(
		name="prefergametype"
		, joinColumns={
			@JoinColumn(name="Username")
			}
		, inverseJoinColumns={
			@JoinColumn(name="GameType")
			}
		)
	private List<Gametype> gametypes;

	//bi-directional many-to-many association to Movie
	@ManyToMany
	@JoinTable(
		name="prefermovie"
		, joinColumns={
			@JoinColumn(name="Username")
			}
		, inverseJoinColumns={
			@JoinColumn(name="MovieId")
			}
		)
	private List<Movie> movies = new ArrayList<Movie>();

	//bi-directional many-to-many association to Movietype
	
	@ManyToMany
	@JoinTable(
		name="prefermovietype"
		, joinColumns={
			@JoinColumn(name="username")
			}
		, inverseJoinColumns={
			@JoinColumn(name="movietype")
			}
		)
	
	
	
	private List<Movietype> movietypes = new ArrayList<Movietype>();

	
	
	@OneToMany(mappedBy="user")
	private List<Event> events;
	
	
	
	public User() {
	}

	

	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

	public List<Concert> getConcerts() {
		return this.concerts;
	}

	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}

	public List<Game> getGames() {
		return this.games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Gametype> getGametypes() {
		return this.gametypes;
	}

	public void setGametypes(List<Gametype> gametypes) {
		this.gametypes = gametypes;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Movietype> getMovietypes() {
		return this.movietypes;
	}

	public void setMovietypes(List<Movietype> movietypes) {
		this.movietypes = movietypes;
	}
	
	
	
	public int removeMovietype(User user, String mt){
 		
 		//MovieDao md=new MovieDao();
 		//Movietype movietype = md.findMovieType(mt);
 		for (int i=0; i<user.getMovietypes().size();i++)
 		{
 			if (user.getMovietypes().get(i).getType().equals(mt)){
 				user.getMovietypes().remove(i);
 				//this.updateUser(user.getUsername(), user);
 				return 1;
 			}
 			
 		}
 		return 0;
 		
 	}
 	
 	
 	public int removeMovie(User user, int Id){
 		
 		//MovieDao md=new MovieDao();
 		//Movietype movietype = md.findMovieType(mt);
 		for (int i=0; i<user.getMovies().size();i++)
 		{
 			if (user.getMovies().get(i).getId() == Id){
 				user.getMovies().remove(i);
 				//this.updateUser(user.getUsername(), user);
 				return 1;
 			}
 			
 		}
 		return 0;
 		
 	}
	
	
	
	
	
	
	
	
	
	
	

}