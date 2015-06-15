package study.java.jdks.version1_6.console;

import java.io.Console;

public class TestConsole {
	public static void main(String[] args) {
		Console console = System.console();
		if(console != null) {
			String user = new String(console.readLine("Enter username:"));
			//  ‰»Î√‹¬Î£¨÷’∂À≤ªœ‘ æ√‹¬Î
			String pwd = new String(console.readPassword("Enter password"));
			console.printf("User name is " + user + "\n");
			console.printf("Password is " + pwd + "\n");
		} else {
			System.out.println("Console is unavaliable");
		}
	}

}
