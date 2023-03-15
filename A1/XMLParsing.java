import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public interface XMLParsing {
    void parseXMLInputFile(File file);
    HashMap<String, String> orderValidation(NodeList itemList) throws Exception;
    DeliveryAddress deliveryAddressValidation(NodeList deliverAddress) throws Exception;
    Document convertToDocument(File file) throws ParserConfigurationException, IOException, SAXException;
    void convertToXML(String str) throws ParserConfigurationException, SAXException, IOException, TransformerException, URISyntaxException, InterruptedException;
}
