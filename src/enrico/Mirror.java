package enrico;

public class Mirror {

	String name;
	String link;

	public Mirror(String name, String link) {
		setName(name);
		setLink(link);
	}

	public String toString() {
		return getName() + ": " + getLink();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
