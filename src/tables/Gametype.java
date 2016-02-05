package tables;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gametype database table.
 * 
 */
@Entity
@NamedQuery(name="Gametype.findAll", query="SELECT g FROM Gametype g")
public class Gametype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String type;

	//bi-directional many-to-one association to Game
	@OneToMany(mappedBy="gametype")
	private List<Game> games;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="gametypes")
	private List<User> users;

	public Gametype() {
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Game> getGames() {
		return this.games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Game addGame(Game game) {
		getGames().add(game);
		game.setGametype(this);

		return game;
	}

	public Game removeGame(Game game) {
		getGames().remove(game);
		game.setGametype(null);

		return game;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}