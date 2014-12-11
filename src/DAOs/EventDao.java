package DAOs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tables.Event;
import tables.User;




public class EventDao {
	
	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("Etertainment2"); 
	protected EntityManager em = null; 
	
	
	public Event findEvent(int Id){
		Event event = null;
		em = factory.createEntityManager();
		em.getTransaction().begin();
		event= em.find(Event.class, Id);
		em.getTransaction().commit();
		em.close();
		return event;
	}
	
	
	 public void updateEvent(int id, Event event){
			// List<Event> Events = new ArrayList<Event>();
			 em = factory.createEntityManager();
			 em.getTransaction().begin();
			 event.setId(id);
			 em.merge(event);
			 //Query query = em.createNamedQuery(findAll);
			// Events = (List<Event>)query.getResultList();
			 em.getTransaction().commit();
			 em.close();
			 //return Events;
		 }

		
		 public void removeEvent(int id){
			// List<Event> Events = new ArrayList<Event>();
			 em = factory.createEntityManager();
			 Event event = null;
			 em.getTransaction().begin();
			 event = em.find(Event.class, id);
			 em.remove(event);
			 //Query query = em.createNamedQuery(findAll);
			// Events = (List<Event>)query.getResultList();
			 em.getTransaction().commit();
			 em.close();
			 //return Events;
		 }
		 
		 public void createEvent(Event event){
			// List<Event> Events = new ArrayList<Event>();
			 em = factory.createEntityManager();
			 em.getTransaction().begin();
			 em.persist(event);
			 //Query query = em.createNamedQuery(findAll);
			// Events = (List<Event>)query.getResultList();
			 em.getTransaction().commit();
			 em.close();
			// return Events;
		 }

}
