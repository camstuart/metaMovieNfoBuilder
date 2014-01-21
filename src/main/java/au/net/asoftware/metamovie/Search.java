package au.net.asoftware.metamovie;

import java.util.HashMap;
import java.util.Map;

public class Search {

	private String Title;
	private String Year;
	private String imdbID;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String Year) {
		this.Year = Year;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}