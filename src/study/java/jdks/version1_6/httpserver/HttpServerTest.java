package study.java.jdks.version1_6.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
/**
 * HttpServer
 * @author hadoop2
 *
 */
public class HttpServerTest{
	public static void main(String[] args) {
		
		try {
			HttpServer hs =HttpServer.create(new InetSocketAddress(8888), 0);
			hs.createContext("/fucheng", new HttpHandler() {
				public void handle(HttpExchange t) throws IOException{
					InputStream is =t.getRequestBody();
					System.out.println(t.getRemoteAddress().toString());
					String response = "<h3>fucheng!welcome!</h3>";
					t.sendResponseHeaders(200, response.length());
					OutputStream os = t.getResponseBody();
					os.write(response.getBytes());
					os.close();
				}
				
			});
			hs.setExecutor(null);
			hs.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
