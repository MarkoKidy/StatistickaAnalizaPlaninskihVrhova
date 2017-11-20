import java.util.ArrayList;

public final class Mountain {

	private final String name;
	private final ArrayList<Peak> peaks;

	public Mountain(String name, ArrayList<Peak> p) {
		super();
		this.name = name;
		this.peaks = new ArrayList<>();

		for (Peak peak : p) {
			peaks.add(peak);
		}
	}

	public String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Peak> getPeaks() {
		return (ArrayList<Peak>) peaks.clone();
	}

	@Override
	public String toString() {
		String s = name + "\n";

		for (Peak peak : peaks)
			s += peak + "\n";

		return s;
	}

}
