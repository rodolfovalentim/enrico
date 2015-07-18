package download;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.JSONException;

import mega.MegaHandler;

public class Mega extends DownloadLink {

	public Mega(String link) {
		super(link);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void download(String path) {
		MegaHandler mh = new MegaHandler("00@gmail.com","1234");
		try {
			mh.download("https://mega.co.nz/#!vkp0CCBS!ksUTWoCJn-Uvd1G0r5b7TXjeN9DZwKVojFz6J7lXX_8", path);
		} catch (InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException
				| NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | IOException
				| JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
