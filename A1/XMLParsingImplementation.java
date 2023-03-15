import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLParsingImplementation extends XMLValidations implements XMLParsing {

    public static final String INVALID_PART = "Invalid Part";
    public static final String OUT_OF_STOCK = "Out of Stock";
    public static final String NO_LONGER_MANUFACTURED = "No Longer Manufactured";
    public static final String FAILURE = "Failure";
    public static final String SUCCESS = "Success";

    public static final String EMPTY = "";

    public static final String SOMETHING_WENT_WRONG = "Something went wrong";

    public static final String OPENING_TAG = "<order>\n" +
            "<orderitems>\n";

    public static final String CLOSING_TAG = "</orderitems>\n" +
            "</order>";


    public void parseXMLInputFile(File file) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();

            if (doc.getElementsByTagName("dealer").item(0).getNodeType() == Node.ELEMENT_NODE) {

                extracted(doc);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void extracted(Document doc) throws Exception {
        Dealer dealerObj = new Dealer();
        Element eElement = (Element) doc.getElementsByTagName("dealer").item(0);

        String dealerId = eElement.getElementsByTagName("dealerid").item(0).getTextContent();
        String dealerAccessKey = eElement.getElementsByTagName("dealeraccesskey").item(0).getTextContent();

        dealerObj.setDealerId(dealerId);
        dealerObj.setDealerAccessKey(dealerAccessKey);
        delearValidation(dealerObj);

        if (validateOrderAndAddress(orderValidation(doc.getElementsByTagName("orderitems").item(0).getChildNodes()), deliveryAddressValidation(doc.getElementsByTagName("deliveryaddress")))) {
            StringBuilder response = new StringBuilder();
            for (Map.Entry<String, String> mapEntry : orderValidation(doc.getElementsByTagName("orderitems").item(0).getChildNodes()).entrySet()) {
                extracted(doc, response, mapEntry);
            }
            System.out.println(OPENING_TAG + response + CLOSING_TAG);
            convertToXML(OPENING_TAG + response + CLOSING_TAG);
        }
    }

    private void extracted(Document doc, StringBuilder response, Map.Entry<String, String> mapEntry) throws Exception {
        String result = EMPTY;
        String errorMessage = EMPTY;

        PartManager partManager = new PartManagerImplementation();
        int partNumber = Integer.parseInt(mapEntry.getKey());
        int quantity = Integer.parseInt(mapEntry.getValue());

        PartManager.PartResponse reponse1 = partManager.SubmitPartForManufactureAndDelivery(partNumber, quantity, deliveryAddressValidation(doc.getElementsByTagName("deliveryaddress")));
        if (reponse1 != null) {
            if (reponse1.equals(PartManager.PartResponse.INVALID_PART)) {
                result = FAILURE;
                errorMessage = INVALID_PART;
            } else if (reponse1.equals(PartManager.PartResponse.OUT_OF_STOCK)) {
                result = FAILURE;
                errorMessage = OUT_OF_STOCK;
            } else if (reponse1.equals(PartManager.PartResponse.NO_LONGER_MANUFACTURED)) {
                result = FAILURE;
                errorMessage = NO_LONGER_MANUFACTURED;
            } else if (reponse1.equals(PartManager.PartResponse.SUCCESS)) {
                result = SUCCESS;
                errorMessage = EMPTY;
            }
        } else {
            result = FAILURE;
            errorMessage = SOMETHING_WENT_WRONG;
        }
        response.append("<item>\n").append("<partnumber>").append(mapEntry.getKey()).append("</partnumber>\n").append("<quantity>").append(mapEntry.getValue()).append("</quantity>\n").append("<result>").append(result).append("</result>\n").append("<errormessage>").append(errorMessage).append("</errormessage>\n").append(" </item>\n");
    }

    private boolean validateOrderAndAddress(HashMap<String, String> validateOrderMap, DeliveryAddress deliveryAddressObj) {
        return deliveryAddressObj != null && !validateOrderMap.isEmpty();
    }
    @Override
    public Document convertToDocument(File file) throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        doc.getDocumentElement().normalize();
        return doc;
    }
}
