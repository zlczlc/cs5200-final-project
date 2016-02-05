package websevice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

import tables.Movie;
import tables.Movietype;
import tables.Showtimes;
import tables.User;
import DAOs.MovieDao;
import DAOs.UserDao;

import com.sun.net.ssl.HttpsURLConnection;

import java.util.ArrayList;
import java.util.Date; 
import java.util.Calendar; 
import java.util.List;
import java.text.SimpleDateFormat; 


public class MovieService {
	
	private String key = "dc3gan7yrxq6wfaf2776fbb3";
	//private String key="9gnk9dw7rbpzg55wmk8qamn9";
	
	public List<Movie> findMovieByZipcode(String zipcode, String urlApi, String function){
		
		String date=this.getDate();
		if (urlApi==null)
			urlApi ="http://data.tmsapi.com/v1/movies/showings?startDate={{DATE}}&numDays=15&zip={{ZIP}}&radius=15&imageSize=Lg&api_key="+key;
		String urlStr = urlApi.replace("{{ZIP}}", zipcode);
		urlStr=urlStr.replace("{{DATE}}", date);
		System.out.println(urlStr);
		List<Movie> AllMovies = new ArrayList<Movie>();
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
			System.out.println(json);
			//JSONObject root = (JSONObject) parser.parse(json);
			//JSONArray array = (JSONArray) root.get("array");
			JSONArray array = (JSONArray) parser.parse(json);
			System.out.println(array);
			JSONObject data=null;
			JSONObject O = null;
			JSONArray A = null;
			
			MovieDao mdao= new MovieDao();
			for (int i=0;i<Math.min(array.size(),200);i++){
				data = (JSONObject) array.get(i);
				System.out.println("data: "+data);
				Movie m = new Movie();
				m.setId(Integer.parseInt((String)(data.get("rootId"))));
				m.setMoviename((String)(data.get("title")));
				m.setLongDescription("discription");
				m.setShortDescription("discription");
				m.setLongDescription(((String)(data.get("longDescription"))));
				m.setShortDescription(((String)(data.get("shortDescription"))));
				m.setReleaseYear("");
				m.setReleaseYear((String)(data.get("releaseDate")));
				m.setRunTime((String)(data.get("runTime")));
				m.setOfficialUrl((String)(data.get("officialUrl")));
				
				System.out.println("data: "+data);
				A =  (JSONArray) data.get("ratings");
				if(A!=null){
					O = (JSONObject) A.get(0);
					//O =  (JSONObject) data.get("ratings");
					//System.out.println(i);
					//System.out.println("ratings: "+A);
				
					m.setRating((String) O.get("code"));
					//m.setRatingsBody((String) O.get("body"));
				//m.setImageUrl((String)(data.get("rootId")));
				}
				else{ m.setRating("Class"); 
				//m.setRatingsBody("not available");
				}
				//m.setTopCast("");
				
				A =  (JSONArray) data.get("topCast");
				if (A!=null){
					String tc="";
					for (int j = 0; j< A.size();j++){
						tc = tc+", "+(String) A.get(j);
					}
					tc=tc.replaceFirst(",", "");
					
					m.setTopCast(tc);
				}
				else m.setTopCast("not available");
				
				O = (JSONObject) data.get("qualityRating");
				if (O!=null){
					m.setQualityRating((String) O.get("value"));
					m.setRatingsBody((String) O.get("ratingsBody"));
				}
				else {
					m.setQualityRating("??");
					m.setRatingsBody("");
				}
				
				O = (JSONObject) data.get("preferredImage");
				if (O!=null){
					m.setImageUrl((String) O.get("uri"));
				}
				
				
				m.setMovietypes((List<Movietype>)new ArrayList<Movietype>());
				//mdao.createMovie(m);
				
				A =  (JSONArray) data.get("genres");
				if (A!=null){
					for (int j = 0; j< A.size();j++){
						//System.out.println((String) A.get(j));
						mdao.addMovieType(m,(String) A.get(j));
					}
				
				}
				if (function.equals("read"))
					mdao.updateMovie(m.getId(),m);
				//mdao.createMovie(m);
				AllMovies.add(m);
				
			}
			//cityname = (String) root.get("city");
			//statename = (String) root.get("state");
			
			//city.setState(state);
			//System.out.println(cityname);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			return AllMovies;
			
		}
		//return AllMovies;
		
	}
	
	
	
	
	public List<Showtimes> searchShowTimes(String zipcode,String numOfDays, String radius,int movieId){
		
		String date=this.getDate();
		System.out.println(date);
		//String urlApi = "http://data.tmsapi.com/v1/movies/{{movieId}}/showings?startDate={{DATE}}&numDays={{numOfDays}}&zip=02115&radius={{radius}}&api_key="+key;
		String urlApi ="http://data.tmsapi.com/v1/movies/showings?startDate={{DATE}}&numDays={{numOfDays}}&zip={{ZIP}}&radius={{radius}}&imageSize=Lg&api_key="+key;
		String urlStr = urlApi.replace("{{ZIP}}", zipcode);
		urlStr=urlStr.replace("{{DATE}}", date);
		urlStr = urlStr.replace("{{numOfDays}}", numOfDays);
		urlStr = urlStr.replace("{{radius}}", radius);
		//urlStr = urlStr.replace("{{movieId}}", String.valueOf(movieId));
		
		System.out.println(urlStr);
		List<Showtimes> showtimes = new ArrayList<Showtimes>();
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
			System.out.println(json);
			//JSONObject root = (JSONObject) parser.parse(json);
			//JSONArray array = (JSONArray) root.get("array");
			JSONArray array = (JSONArray) parser.parse(json);
			System.out.println(array);
			JSONObject data=null;
			JSONObject O = null;
			JSONArray A = null;
			
			Showtimes st = new Showtimes();
			//MovieDao mdao= new MovieDao();
			for (int i=0;i<Math.min(array.size(),200);i++){
				data = (JSONObject) array.get(i);
				System.out.println("data: "+data);
				//Movie m = new Movie();
				//m.setId(Integer.parseInt((String)(data.get("rootId"))));
				
				//m.setMoviename((String)(data.get("title")));
				//m.setLongDescription(((String)(data.get("longDescription"))));
				//m.setShortDescription(((String)(data.get("shortDescription"))));
				//m.setReleaseYear((String)(data.get("releaseDate")));
				//m.setRunTime((String)(data.get("runTime")));
				//m.setOfficialUrl((String)(data.get("officialUrl")));
				
				System.out.println("data: "+data);
				if (Integer.parseInt((String)(data.get("rootId")))==movieId){
				
				
				A =  (JSONArray) data.get("showtimes");
				if (A!=null){
					
					for (int j = 0; j< A.size();j++){
						st = new Showtimes();
						O = (JSONObject) A.get(j);
						JSONObject O1 = (JSONObject) O.get("theatre");
						st.setTheatreId((String)(O1.get("id")) );
						st.setTheatrename((String)(O1.get("name")) );
						st.setDatetime((String)(O.get("dateTime")) );
						st.setUrl((String)(O.get("ticketURI")) );
						showtimes.add(st);
					}
					
				}
		
				
				break;
				
				}
				
			}
			//cityname = (String) root.get("city");
			//statename = (String) root.get("state");
			
			//city.setState(state);
			//System.out.println(cityname);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			return showtimes;
			
		}
		//return AllMovies;
		
	}
	
	
	
	public String getDateF(int n){
		Date now = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		int d = now.getDate();
		d= d+n;
		now.setDate(d);
		String date = dateFormat.format( now ); 
		System.out.println(date); 
		return date;
	}
	
	
	
	
	
	
	
	
	public String getDate(){
		Date now = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 


		String date = dateFormat.format( now ); 
		System.out.println(date); 
		return date;
	}
	
	
	public List<Movie> findAll(String zipcode, String numOfDays, String radius){
		
		
		String urlApi ="http://data.tmsapi.com/v1/movies/showings?startDate={{DATE}}&numDays={{numOfDays}}&zip={{ZIP}}&radius={{radius}}&imageSize=Lg&api_key="+key;
		//urlApi = urlApi.replace("{{ZIP}}", zipcode);
		urlApi = urlApi.replace("{{numOfDays}}", numOfDays);
		urlApi = urlApi.replace("{{radius}}", radius);
		
		//urlStr=urlStr.replace("{{DATE}}", date);
		
		return findMovieByZipcode(zipcode, urlApi, "");
		
	}
	
	public List<Movie> findBypreference(String zipcode, String numOfDays, String radius, User u){
		
		
		String urlApi ="http://data.tmsapi.com/v1/movies/showings?startDate={{DATE}}&numDays={{numOfDays}}&zip={{ZIP}}&radius={{radius}}&imageSize=Lg&api_key="+key;
		//urlApi = urlApi.replace("{{ZIP}}", zipcode);
		urlApi = urlApi.replace("{{numOfDays}}", numOfDays);
		urlApi = urlApi.replace("{{radius}}", radius);
		
		//urlStr=urlStr.replace("{{DATE}}", date);
		List<Movie> ms= new ArrayList<Movie>();
		List<Movie> ms1= new ArrayList<Movie>();
		ms = findMovieByZipcode(zipcode, urlApi, "");
		List<Movietype> mtsU = u.getMovietypes();
		for (int i = 0; i<ms.size();i++){
			Movie m = ms.get(i);
			for (Movietype mtU:mtsU){
				for (Movietype mt:m.getMovietypes())
				if ((mtU.getType().equals(mt.getType()))){
					ms1.add(ms.get(i));
					
				}
			}	
			
		}
		return ms1;
		
	}
	
	
