package study.java.jdks.version1_6.stax.extend;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Sax 流处理模式的推模式
 * @author hadoop2
 *
 */
public class SaxReadTest {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		SAXParserFactory spf = SAXParserFactory.newInstance();// 创建SAX解析器工厂
		spf.setNamespaceAware(true);// 设置名称空间属性
		SAXParser sp = spf.newSAXParser();// 创建解析器
		// 开始解析
		sp.parse(new File("E:\\eclipse_luna\\aidefaultws\\JavaStudy\\src\\study\\java\\jdks\\version1_6\\stax\\student.xml"),

		new DefaultHandler() {
			boolean isStudentId = false;// 是否是学号信息

			@Override
			public void startElement(String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
				super.startElement(uri, localName, qName, attributes);
				//System.out.println(uri+"==="+ localName+"==="+ qName);
				if ("student_id".equals(localName))// 如果接收到的元素是student_id
				{
					isStudentId = true;// 标记这个是学号信息，在接收字符信息时需要输出学号
					System.out.print(localName + " : ");// 输出元素名称
				}
			}

			@Override
			public void characters(char[] ch, int start, int length)// 接收到字符信息
					throws SAXException {
				super.characters(ch, start, length);
				if (isStudentId)// 如果当前信息是学号
				{
					System.out.println(new String(ch, start, length));// 输出这个学号
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName)// 元素终止
					throws SAXException {
				super.endElement(uri, localName, qName);
				isStudentId = false;// 标记当前元素不是学号
			}
		});
	}
}
