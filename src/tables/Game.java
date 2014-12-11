package tables;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the game database table.
 * 
 */
@Entity
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String gamename;

	//bi-directional many-to-one association to Gametype
	@ManyToOne
	@JoinColumn(name="gameType")
	private Gametype gametype;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="games")
	private List<User> users;

	public Game() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGamename() {
		return this.gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public Gametype getGametype() {
		return this.gametype;
	}

	public void setGametype(Gametype gametype) {
		this.gametype = gametype;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}