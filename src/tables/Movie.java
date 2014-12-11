package tables;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the movie database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Movie.findAll", query="SELECT s FROM Movie s"),
@NamedQuery(name="Movie.findOne", query="SELECT s FROM Movie s where s.moviename=:name")})
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	
	@OneToMany(mappedBy="movie")
	private List<Event> events;
	
	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	private String moviename;
	
	
	private String longDescription;
	private String shortDescription;
	
	private String imageUrl;
	private String officialUrl;
	private String releaseYear;
	private String runTime;
	private String qualityRating;
	private String ratingsBody;
	private String topCast;
	public String getOfficialUrl() {
		return officialUrl;
	}

	public void setOfficialUrl(String officialUrl) {
		this.officialUrl = officialUrl;
	}

	public String getQualityRating() {
		return qualityRating;
	}

	public void setQualityRating(String qualityRating) {
		this.qualityRating = qualityRating;
	}

	public String getRatingsBody() {
		return ratingsBody;
	}

	public void setRatingsBody(String ratingsBody) {
		this.ratingsBody = ratingsBody;
	}

	public String getTopCast() {
		return topCast;
	}

	public void setTopCast(String topCast) {
		this.topCast = topCast;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String rating;
	//bi-directional many-to-many association to Movietype
	@ManyToMany
	@JoinTable(
		name="movie_type"
		, joinColumns={
			@JoinColumn(name="movieId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="movieType")
			})
	private List<Movietype> movietypes;

	//bi-directional many-to-many association to User
	
	
	@ManyToMany(mappedBy="movies")
	private List<User> users;

	public Movie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMoviename() {
		return this.moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public List<Movietype> getMovietypes() {
		return this.movietypes;
	}

	public void setMovietypes(List<Movietype> movietypes) {
		this.movietypes = movietypes;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}