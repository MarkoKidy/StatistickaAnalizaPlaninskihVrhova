import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

/**
 * @author Marko Kidisevic
 *         <p>
 *         Singleton class for parsing List of Files to the given OutputSteam.
 */
// Eager singleton is used since the object is not going to take any significant
// memory and will be used for sure. No need for checking null pointers or synchronized access.
public class MountainAnalyzer {

	private static final MountainAnalyzer mountainAnalyzerInstance = new MountainAnalyzer();

	private MountainAnalyzer() {
	}

	public static MountainAnalyzer getInstance() {
		return mountainAnalyzerInstance;
	}

	public void analyze(List<File> inputFiles, OutputStream report) {
		ParseFactory parseFactory = new ParseFactory();
		ArrayList<ParseResult> parseResults = new ArrayList<>();
		ParseResultAnalyzer parseResultAnalyzer = new ParseResultAnalyzer();

		for (File file : inputFiles) {
			FileParser fileParser = parseFactory.getParser(file);
			if (fileParser != null) {
				ParseResult ps = fileParser.parse(file);
				if (ps != null)
					parseResults.add(ps);
			}
		}

		parseResultAnalyzer.analyzeAll(parseResults, report);
	}
}
