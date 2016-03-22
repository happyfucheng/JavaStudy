import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class TestClass {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://www.asiainfo.com/Portals/0/LogoHeader.png");
		InputStream is = url.openStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = is.read(buffer))) {
			output.write(buffer, 0, n);
		}
		System.out.println(output.toByteArray().length);

	}
}