public List<Movie> searchOne(String zipcode, String numOfDays, String radius , String keyword){
		
		
		String urlApi ="http://data.tmsapi.com/v1/movies/showings?startDate={{DATE}}&numDays={{numOfDays}}&zip={{ZIP}}&radius={{radius}}&imageSize=Lg&api_key="+key;
		//urlApi = urlApi.replace("{{ZIP}}", zipcode);
		urlApi = urlApi.replace("{{numOfDays}}", numOfDays);
		urlApi = urlApi.replace("{{radius}}", radius);
		
		//urlStr=urlStr.replace("{{DATE}}", date);
		List<Movie> ms= new ArrayList<Movie>();
		List<Movie> msResult= new ArrayList<Movie>();
		ms = findMovieByZipcode(zipcode, urlApi, "");
		//List<Movietype> mtsU = u.getMovietypes();
		for (int i = 0; i<ms.size();i++){
			Movie m = ms.get(i);
			if (m.getMoviename().contains(keyword)){
				msResult.add(m);
				
			}
			
		}
		return msResult;
		
	}
	
	
	
	
	
	
	
public List<Movie> findAllFutureRelease(String zipcode, String numOfDays, String radius){
		
		
		String urlApi ="http://data.tmsapi.com/v1/movies/futureReleases?releaseDate={{DATE}}&numDays=90&country=USA&imageSize=Lg&api_key="+key;
		//urlApi = urlApi.replace("{{ZIP}}", zipcode);
		//urlApi = urlApi.replace("{{numOfDays}}", numOfDays);
		//urlApi = urlApi.replace("{{radius}}", radius);
		
		//urlStr=urlStr.replace("{{DATE}}", date);
		
		return findMovieByZipcode(zipcode, urlApi, "");
		
	}
	
	public List<Movie> findBypreferenceFutureRelease(String zipcode, String numOfDays, String radius, User u){
		
		
		String urlApi ="http://data.tmsapi.com/v1/movies/futureReleases?releaseDate={{DATE}}&numDays=90&country=USA&imageSize=Lg&api_key="+key;
		//urlApi = urlApi.replace("{{ZIP}}", zipcode);
		//urlApi = urlApi.replace("{{numOfDays}}", numOfDays);
		//urlApi = urlApi.replace("{{radius}}", radius);
		
		//urlStr=urlStr.replace("{{DATE}}", date);
		List<Movie> ms= new ArrayList<Movie>();
		List<Movie> ms1= new ArrayList<Movie>();
		ms = findMovieByZipcode(zipcode, urlApi, "");
		List<Movietype> mtsU = u.getMovietypes();
		for (int i = 0; i<ms.size();i++){
			Movie m = ms.get(i);
			for (Movietype mtU:mtsU){
				for (Movietype mt:m.getMovietypes())
				if ((mtU.getType().equals(mt.getType()))){
					ms1.add(ms.get(i));
					
				}
			}	
			
		}
		return ms1;
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 MovieService m=new  MovieService();
		// System.out.println(m.getDate());
		 //String date=m.getDate();
			//String urlApi ="http://data.tmsapi.com/v1/movies/showings?startDate={{DATE}}&numDays=15&zip={{ZIP}}&radius=15&imageSize=Lg&api_key=9gnk9dw7rbpzg55wmk8qamn9";
			//String urlStr = urlApi.replace("{{ZIP}}", "11111");
			//urlStr=urlStr.replace("{{DATE}}", date);
			//m.findMovieByZipcode("02148",null,"read");
			
			//String s="asd, dsa, qww";
			//m.searchShowTimes("02115","5", "5",11312638);
			//System.out.println(m.searchShowTimes("02115","5", "5",11312638));
			
			
			 System.out.println(m.getDateF(2));
	}

}
