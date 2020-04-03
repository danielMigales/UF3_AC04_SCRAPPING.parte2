package base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Text;

/**
 * @author Daniel Migales Puertas
 *
 */
public class Gestor {

    //VARIABLES
    ArrayList<Peliculas> peliculas = new ArrayList<>();
    Peliculas pelicula;

    //METODO PARA ANALIZAR EL HTML
    public void scrappingHtml() {

        String filePath = "peliculas.html";
        String ENCODING = "UTF-8";
        File inputFile = new File(filePath);
        // parse file as HTML document
        Document document = null;

        try {
            document = Jsoup.parse(inputFile, ENCODING);
            Elements links = document.select("a[href]"); //busca todos los enlaces
            Elements tags = document.getElementsByTag("i"); //busca los que tengan el tag <i>
            Elements select = document.select("i>a"); //busca los que tengan un <i> delante de un enlace <a
            int contador = 1;

            for (Element link : select) {

                String url = link.attr("abs:href");
                String titulo = link.attr("title");

                if (!url.contains("index.php")) {
                    pelicula = new Peliculas(url, titulo, contador);
                    peliculas.add(pelicula);
                    contador++;
                    System.out.println(pelicula);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //METODO PARA CREAR EL XML A PARTIR DEL ARRAY CREADO LEYENDO EL HTML
    public void crearXML() throws IOException, ParserConfigurationException, TransformerException {

        String nombreDocumento = "peliculas_oscarizadas";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        org.w3c.dom.Document document = implementation.createDocument(null, nombreDocumento, null);
        document.setXmlVersion("1.0");

        for (Peliculas listadoPeliculas : peliculas) {

            org.w3c.dom.Element raiz = document.createElement("pelicula");
            document.getDocumentElement().appendChild(raiz);

            String hipervinculo = listadoPeliculas.getEnlace();
            CrearElemento("hipervinculo", hipervinculo, raiz, document);
            String titulo = listadoPeliculas.getTitulo();
            CrearElemento("titulo", titulo, raiz, document);

            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File(nombreDocumento + ".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
    }

    public static void CrearElemento(String dato, String valor, org.w3c.dom.Element raiz, org.w3c.dom.Document document) {

        org.w3c.dom.Element elem = document.createElement(dato);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

}
