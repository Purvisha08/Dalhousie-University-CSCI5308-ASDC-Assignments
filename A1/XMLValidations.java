import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.HashMap;

public class XMLValidations {

    public static final String INVALID_DELIVERY_ADDRESS = "Invalid Delivery Address";
    public static final String DELIVERY_ADD_NOT_PROVIDED = "Delivery Address Not Provided";

    public static final String RESULT_ERROR = "<result>failure</result>\n" +
            "<error>Invalid order</error>\n" +
            "<errormessage>";
    public static final String  INVALID_ORDER_ITEM_ENTRY = "Invalid Order item entry";

    public static final String  INVALID_ORDER_ITEM_LIST = "Invalid Order item List";

    public static final String FAILURE_ORDER_TAG = "<result>failure</result>\n" +
            "<error>Invalid order</error>\n" +
            "<errormessage>";

    public static final String DEALER_ID_NOT_FOUND = "Dealer id not found";

    public static final String DEALER_ACCESS_KEY_NOT_PROVIDED  = "Dealer accesskey not provided for authorization";

    public static final String NOT_PROVIDED = "Not authorized";
    public DeliveryAddress deliveryAddressValidation(NodeList deliveryAddress) throws Exception {

        String errorMessage = null;
        DeliveryAddress deliveryAdrs = null;

        if (deliveryAddress.getLength() != 0) {
            for (int tempInt = 0; tempInt < deliveryAddress.getLength(); tempInt++) {
                deliveryAdrs = new DeliveryAddress();

                if (deliveryAddress.item(tempInt).getNodeType() == Node.ELEMENT_NODE) {
                    setName(deliveryAdrs, (Element) deliveryAddress.item(tempInt));
                    setStreet(deliveryAdrs, (Element) deliveryAddress.item(tempInt));
                    setCity(deliveryAdrs, (Element) deliveryAddress.item(tempInt));
                    setProvience(deliveryAdrs, (Element) deliveryAddress.item(tempInt));
                    setPostalCode(deliveryAdrs, (Element) deliveryAddress.item(tempInt));

                    if (isDeliveryAddressNull(deliveryAdrs)) {
                        errorMessage = INVALID_DELIVERY_ADDRESS;
                        break;
                    }
                }
            }
        } else {
            errorMessage = DELIVERY_ADD_NOT_PROVIDED;
        }

        if (errorMessage != null) {
            final String errorResponse = RESULT_ERROR + errorMessage + "</errormessage>";
            convertToXML(errorResponse);
            throw new Exception(errorResponse);

        } else {
            return deliveryAdrs;
        }

    }

    private void setPostalCode(DeliveryAddress deliveryAdrs, Element eElement2) {
        deliveryAdrs.setPostalCode(eElement2.getElementsByTagName("postalcode")
                .item(0)
                .getTextContent());
    }

    private void setProvience(DeliveryAddress deliveryAdrs, Element eElement2) {
        deliveryAdrs.setProvince(eElement2.getElementsByTagName("province")
                .item(0)
                .getTextContent());
    }

    private void setCity(DeliveryAddress deliveryAdrs, Element eElement2) {
        deliveryAdrs.setCity(eElement2.getElementsByTagName("city")
                .item(0)
                .getTextContent());
    }

    private void setStreet(DeliveryAddress deliveryAdrs, Element eElement2) {
        deliveryAdrs.setStreet(eElement2.getElementsByTagName("street")
                .item(0)
                .getTextContent());
    }

    private void setName(DeliveryAddress deliveryAdrs, Element eElement2) {
        deliveryAdrs.setName(eElement2.getElementsByTagName("name")
                .item(0)
                .getTextContent());
    }

    private boolean isDeliveryAddressNull(DeliveryAddress deliveryAdrs) {
        return deliveryAdrs.getName() == null ||
                deliveryAdrs.getCity() == null ||
                deliveryAdrs.getPostalCode() == null ||
                deliveryAdrs.getProvince() == null ||
                deliveryAdrs.getStreet() == null;
    }

    public HashMap<String, String> orderValidation(NodeList item) throws Exception {
        String errorMessage = null;
        HashMap<String, String> map = new HashMap<>();
        if (item.getLength() != 0) {
            for (int i = 0; i < item.getLength(); i++) {
                if (item.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) item.item(i);
                    String partNumberContent = element.getElementsByTagName("partnumber").item(0).getTextContent();
                    String quantityContent = element.getElementsByTagName("quantity").item(0).getTextContent();
                    errorMessage = isPartOrQuantityNull(errorMessage, map, partNumberContent, quantityContent);
                }
            }
        } else {
            errorMessage = INVALID_ORDER_ITEM_LIST;
        }
        if (errorMessage != null) {
            final String errorResponse = FAILURE_ORDER_TAG + errorMessage + "</errormessage>";
            convertToXML(errorResponse);
            throw new Exception(errorResponse);
        } else {
            return map;
        }

    }

    private String isPartOrQuantityNull(String errorMessage, HashMap<String, String> map, String partNumber, String quantity) {
        if (partNumber == null || quantity == null) {
            errorMessage = INVALID_ORDER_ITEM_ENTRY;
        } else {
            map.put(partNumber, quantity);
        }
        return errorMessage;
    }

    protected void delearValidation(Dealer dealer) throws Exception {

        String errorMessage = null;

        Security security = new SecurityImplementation();
        if (dealer.getDealerId() == null) {
            errorMessage = DEALER_ID_NOT_FOUND;
        } else if (dealer.getDealerAccessKey() == null) {
            errorMessage = DEALER_ACCESS_KEY_NOT_PROVIDED;
        } else {
            try {
                boolean authorized = security.IsDealerAuthorized(dealer.getDealerId(), dealer.getDealerAccessKey());
                if (!authorized) {
                    errorMessage = NOT_PROVIDED;
                }
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
        }

        if (errorMessage != null) {
            final String errorResponse =
                    "<order>\n"
                            + "<result>failure</result>\n"
                            + "<error>" + errorMessage + "</error>\n"
                            + "</order>\n";
            convertToXML(errorResponse);
            throw new Exception(errorResponse);
        }
    }
    public void convertToXML(String str) throws ParserConfigurationException, SAXException, IOException, TransformerException, URISyntaxException, InterruptedException {
        DocumentBuilderFactory.newInstance().setNamespaceAware(true);
        DOMSource domSource = new DOMSource(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str))));
        StreamResult streamResult = new StreamResult(new File("output.xml"));
        TransformerFactory.newInstance().newTransformer().transform(domSource, streamResult);
        File file = new File("output.xml");
        Desktop desk = Desktop.getDesktop();
        if (file.exists()) {
            desk.open(file);
        }
    }
}
