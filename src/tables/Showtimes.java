package tables;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class Showtimes {
	private String TheatreId;
	private String Theatrename;
	private String url;
	private String datetime;
	public String getTheatreId() {
		return TheatreId;
	}
	public void setTheatreId(String theatreId) {
		TheatreId = theatreId;
	}
	public String getTheatrename() {
		return Theatrename;
	}
	public void setTheatrename(String Theatrename) {
		this.Theatrename = Theatrename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	
	
	public static List<Showtimes> FindTheatres(List<Showtimes> st,String TheatreId){
		List<Showtimes> na=new ArrayList<Showtimes>();
		for(int i=0;i<st.size();i++){
			if(st.get(i).getTheatreId().equals(TheatreId)){
				na.add(st.get(i));
			}
		}
		return na;
	}
	
	public static List<String> FindTheatresList(List<Showtimes> st){
		List<String> na=new ArrayList<String>();
		//String theatreId = "";
		for(int i=0;i<st.size();i++){
			
			String s0=st.get(i).getTheatreId();
			String s1=st.get(i).getTheatrename();
			if(!na.contains(s0)){
				
				na.add(s0);
				na.add(s1);
		
			}
		}
		return na;
	}
		
	
	

}
