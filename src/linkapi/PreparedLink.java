package linkapi;

public class PreparedLink {

	String[] link;
	String[] arguments;

	public PreparedLink(String link) {

		this.link = link.split("[*]");
		int lengthLink = this.link.length;
		int extra = 0;
		
		if (link.charAt(lengthLink - 1) == '*')
			extra = 1;

		this.arguments = new String[lengthLink + extra];
	}

	public void set(int i, String s) {
		if(i >= this.arguments.length)
			throw new IndexOutOfBoundsException();
		
		this.arguments[i] = s;
	}

	public String toString() {

		int lengthLink = this.link.length;
		int lengthArguments = this.arguments.length;

		String tmp = "";

		for (int i = 0; (i < lengthLink) || (i < lengthArguments); i++) {
			if (i < lengthLink)
				tmp = tmp + this.link[i];

			if (i < lengthArguments)
				if(this.arguments[i] != null)
					tmp = tmp + this.arguments[i];
		}
		
		return tmp;
	}
}
