import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class Testing {
    public static final String INVALID_PART = "Invalid Part";
    public static final String OUT_OF_STOCK = "Out of Stock";
    public static final String NO_LONGER_MANUFACTURED = "No Longer Manufactured";
    public static final String FAILURE = "Failure";
    public static final String SUCCESS = "Success";
    public static final String EMPTY = "";

    public static final String INVALID_ITEM_ENTRY = "Invalid item entry";

    public static final String REGX = "[0-9]+";

    public Document parseXML(File file){
        try {
            XMLParsing xmlParsing = new XMLParsingImplementation();
            if (file!=null){
                return xmlParsing.convertToDocument(file);
            }else{
                String xmlFileContent = "<order>\n" +
                        "    <!-- The dealer submitting the order -->\n" +
                        "    <dealer>\n" +
                        "        <!-- The dealer ID, identifies the dealer. Must validate that the dealer is a known\n" +
                        "        dealer. -->\n" +
                        "        <dealerid>XXX-1234-ABCD-1234</dealerid>\n" +
                        "        <!-- The dealer access key, used by the security class to authenticate the dealer. -->\n" +
                        "        <dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>\n" +
                        "    </dealer>\n" +
                        "    <!-- A list of items ordered by the dealer. -->\n" +
                        "    <orderitems>\n" +
                        "        <!-- An item in the list of items ordered by the dealer. -->\n" +
                        "        <item>\n" +
                        "            <!-- The part number to submit to PARTMANAGER. -->\n" +
                        "            <partnumber>1234</partnumber>\n" +
                        "            <!-- The quantity ordered to submit to PARTMANAGER. -->\n" +
                        "            <quantity>2</quantity>\n" +
                        "        </item>\n" +
                        "        <!-- Another item, there can be an unlimited number of items in the orderitems list. -->\n" +
                        "        <item>\n" +
                        "            <partnumber>5678</partnumber>\n" +
                        "            <quantity>25</quantity>\n" +
                        "        </item>\n" +
                        "    </orderitems>\n" +
                        "    <!-- The delivery address to send the parts to. -->\n" +
                        "    <deliveryaddress>\n" +
                        "        <!-- The name field can be blank. -->\n" +
                        "        <name>Mrs. Jane Smith</name>\n" +
                        "        <!-- The street is required. -->\n" +
                        "        <street>35 Streetname</street>\n" +
                        "        <!-- The city is required. -->\n" +
                        "        <city>Halifax</city>\n" +
                        "        <!-- The province is required. -->\n" +
                        "        <province>NS</province>\n" +
                        "        <!-- The postal code is required. -->\n" +
                        "        <postalcode>B2T1A4</postalcode>\n" +
                        "    </deliveryaddress>\n" +
                        "</order>";
                if (convertToXML(xmlFileContent).exists()) {
                    return xmlParsing.convertToDocument(convertToXML(xmlFileContent));
                }else{
                    System.out.println("FAILED - Parse file to document");
                }
            }
        }catch (Exception e){
            System.out.println("FAILED - Parse file to document ");
        }
        return null;
    }

    void isDealerPresent(Document document){
        if (document.getElementsByTagName("dealer")!=null) {
            System.out.println("PASS - Check Dealer Tag");
            Node dealerTagNode = document.getElementsByTagName("dealer").item(0);
            Element eElement = (Element) dealerTagNode;
            boolean checkDealerID = isDealerIDProvided(eElement.getElementsByTagName("dealerid").item(0).getTextContent());
            boolean checkDealerAccessKey = isDealerAccessKeyProvided(eElement.getElementsByTagName("dealeraccesskey").item(0).getTextContent());
            if (checkDealerID && checkDealerAccessKey){
                dealerAuthorization(eElement.getElementsByTagName("dealerid").item(0).getTextContent(),eElement.getElementsByTagName("dealeraccesskey").item(0).getTextContent(),document);
            }
        }else{
            System.out.println("FAILED - Check Dealer Tag");
        }
    }

    boolean isDealerIDProvided(String dealerId){
        if (dealerId!=null){
            System.out.println("PASS - Validated Dealer Id ");
            return true;
        }else{
            System.out.println("FAILED - Validated Dealer Id");
            return false;
        }
    }

    boolean isDealerAccessKeyProvided(String dealerAccessKey){
        if (dealerAccessKey!=null){
            System.out.println("PASS - Dealer Access Key Present");
            return true;
        }else{
            System.out.println("FAILED - Dealer Access Key Present");
            return false;
        }

    }

    void dealerAuthorization(String dealerId, String dealerAccessKey, Document document){
        boolean flag = false;
        try {
            if (idDealerIDorDealerAccessKeyNull(dealerId, dealerAccessKey)) {
                Security srty = new SecurityImplementation();
                flag = srty.IsDealerAuthorized(dealerId, dealerAccessKey);
                if (flag){
                    System.out.println("PASS -Check Dealer Authorized ");
                    checkOrderItems(document);
                }else{
                    System.out.println("FAILED - Check Dealer Authorized");
                }
            }
        }catch (Exception e){
            System.out.println("FAILED - Check Dealer Authorized");
        }
    }

    private boolean idDealerIDorDealerAccessKeyNull(String dealerId, String dealerAccessKey) {
        return dealerId != null && dealerAccessKey != null;
    }

    private void checkOrderItems(Document document) {
        if (document.getElementsByTagName("orderitems").getLength()>=0){
            System.out.println("PASS - Check Order Items");
            OrderValidation(document.getElementsByTagName("orderitems").item(0),document);
        }else{
            System.out.println("FAILED - Check Order Items");
        }
    }

    private void OrderValidation(Node node,Document document) {
        try {
            HashMap<String, String> map = new HashMap<>();
            if (node.getChildNodes().getLength() >= 0) {
                map = new XMLParsingImplementation().orderValidation(node.getChildNodes());
                System.out.println("PASSED - Order Validation");
                checkDeliveryTag(map,document);
            } else {
                System.out.println("FAILED - Order Validation");
            }
        }catch (Exception e){
            System.out.println("FAILED - Order Validation");
        }
    }

    private void checkDeliveryTag(HashMap<String,String> partNumberMap,Document document) {

        try {
            if (document.getElementsByTagName("deliveryaddress").getLength() >= 0) {
                System.out.println("PASS - Delivery Tag Check");
                boolean check = addressObject(new XMLParsingImplementation().deliveryAddressValidation(document.getElementsByTagName("deliveryaddress")));
                extracted(partNumberMap, document, check);
            } else {
                System.out.println("FAILED - Delivery Tag Check");
            }
        }catch (Exception e){
            System.out.println("FAILED - Delivery Tag Check");
        }
    }

    private void extracted(HashMap<String, String> partNumberMap, Document document, boolean check) throws Exception {
        if (check){
            partNumberAndAddressValidation(partNumberMap,new XMLParsingImplementation().deliveryAddressValidation(document.getElementsByTagName("deliveryaddress")));
        }
    }

    private void partNumberAndAddressValidation(HashMap<String, String> partNumberMap, DeliveryAddress deliveryAddress) {
        try {;
            StringBuilder itemReponse = new StringBuilder();
            for (Map.Entry<String, String> mapEntry : partNumberMap.entrySet()) {
                extracted(deliveryAddress, itemReponse, mapEntry);
            }
            String xmlFinalResponse = "<order>\n" +
                    "<orderitems>\n" +
                    itemReponse +
                    "  </orderitems>\n" +
                    "</order>";
            if (itemReponse.length() != 0) {
                System.out.println("PASS - Check Part Number");
            } else {
                System.out.println("FAILED - Check Part Number");
            }
        }catch (Exception e){
            System.out.println("FAILED - Check Part Number");
        }

    }

    private void extracted(DeliveryAddress deliveryAddress, StringBuilder response, Map.Entry<String, String> mapEntry) {
        String result = EMPTY;
        String errorMsg = EMPTY;
        PartManager partMng = new PartManagerImplementation();
        if (isaBoolean(mapEntry)) {
            int partNum = Integer.parseInt(mapEntry.getKey());
            int quantityInNumber = Integer.parseInt(mapEntry.getValue());

            PartManager.PartResponse partResponse = partMng.SubmitPartForManufactureAndDelivery(partNum, quantityInNumber, deliveryAddress);
            if (partResponse != null) {
                if (isEquals(partResponse)) {
                    result = FAILURE;
                    errorMsg = INVALID_PART;
                } else if (isaBoolean(partResponse)) {
                    result = FAILURE;
                    errorMsg = OUT_OF_STOCK;
                } else if (isEquals1(partResponse)) {
                    result = FAILURE;
                    errorMsg = NO_LONGER_MANUFACTURED;
                } else if (isEquals2(partResponse)) {
                    result = SUCCESS;
                    errorMsg = EMPTY;
                }
            }
        }else {
            result = FAILURE;
            errorMsg = INVALID_ITEM_ENTRY;
        }

        response.append("<item>\n").append("<partnumber>").append(mapEntry.getKey()).append("</partnumber>\n").append("<quantity>").append(mapEntry.getValue()).append("</quantity>\n").append("<result>").append(result).append("</result>\n").append("<errormessage>").append(errorMsg).append("</errormessage>\n").append(" </item>\n");
    }

    private boolean isEquals2(PartManager.PartResponse partResponse) {
        return partResponse.equals(PartManager.PartResponse.SUCCESS);
    }

    private boolean isEquals1(PartManager.PartResponse partResponse) {
        return partResponse.equals(PartManager.PartResponse.NO_LONGER_MANUFACTURED);
    }

    private boolean isaBoolean(PartManager.PartResponse partResponse) {
        return partResponse.equals(PartManager.PartResponse.OUT_OF_STOCK);
    }

    private boolean isEquals(PartManager.PartResponse partResponse) {
        return partResponse.equals(PartManager.PartResponse.INVALID_PART);
    }

    private boolean isaBoolean(Map.Entry<String, String> mapEntry) {
        return mapEntry.getKey() != null && mapEntry.getKey().matches(REGX) && mapEntry.getValue() != null && mapEntry.getValue().matches(REGX);
    }

    private boolean addressObject(DeliveryAddress deliveryAddress) {
        if (isDeliveryAdrNull(deliveryAddress)){
            if (isDeliverAddressNull(deliveryAddress)){
                System.out.println("FAILED - Check Delivery Address Parameter");
                return false;
            }else{
                System.out.println("PASS - Check Delivery Address Parameter");
                return true;
            }
        }else{
            System.out.println("FAILED - Check Delivery Address Parameter");
            return false;
        }
    }

    private boolean isDeliveryAdrNull(DeliveryAddress deliveryAddress) {
        return deliveryAddress != null;
    }

    private boolean isDeliverAddressNull(DeliveryAddress deliveryAddress) {
        return deliveryAddress.getName() == null ||
                deliveryAddress.getStreet() == null ||
                deliveryAddress.getCity() == null ||
                deliveryAddress.getProvince() == null ||
                deliveryAddress.getPostalCode() == null;
    }

    File convertToXML(String str){
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setNamespaceAware(true);

            StreamResult result = new StreamResult(new File("order.xml"));
            TransformerFactory.newInstance().newTransformer().transform(new DOMSource(docBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(str)))), result);
            System.out.println("PASS - Convert To XML Document File");
            return new File("order.xml");

        }catch (Exception e){
            System.out.println("FAILED - Convert To XML Document File");
        }
        return null;
    }


}
