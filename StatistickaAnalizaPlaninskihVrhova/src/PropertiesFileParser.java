import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class PropertiesFileParser implements FileParser {

	@Override
	public ParseResult parse(File f) {

		LinkedProperties linkedPorps = new LinkedProperties();

		try (InputStream inputs = new FileInputStream(f)) {

			linkedPorps.load(inputs);
			inputs.close();
			Iterable<Object> iterator = linkedPorps.orderedKeys();
			String mountain = null;
			String key;
			String keySplit[];
			int height;
			ArrayList<Mountain> mountains = new ArrayList<>();
			ArrayList<Peak> peaks = new ArrayList<>();

			for (Object object : iterator) {
				key = (String) object;
				height = Integer.parseInt(linkedPorps.getProperty(key));
				keySplit = key.split("\\.");
				if (keySplit.length != 2) {
					ExceptionsHolder.addExceptions("Key is not right - " + key);
					continue;
				}

				if (!Objects.equals(mountain, keySplit[0])) {
					if (mountain != null) {
						mountains.add(new Mountain(mountain, peaks));
						peaks.clear();
					}
					mountain = keySplit[0];
					peaks.add(new Peak(keySplit[1], height));
				} else {
					peaks.add(new Peak(keySplit[1], height));
				}
			}
			mountains.add(new Mountain(mountain, peaks));
			return new ParseResult(mountains);
		} catch (NumberFormatException | IOException e) {
			ExceptionsHolder.addExceptions(e);
			return null;
		}

	}

}
