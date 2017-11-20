
public final class Peak {

	private final String name;
	private final Integer height;

	public Peak(String name, Integer height) {
		this.name = name;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public Integer getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return name + " " + height;
	}

}
