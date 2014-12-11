package tables;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the concert database table.
 * 
 */
@Entity
@NamedQuery(name="Concert.findAll", query="SELECT c FROM Concert c")
public class Concert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String artist;

	private String concertname;

	private String locatial;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="concerts")
	private List<User> users;

	public Concert() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArtist() {
		return this.artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getConcertname() {
		return this.concertname;
	}

	public void setConcertname(String concertname) {
		this.concertname = concertname;
	}

	public String getLocatial() {
		return this.locatial;
	}

	public void setLocatial(String locatial) {
		this.locatial = locatial;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}