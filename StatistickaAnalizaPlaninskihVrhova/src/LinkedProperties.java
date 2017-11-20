import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Properties;

/**
 * @author Marko Kidisevic
 *         <p>
 *         Class that extends Properties class, overrides keys so it returns
 *         Enumeration from added LinkedHashSet keys. Method put add sent pair
 *         to LinkedHashSet keys as well.
 */
// Doubles the data.
public class LinkedProperties extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final HashSet<Object> keys = new LinkedHashSet<Object>();

	public LinkedProperties() {
	}

	public Iterable<Object> orderedKeys() {
		return Collections.list(keys());
	}

	public Enumeration<Object> keys() {
		return Collections.<Object>enumeration(keys);
	}

	public Object put(Object key, Object value) {
		keys.add(key);
		return super.put(key, value);
	}
}