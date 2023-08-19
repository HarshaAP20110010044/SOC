import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XMLToHTMLParser {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("details.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("person");

            StringBuilder tableHtml = new StringBuilder("<table><tr><th>Name</th><th>Age</th></tr>");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getAttribute("name");
                    String age = element.getAttribute("age");

                    tableHtml.append("<tr><td>").append(name).append("</td><td>").append(age).append("</td></tr>");
                }
            }
            tableHtml.append("</table>");

            try (PrintWriter out = new PrintWriter("output.html")) {
                out.println("<!DOCTYPE html><html><head><title>XML to HTML Table</title></head><body>");
                out.println("<h1>XML to HTML Table</h1>");
                out.println(tableHtml.toString());
                out.println("</body></html>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
