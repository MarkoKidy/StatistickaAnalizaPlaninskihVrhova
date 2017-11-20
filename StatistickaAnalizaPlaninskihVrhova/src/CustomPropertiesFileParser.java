import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Marko Kidisevic
 *         <p>
 * 
 */
// I decided to use this parser over PropertiesFileParser because of double the
// data in that one since it uses LinkerProperties.
public class CustomPropertiesFileParser implements FileParser {

	@Override
	public ParseResult parse(File f) {
		String line;
		String lineSplit[];
		String mountain = null;
		int height;
		ArrayList<Mountain> mountains = new ArrayList<>();
		ArrayList<Peak> peaks = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(f));) {
			while ((line = br.readLine()) != null) {
				if (!validation(line)) {
					ExceptionsHolder.addExceptions("Error at parsing the line - " + line);
					continue;
				}
				lineSplit = line.split("=|\\.");
				height = Integer.parseInt(lineSplit[2]);
				if (!Objects.equals(mountain, lineSplit[0])) {
					if (mountain != null) {
						mountains.add(new Mountain(mountain, peaks));
						peaks.clear();
					}
					mountain = lineSplit[0];
					peaks.add(new Peak(lineSplit[1], height));
				} else {
					peaks.add(new Peak(lineSplit[1], height));
				}
			}
			mountains.add(new Mountain(mountain, peaks));
			return new ParseResult(mountains);
		} catch (NumberFormatException | IOException e) {
			ExceptionsHolder.addExceptions(e);
			return null;
		}
	}

	// some simple validation
	private boolean validation(String str) {

		if (str.split("=").length != 2)
			return false;
		if (str.split("\\.").length != 2)
			return false;

		return true;
	}

}
