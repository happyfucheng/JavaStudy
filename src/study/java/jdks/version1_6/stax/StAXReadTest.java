package study.java.jdks.version1_6.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * StAX 拉模式
 * @author hadoop2
 *
 */
public class StAXReadTest {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
       //创建InputStream
       InputStream in = new FileInputStream("E:\\eclipse_luna\\aidefaultws\\JavaStudy\\src\\study\\java\\jdks\\version1_6\\stax\\student.xml");
       XMLInputFactory xif = XMLInputFactory.newInstance();//创建StAX分析工厂
       XMLStreamReader reader = xif.createXMLStreamReader(in);//创建分析器
       while(reader.hasNext())//迭代
       {
           int event = reader.next();//读取下一个事件
           if(event == XMLStreamReader.START_ELEMENT)//如果这个事件是元素开始
           {
              if("student_id".equals(reader.getLocalName()))//判断元素是不是student_id
              {//如果是student_id则输出元素的文本内容
                  System.out.print(reader.getLocalName()+" : ");
                  System.out.println(reader.getElementText());
              }                   
           }
       }
    }
}

