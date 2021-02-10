package fileManager;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class XML {

    public static XML statico = null;

    public Document doc;
    private File file;

    /**
     * Le um arquivo XML e torna ele legivel pela classe org.w3c.dom.Document
     * 
     * Para pegar elementos chame doc.getElementsByTagName
     * @param file Arquivo .xml
     * @throws java.lang.Exception Causa erro se ocorrer algum erro.
     */
    public XML(File file) throws Exception {
        try {
            this.file = file;

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
