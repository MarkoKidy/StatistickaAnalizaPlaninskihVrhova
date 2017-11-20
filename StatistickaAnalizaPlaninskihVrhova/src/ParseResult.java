import java.util.ArrayList;

public final class ParseResult {

	private final ArrayList<Mountain> mountains;

	public ParseResult(ArrayList<Mountain> m) {
		super();
		this.mountains = new ArrayList<>();

		for (Mountain mountain : m) {
			mountains.add(mountain);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Mountain> getPlanine() {
		return (ArrayList<Mountain>) mountains.clone();
	}

	public String toString() {
		String s = "";
		for (Mountain mountain : mountains) {
			s += mountain + "\n";
		}
		return s;
	}

}
