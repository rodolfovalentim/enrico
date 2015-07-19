package enrico;

import download.DownloadLink;

public class Mirror {

	String name;
	String link;
	DownloadLink download;

	public Mirror(String name, String link) {
		setName(name);
		setLink(link);
		download = DownloadLink.create(link);
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

	public DownloadLink getDownload() {
		return download;
	}

	public void setDownload(DownloadLink download) {
		this.download = download;
	}

	public void setLink(String link) {
		this.link = link;
		download = DownloadLink.create(link);
	}

}
