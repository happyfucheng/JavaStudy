package study.java.jdks.version1_6.jaxb2.a_sample;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loginMockData") 
public class LoginMockData {
	@XmlElementWrapper(name = "mLoginBeans")
	@XmlElement(name = "mLoginBean")
	private List<MLoginBean> mLoginBeans;

	public List<MLoginBean> getMLoginBeans() {
		return mLoginBeans;
	}

	public void setMLoginBeans(List<MLoginBean> mLoginBeans) {
		this.mLoginBeans = mLoginBeans;
	}


}
