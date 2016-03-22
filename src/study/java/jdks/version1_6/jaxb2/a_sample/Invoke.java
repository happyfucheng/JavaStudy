package study.java.jdks.version1_6.jaxb2.a_sample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import study.java.jdks.version1_6.jaxb2.a_sample.JaxbUtil.CollectionWrapper;


public class Invoke {
	public static void main(String[] args) throws Exception{
		
		InputStream is = Invoke.class.getClassLoader().getResourceAsStream("study/java/jdks/version1_6/jaxb2/a_sample/mLoginBean.xml");
		//InputStream is = Invoke.class.getResourceAsStream("/study/java/jdks/version1_6/jaxb2/a_sample/mLoginBean.xml");
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String str = "";
		String readStr;
		while((readStr=br.readLine()) != null) {
			str +=readStr;
		}
		System.out.println(str);
		JaxbUtil requestBinder = new JaxbUtil(LoginMockData.class,CollectionWrapper.class);
		LoginMockData loginMockData = requestBinder.fromXml(str);
		System.out.println(loginMockData);
	}
}
