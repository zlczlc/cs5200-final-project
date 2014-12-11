package DAOs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tables.Movie;
import tables.Movietype;
import tables.User;


public class MovieDao {
	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("Etertainment2"); 
	protected EntityManager em = null; 
	protected String findAll = "Movie.findAll";
	protected String findOne = "Movie.findOne";
	
	public Movie findMovie(int movieId){
		Movie movie = null;
		em = factory.createEntityManager();
		em.getTransaction().begin();
		movie = em.find(Movie.class, movieId);
		em.getTransaction().commit();
		em.close();
		return movie;
		
	}
	
	
	public Movie findMovieByMovieName(String un){
		Movie movie = null;
		List<Movie> movies = new ArrayList<Movie>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		 Query query = em.createNamedQuery(findOne);
		 query.setParameter("name",un);
		 movies = (List<Movie>)query.getResultList();
		 if (movies.size()!=0){
			 movie=movies.get(0);
		 }
		 
		em.getTransaction().commit();
		em.close();
		return movie;
		
	}
	
	public List<Movie> findAllMovies(){
		 List<Movie> movies = new ArrayList<Movie>();
		 em = factory.createEntityManager();
		 em.getTransaction().begin();
		 Query query = em.createNamedQuery(findAll);
		 movies = (List<Movie>)query.getResultList();
		 em.getTransaction().commit();
		 em.close();
		 return movies;
	 }
	
	public List<Movietype> findAllMovietypes(){
		 List<Movietype> movietypes = new ArrayList<Movietype>();
		 em = factory.createEntityManager();
		 em.getTransaction().begin();
		 Query query = em.createNamedQuery("Movietype.findAll");
		 movietypes = (List<Movietype>)query.getResultList();
		 em.getTransaction().commit();
		 em.close();
		 return movietypes;
	 }
	 
	 public void updateMovie(int id, Movie movie){
		// List<Movie> Movies = new ArrayList<Movie>();
		 em = factory.createEntityManager();
		 em.getTransaction().begin();
		 movie.setId(id);
		 em.merge(movie);
		 //Query query = em.createNamedQuery(findAll);
		// Users = (List<User>)query.getResultList();
		 em.getTransaction().commit();
		 em.close();
		 //return Users;
	 }

	
	 public void removeMovie(int id){
		// List<Movie> Movies = new ArrayList<Movie>();
		 em = factory.createEntityManager();
		 Movie movie = null;
		 em.getTransaction().begin();
		 movie = em.find(Movie.class, id);
		 em.remove(movie);
		 //Query query = em.createNamedQuery(findAll);
		// Movies = (List<Movie>)query.getResultList();
		 em.getTransaction().commit();
		 em.close();
		 //returMovieers;
	 }
	 
	 public void createMovie(Movie movie){
		// List<Movie> Movies = new ArrayList<Movie>();
		 em = factory.createEntityManager();
		 em.getTransaction().begin();
		 Movie movie1 = em.find(Movie.class, movie.getId());
		 if (movie1==null)
			 em.persist(movie);
		 //else
			 //em.merge(movie);
		 //Query query = em.createNamedQuery(findAll);
		// Movies = (List<Movie>)query.getResultList();
		 em.getTransaction().commit();
		 em.close();
		// returMovieers;
	 }
	
	 public Movietype findMovieType(String mt){
			Movietype movietype = null;
			em = factory.createEntityManager();
			em.getTransaction().begin();
			movietype = em.find(Movietype.class, mt);
			em.getTransaction().commit();
			em.close();
			return movietype;
			
		}
	 
	 public List<Movie> findMovieByType(String mt){
		 	Movietype movietype = null;
			em = factory.createEntityManager();
			em.getTransaction().begin();
			movietype = em.find(Movietype.class, mt);
			em.getTransaction().commit();
			em.close();
			List <Movie> movies= movietype.getMovies();
			return movies;
			
		}
	
	 
	 public void addMovieType(Movie movie, String mt) {
	     
		 em = factory.createEntityManager();
		 em.getTransaction().begin();
		
		 
		// Movietype movietype =  em.find(Movietype.class, mt);
		 
		
			 
			 
		 
		 //System.out.println(movietype.get(0).getType());
		 Query query = em.createNamedQuery("Movietype.findOne");
		 query.setParameter("name",mt);
		 List<Movietype> movietypes =  query.getResultList();
		 //System.out.println(movietypes.get(0).getType());
		 //Movietype movietype = new Movietype();
		// movietype.setType(mt);
		 Movietype movietype = null;
		 if (movietypes.size()==0) {
			movietype = new Movietype();
			movietype.setType(mt);
			em.persist(movietype);
			 
		 }
		 else{
			 movietype=movietypes.get(0);
			 
		 }
		 //em.merge(movie);
		 em.getTransaction().commit();
		 em.close();
		 //if (movietype.getMovies()==null) {
		//	 movie
		 //}
		 //System.out.println(movietype.getType());
		 if (!movie.getMovietypes().contains(movietype)) {
	        	movie.getMovietypes().add(movietype);
	        	//movietype.getMovies().add(movie);
	     }
		 
	    }
	 private String moviename;
		
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Movie m=new Movie();
		m.setId(106);
		Movietype mt = new Movietype();
		mt.setType("action");
		//m.getMovietypes().add(mt);
		
		MovieDao mdao=new MovieDao();
		
		
		mdao.createMovie(m);
		mdao.addMovieType(m, "action");
	}

}
