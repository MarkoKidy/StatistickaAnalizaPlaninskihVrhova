import java.io.File;

public class ParseFactory {

	public FileParser getParser(File f) {

		String[] pathSplit = f.getName().split("\\.");
		String extension = pathSplit[pathSplit.length - 1];
		FileParser parser = null;

		switch (extension) {
		case "csv":
			parser = new CsvFileParser();
			break;

		case "properties":
			parser = new CustomPropertiesFileParser();
			break;

		default:
			ExceptionsHolder.addExceptions("Not supported extension " + extension);
			break;
		}

		return parser;
	}

}
