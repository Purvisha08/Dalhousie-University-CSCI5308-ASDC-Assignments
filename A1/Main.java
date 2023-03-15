import java.io.File;

public class Main {
    public static void main(String[] args) {
       try {

           File inputFile = null;
           Testing testing = new Testing();
           testing.isDealerPresent(testing.parseXML(inputFile));

           String file = args[0];

           if (isFileNull(file)){
               ifFile(file);
           }
       }catch (Exception Ignore){}
    }
    private static void ifFile(String file) {
        File inputFile;
        XMLParsing xmlParsing = new XMLParsingImplementation() {};
        inputFile = new File(file);
        xmlParsing.parseXMLInputFile(inputFile);
    }

    private static boolean isFileNull(String file) {
        return file != null;
    }
}
