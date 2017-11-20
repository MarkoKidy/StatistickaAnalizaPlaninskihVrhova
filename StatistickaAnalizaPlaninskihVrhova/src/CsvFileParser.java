import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFileParser implements FileParser {

	@Override
	public ParseResult parse(File f) {
		String line;
		ArrayList<Peak> peaks = new ArrayList<>();
		ArrayList<Mountain> mountains = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(f));){
			while((line = br.readLine()) != null)
			{
				String[] str = line.split(",");
				String name = str[0];
				for (int i = 1; i < str.length; i+=2) {
					peaks.add(new Peak(str[i], Integer.parseInt(str[i+1])));
				}
				mountains.add(new Mountain(name, peaks));
				peaks.clear();
			}
			return new ParseResult(mountains);
		} catch (NumberFormatException | IOException e) {
			ExceptionsHolder.addExceptions(e);
			return null;
		}
	}

}
