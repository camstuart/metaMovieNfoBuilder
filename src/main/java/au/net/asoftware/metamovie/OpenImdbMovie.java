package au.net.asoftware.metamovie;

import java.util.HashMap;
import java.util.Map;

public class OpenImdbMovie {

	private String Title;
	private String Year;
	private String Rated;
	private String Released;
	private String Runtime;
	private String Genre;
	private String Director;
	private String Writer;
	private String Actors;
	private String Plot;
	private String Poster;
	private String imdbRating;
	private String imdbVotes;
	private String imdbID;
	private String Response;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public OpenImdbMovie() {
		Title = "";
		Year = "";
		Rated = "";
		Released = "";
		Runtime = "";
		Genre = "";
		Director = "";
		Writer = "";
		Actors = "";
		Plot = "";
		Poster = "";
		imdbRating = "";
		imdbVotes = "";
		imdbID = "";
		Response = "";
	}

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

	public String getRated() {
		return Rated;
	}

	public void setRated(String Rated) {
		this.Rated = Rated;
	}

	public String getReleased() {
		return Released;
	}

	public void setReleased(String Released) {
		this.Released = Released;
	}

	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String Runtime) {
		this.Runtime = Runtime;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String Genre) {
		this.Genre = Genre;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String Director) {
		this.Director = Director;
	}

	public String getWriter() {
		return Writer;
	}

	public void setWriter(String Writer) {
		this.Writer = Writer;
	}

	public String getActors() {
		return Actors;
	}

	public void setActors(String Actors) {
		this.Actors = Actors;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String Plot) {
		this.Plot = Plot;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String Poster) {
		this.Poster = Poster;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getResponse() {
		return Response;
	}

	public void setResponse(String Response) {
		this.Response = Response;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}