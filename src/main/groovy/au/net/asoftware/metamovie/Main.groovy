package au.net.asoftware.metamovie



class Main {

	static main(args) {

		//def config = new ConfigSlurper().parse(new File('resources/metaMovieNfoBuilder.ini').toURI().toURL())
		
		//println "Config: " + config.base.work.directory
		
		OpenImdbApi oimdb = new OpenImdbApi()
		def OpenImdbMovie openImdbMovie
		
		// C:\Users\cam\Desktop\Rob\Alex Cross[2012].avi
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
		print "Enter Movie ID or Name: "
		
		//def String input = "tt0087928"
		
		String input = br.readLine()
		println "input was:${input}:"
		
		
		if (input.toLowerCase().startsWith("tt")) {	
			// Find the movie by ID:
			println "Search on imdbID: ${input}"
			
			openImdbMovie = oimdb.getByImdbId(input.trim())
			
			println "Got back movie title: ${openImdbMovie.getTitle()}"
			
			
		} else {
			def File movieFile =  new File(input)
			
			if (movieFile.exists()) {
				// Use the filename to lookup movie
				
				println "Search on filename: " + movieFile.getName()
				
				
				def String rawTitle = movieFile.getName()
				rawTitle = rawTitle.replace("[", "(")
				rawTitle = rawTitle.replace("]", ")")
				
				
				def regex = /Alex/
				def matcher = ( rawTitle =~ regex )
				
				println "name: ${rawTitle}"
				
				if (matcher.matches()) {
					println "m " + matcher[0][0]
				}
				
				
				//def List searchResultsList = oimdb.findByKeyword(input)
				openImdbMovie = oimdb.findByTitleAndYear("Alex.Cross", "2012")
				
				if (openImdbMovie.getTitle().equals("")) {
					println "Unable to find movie, try using ID"
				}
				
				println "Title: " + openImdbMovie.getTitle()
				println "Year : " + openImdbMovie.getYear()
				println "ID   : " + openImdbMovie.getImdbID()
				
				//def resultChoices = []
				//def int i = 1
				//for (def Search r in searchResultsList) {
				//	println "Search Result [${i}]: ${r.getTitle()} (${r.getYear()})"
				//	i++
				//}
				
				//if (i == 2) {
				//	println "Single result, using that one"
				//} else {
				//	println "which one was it?"
				//}
			} else {
				// Do a plain search to lookup movie
				
				println "Search on string: [${input}]"
			
			}
			
		}
		
		
		// Generate directory & nfo and folder.jpg
		
		def directoryName = openImdbMovie.getTitle().trim().replace(":", "").replace(" ", ".") + ".(" + openImdbMovie.getYear() + ")"
		
		def File ouputPath = new File("C:/tmp/_boxxee/" + directoryName)
		if (!ouputPath.exists()) {
			ouputPath.mkdirs()
		}

		def file = new FileOutputStream(ouputPath.getAbsolutePath() + "/folder.jpg")
		def out = new BufferedOutputStream(file)
		out << new URL(openImdbMovie.getPoster()).openStream()
		out.close()


		def actors = []
		actors = openImdbMovie.getActors().trim().split(",")

		def String actorBlock = ""
					
					for (def actor in actors) {
		actorBlock = actorBlock + """\
		<actor>
			<name>${actor.trim()}</name>
			<role></role>
			<thumb></thumb>
		</actor>
"""
					}
					
		
					def nfoBody = """\
<movie>
    <title>${openImdbMovie.getTitle().trim()}</title>
    <rating>${openImdbMovie.getImdbRating().trim()}</rating>
    <year>${openImdbMovie.getYear().trim()}</year>
    <outline>${openImdbMovie.getPlot().trim()}</outline>
    <runtime>${convertRunTime(openImdbMovie.getRuntime().trim())}</runtime>
    <thumb>folder.jpg</thumb>
    <mpaa>${openImdbMovie.getRated().trim()}</mpaa>
    <id>${openImdbMovie.getImdbID()}</id>
    <trailer></trailer>
    <genre>${openImdbMovie.getGenre().trim()}</genre>
    <director>${openImdbMovie.getDirector().trim()}</director>
    ${actorBlock}
</movie>"""
		
					new File(ouputPath.getAbsolutePath() + "/movie.nfo").withWriter{ it << nfoBody }
		
					
		println "Created output: ${ouputPath.getAbsolutePath()}"
		
		input = br.readLine()
		
		System.exit(0)
		
		
		def List searchResultsList = oimdb.findByKeyword(input)

		for (def Search r in searchResultsList) {
			println "Search Result: ${r.getTitle()} (${r.getYear()})"
			
			sleep(2000)
			
			//OpenImdbMovie movie = oimdb.getByImdbId(r.getImdbID())
			
			//OpenImdbMovie movie = oimdb.findByTitleAndYear(r.getTitle(), r.getYear())
			
			println "Detail Runtime: " + convertRunTime(openImdbMovie.getRuntime())
			println "Detail Rating : " + openImdbMovie.getRated()
			
		}
		
		

		def runTimes = ["1 h 8 min", "2 h 58 min", "1 h 17 min", "foo"]

		for (def String runTime in runTimes) {
			println "Original Runtime: ${runTime}"

			println "Modified Runtime: " + convertRunTime(runTime)
		}
	}


	private static String convertRunTime(def String runTime) {

		def String newRuntime = "90 min"
		
		if (!runTime.contains("min") && !runTime.contains("h")) {
			return newRuntime
		}

		def parts = runTime.split(" ")

		if (parts.size() != 4) {
			return newRuntime
		}

		def int hours
		def int minutes
		try {
			hours = Integer.parseInt(parts[0])
			minutes = Integer.parseInt(parts[2])
		} catch( NumberFormatException nfe) {
			return newRuntime
		}

		newRuntime = ((hours * 60) + minutes) + " min"

		return newRuntime
	}
}
