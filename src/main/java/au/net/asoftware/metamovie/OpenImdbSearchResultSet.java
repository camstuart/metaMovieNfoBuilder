package au.net.asoftware.metamovie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenImdbSearchResultSet {
	private List<Search> Search = new ArrayList<Search>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public List<Search> getSearch() {
		return Search;
	}

	public void setSearch(List<Search> Search) {
		this.Search = Search;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}
