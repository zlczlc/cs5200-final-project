package DAOs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tables.Gametype;
import tables.Movietype;
import tables.User;

public class UserDao {
	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("Etertainment2"); 
	protected EntityManager em = null; 
	protected String findAll = "User.findAll";
	protected String findOne = "User.findOne";
	        
	public User findUser(String UserId){
		User User = null;
		em = factory.createEntityManager();
		em.getTransaction().begin();
		User = em.find(User.class, UserId);
		em.getTransaction().commit();
		em.close();
		return User;
		
	}
	
	public User findUserByUserName(String un){
		User User = null;
		List<User> Users = new ArrayList<User>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		 Query query = em.createNamedQuery(findOne);
		 query.setParameter("name",un);
		 Users = (List<User>)query.getResultList();
		 if (Users.size()!=0){
			 User=Users.get(0);
		 }
		 
		em.getTransaction().commit();
		em.close();
		return User;
		
	}

	 public List<User> findAllUsers(){
		 List<User> Users = new ArrayList<User>();
		 em = factory.createEntityManager();
		 em.getTransaction().begin();
		 Query query = em.createNamedQuery(findAll);
		 Users = (List<User>)query.getResultList();
		 em.getTransaction().commit();
		 em.close();
		 return Users;
	 }
	 
	 public void updateUser(String un, User User){
		// List<User> Users = new ArrayList<User>();
		 em = factory.createEntityManager();
		 em.getTransaction().begin();
		 User.setUsername(un);
		 em.merge(User);
		 Query query = em.createNamedQuery(findAll);
		// Users = (List<User>)query.getResultList();
		 em.getTransaction().commit();
		 em.close();
		 //return Users;
	 }

	
	 public void removeUser(String id){
		// List<User> Users = new ArrayList<User>();
		 em = factory.createEntityManager();
		 User User = null;
		 em.getTransaction().begin();
		 User = em.find(User.class, id);
		 em.remove(User);
		 Query query = em.createNamedQuery(findAll);
		// Users = (List<User>)query.getResultList();
		 em.getTransaction().commit();
		 em.close();
		 //return Users;
	 }
	 
	 public void createUser(User User){
		// List<User> Users = new ArrayList<User>();
		 em = factory.createEntityManager();
		 em.getTransaction().begin();
		 em.persist(User);
		 Query query = em.createNamedQuery(findAll);
		// Users = (List<User>)query.getResultList();
		 em.getTransaction().commit();
		 em.close();
		// return Users;
	 }
	 
	// public void addMovieType(User user, String mt) {
	     
		// em = factory.createEntityManager();
		 //em.getTransaction().begin();
		
		 
		// Movietype movietype =  em.find(Movietype.class, mt);
		 
		
			 
			 
		 
		 //System.out.println(movietype.get(0).getType());
		 //Query query = em.createNamedQuery("Movietype.findOne");
		 //query.setParameter("name",mt);
		 //List<Movietype> movietypes =  query.getResultList();
		 //System.out.println(movietypes.get(0).getType());
		 //Movietype movietype = new Movietype();
		// movietype.setType(mt);
		// Movietype movietype = null;
		 //if (movietypes.size()==0) {
		//	movietype = new Movietype();
		//	movietype.setType(mt);
		//	em.persist(movietype);
			 
		 //}
		 //else{
		//	 movietype=movietypes.get(0);
			 
		 //}
		 
		 //if (user.getMovietypes() == null){
			 
		//	 user.setMovietypes(new ArrayList<Movietype>()) ;
		 //}
		 
		// if (!user.getMovietypes().contains(movietype)) {
	      //  	user.getMovietypes().add(movietype);
	     //}
		 //em.merge(user);
		 //em.getTransaction().commit();
		// em.close();
	    //}
	 
	

	 
	 	
	 	
	 
	 
	 public static void main(String[] args){
		System.out.println("UserDao init");
		
		
		
		UserDao udao=new UserDao();
		
		User user = new User();
		user.setUsername("3433");
		List<Movietype> mts=new ArrayList<Movietype>();
		Movietype mt = new Movietype();
		mt.setType("action");
		mts.add(mt);
		user.setMovietypes(mts);
		udao.updateUser(user.getUsername(), user);
		//udao.findUserByUserName("aa");
		//int n = udao.removeMovietype( user, "3acccccc");
		//System.out.println(n);
		//MovieDao md=new MovieDao();
		//Movietype mt = md.findMovieType("3acccccc");
		//System.out.println(md.findMovieType("3acccccc").getType());
		//if (user.getMovietypes().contains(mt)){
			//System.out.println(md.findMovieType("3acccccc").getType());
			//int n = user.getMovietypes().indexOf(mt);
		//}
		//	System.out.println(n);
		//udao.updateUser(user.getUsername(), user);
		 // System.out.println(user.getMovietypes().get(0).getType());
		 //udao.addMovieType(user, "3acccccc");
		 //udao.addGameType(user, "actionsssssssssssssss");
		 //System.out.println(udao.findUserByUserName("aa").getMovietypes().get(0).getType());
		 //udao.em = udao.factory.createEntityManager();
		 //udao.em.getTransaction().begin();
		
		 //Movietype movietype = em.getReference(Movietype.class, mt);
		 //Query query = udao.em.createNamedQuery("Movietype.findOne");
		 //query.setParameter("name","action");
		 //Movietype movietype = (Movietype) query.getSingleResult();
		 //udao.em.getTransaction().commit();
		 //System.out.println(movietype.getUsers().get(0).getUsername());
		 
	 }
}
