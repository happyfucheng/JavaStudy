package study.java.jdks.version1_6.stax;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

 

public class StAXWriteTest {

    public static void writeXML(XMLStreamWriter writer) throws XMLStreamException
    {
       writer.writeStartDocument("UTF-8", "1.0");//开始写文档
       writer.writeStartElement("", "students");//写出一些内容
           writer.writeStartElement("student");
              writer.writeStartElement("student_id");
                  writer.writeCharacters("S09080709");
              writer.writeEndElement();
              writer.writeStartElement("student_name");
                  writer.writeCharacters("mary");
              writer.writeEndElement();
           writer.writeEndElement();
           writer.writeStartElement("student");
           writer.writeStartElement("student_id");
              writer.writeCharacters("S0900121");
           writer.writeEndElement();
           writer.writeStartElement("student_name");
              writer.writeCharacters("Lord");
           writer.writeEndElement();
       writer.writeEndElement();
       writer.writeEndElement();         
       writer.writeEndDocument();//文档写出结束
       writer.flush();//刷新缓冲
    }
    public static void main(String[] args) throws XMLStreamException {
       XMLOutputFactory xof = XMLOutputFactory.newInstance();//创建输出工厂   
       XMLStreamWriter writer = xof.createXMLStreamWriter(System.out,"UTF-8");//创建XML写出流
       writer.setPrefix("", "");//没提供特殊的前缀
       writeXML(writer);//执行写入一些XML信息
       writer.close();//关闭写出流
    }
}

