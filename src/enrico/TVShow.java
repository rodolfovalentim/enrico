package enrico;

public class TVShow {
	String name;
	String sinopse;
	String year;
	String status;

	public TVShow(String name, String sinopse, String year, String status) {
		super();
		this.name = name;
		this.sinopse = sinopse;
		this.year = year;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
