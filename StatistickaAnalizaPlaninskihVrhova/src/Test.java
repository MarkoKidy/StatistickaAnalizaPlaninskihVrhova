import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {

		List<File> files = new ArrayList<File>();

		// Not supported extension.
		File f1 = new File("src/testFiles/test.sd");
		// File do not exist.
		File f2 = new File("src/testFiles/test.csv");
		File f3 = new File("src/testFiles/test2.csv");
		// One of the keys is not in good format.
		File f4 = new File("src/testFiles/test.properties");

		MountainAnalyzer mountainAnalyzer = MountainAnalyzer.getInstance();

		files.add(f1);
		files.add(f2);
		files.add(f3);
		files.add(f4);

		try {
			File file = new File("src/testFiles/html.html");
			file.createNewFile();
			OutputStream os = new FileOutputStream(file);
			mountainAnalyzer.analyze(files, os);
		} catch (IOException | NullPointerException e) {
			ExceptionsHolder.addExceptions(e);
		}
	}

}
