package DAOs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.net.ssl.HttpsURLConnection;









import tables.City;
import tables.State;

public class LocationDao {

	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("Etertainment2"); 
	protected EntityManager em = null; 
	//protected String findAll = "User.findAll";
	//protected String findOne = "User.findOne";
	String cityname="";
	String statename="";
	public int createLocation(String zipcode){
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		City city = em.find(City.class, zipcode);
		
		if (city!=null){
			
			em.getTransaction().commit();
			em.close();
			return 0;
		}
		
		String urlApi ="https://www.zipcodeapi.com/rest/3qQc9YXt1G5dmTvpWks8h93e6oVT1gq4HhhQgejhVpuijZHgk9oBjsQqRLjS2JXJ/info.json/{{ZIP}}/degrees";
		String urlStr = urlApi.replace("{{ZIP}}", zipcode);
		System.out.println(urlStr);
		
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line;
			String json = "";
			while((line = buffer.readLine()) != null) {
				json += line;
			}
			
			JSONParser parser = new JSONParser();
			//return json;
			
			JSONObject root = (JSONObject) parser.parse(json);
			cityname = (String) root.get("city");
			statename = (String) root.get("state");
			//if (cityname==null) return -1;
			//city.setState(state);
			//System.out.println(cityname);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			em.getTransaction().commit();
			em.close();
			return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			em.getTransaction().commit();
			em.close();
			return -1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			em.getTransaction().commit();
			em.close();
			return -1;
		}		
		//return null;
		
		State state =null;
		
		
		//state.getCities().add(city);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		state=	(State)	em.find(State.class, statename);
		if (state==null)
			{
			state=new State();
			state.setId(statename);
			em.persist(state);
		}
		
		city= new City();
		city.setZipcode(zipcode);
		city.setCity(cityname);
		city.setState(state);
		
		em.persist(city);
		//em.merge(state);
		
		
		em.getTransaction().commit();
		em.close();
		return 1;
	}
	
	
	
	
	
	// find delete
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 LocationDao d=new  LocationDao ();
		int a = d.createLocation("02114");
		System.out.println(a);

	}

}
