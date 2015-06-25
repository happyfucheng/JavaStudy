package study.java.jdks.version1_6.stax.extend;

import java.io.IOException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

 

public class SaxWriteTest {
    static class SAXReaderImpl implements XMLReader{
       @Override
       public boolean getFeature(String name)
              throws SAXNotRecognizedException, SAXNotSupportedException {
           return false;
       }

       @Override
       public void setFeature(String name, boolean value)
              throws SAXNotRecognizedException, SAXNotSupportedException {
       }
       
       @Override
       public Object getProperty(String name)
              throws SAXNotRecognizedException, SAXNotSupportedException {
           return null;
       }
       
       @Override
       public void setProperty(String name, Object value)
              throws SAXNotRecognizedException, SAXNotSupportedException {
       }

       @Override
       public void setEntityResolver(EntityResolver resolver) {
       }

       @Override
       public EntityResolver getEntityResolver() {
           return null;
       }

       @Override
       public void setDTDHandler(DTDHandler handler) {
       }

       @Override
       public DTDHandler getDTDHandler() {
           return null;
       }

       @Override
       public void setContentHandler(ContentHandler handler) {
           this.handler = handler;
       }

       @Override
       public ContentHandler getContentHandler() {
           return null;
       }

       @Override
       public void setErrorHandler(ErrorHandler handler) {
       }

       @Override
       public ErrorHandler getErrorHandler() {
           return null;
       }

       @Override
       public void parse(InputSource input) throws IOException, SAXException {
           if(handler == null)
              throw new SAXException("content handler is null");
           handler.startDocument();
           handler.startElement("", "students", "students", null);
              handler.startElement("", "student", "student", null);
                  handler.startElement("", "student_name", "student_name", null);
                     handler.characters("Mary".toCharArray(), 0, "Mary".length());
                  handler.endElement("", "student_name", "student_name");
                  handler.startElement("", "student_id", "student_id", null);
                     handler.characters("S09070934".toCharArray(), 0, "S09070934".length());
                  handler.endElement("", "student_id", "student_id");
              handler.endElement("", "student", "student");
              handler.startElement("", "student", "student", null);
              handler.startElement("", "student_name", "student_name", null);
                  handler.characters("Lord".toCharArray(), 0, "Mary".length());
              handler.endElement("", "student_name", "student_name");
              handler.startElement("", "student_id", "student_id", null);
                  handler.characters("S09070808".toCharArray(), 0, "S09070808".length());
              handler.endElement("", "student_id", "student_id");
           handler.endElement("", "student", "student");
           handler.endElement("", "students", "students");
           handler.endDocument();
       }

       @Override
       public void parse(String systemId) throws IOException, SAXException {
       }

       private ContentHandler handler;
    }

    public static void main(String[] args) throws TransformerException {
       TransformerFactory tff = TransformerFactory.newInstance();
       Transformer tf = tff.newTransformer();
       tf.setOutputProperty(OutputKeys.INDENT, "yes");//设置转换的属性
       tf.setOutputProperty(OutputKeys.METHOD, "xml");
       tf.transform(new SAXSource(new SAXReaderImpl(),null), new StreamResult(System.out));
    }
}