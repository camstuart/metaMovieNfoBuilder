package au.net.asoftware.metamovie

import groovyx.net.http.*

import com.google.gson.Gson

class OpenImdbApi {

	def String apiBaseUrl = "http://www.omdbapi.com/"
	def Gson gson = new Gson()

	def public OpenImdbMovie findByTitleAndYear(def String title, def String year) {

		def queryMap   = [t:title.trim(), y:year.trim()]
		def jsonResult = callApi(queryMap)


		def OpenImdbMovie openImdbMovie = new OpenImdbMovie()
		try {
			openImdbMovie = gson.fromJson(jsonResult, OpenImdbMovie.class)
		} catch (Exception e) {
			println "GSON Error: " + e.getStackTrace()
		}

		return openImdbMovie
	}

	/**
	 * Get movie by imdbID
	 * 
	 * @param imdbid
	 * @return OpenImdbMovie
	 */
	def public OpenImdbMovie getByImdbId(def String imdbid) {

		def queryMap   = [i:imdbid.trim()]
		def jsonResult = callApi(queryMap)


		def OpenImdbMovie openImdbMovie = new OpenImdbMovie()
		try {
			openImdbMovie = gson.fromJson(jsonResult, OpenImdbMovie.class)
		} catch (Exception e) {
			println "GSON Error: " + e.getStackTrace()
		}

		return openImdbMovie
	}

	/**
	 * Search for movie by keyword(s)
	 * 
	 * @param searchParameter
	 * @return List<Search>
	 */
	def public List<Search> findByKeyword(def String searchParameter) {

		def queryMap   = [s:searchParameter.trim()]
		def jsonResult = callApi(queryMap)

		//println "JSON: ${jsonResult}"

		def OpenImdbSearchResultSet openImdbSearchResultSet = new OpenImdbSearchResultSet()
		try {
			openImdbSearchResultSet = gson.fromJson(jsonResult, OpenImdbSearchResultSet.class)
		} catch (Exception e) {
			println "GSON Error: " + e.getStackTrace()
		}

		//println "Results  : " + openImdbSearchResultSet.getSearch().size()

		return openImdbSearchResultSet.getSearch()
	}

	/**
	 * Call the open imdb restful web service, get a json response back
	 * 
	 * @param queryMap
	 * @return String
	 */
	def private String callApi(def Map queryMap) {

		def http = new HTTPBuilder(apiBaseUrl)

		try {

			http.get(query: queryMap) { resp, json ->

				println "Response Status: " + resp.status

				if (resp.status != 200) {
					println "Bad response code: ${resp.status} returned from URL: ${apiBaseUrl}"
					return ""
				} else {
					return json.toString()
				}
			}

		} catch (Exception e) {

			println "Internet connection or network failure"
			System.exit(1)

		}

	}
}