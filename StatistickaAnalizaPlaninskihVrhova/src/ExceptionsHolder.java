import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Marko Kidisevic
 *         <p>
 *         Class for holding exception messages for later use with getExceptions
 *         method.
 */
// Writers are used so we save everything from e.printStackTrace.
public class ExceptionsHolder {

	private static StringWriter stringWriter = new StringWriter();
	private static PrintWriter printWriter = new PrintWriter(stringWriter);

	public static String getExceptions() {
		return stringWriter.toString();
	}

	public static void addExceptions(Exception e) {
		e.printStackTrace(printWriter);
		printWriter.print("</br></br>");
	}

	public static void addExceptions(String s) {
		printWriter.println(s);
		printWriter.print("</br></br>");
	}
}
