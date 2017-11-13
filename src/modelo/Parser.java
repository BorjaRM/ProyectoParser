package modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<LibroVO> libros = null;

	public Parser() {
		libros = new ArrayList<LibroVO>();
	}

	public void parseFicheroXml(String fichero) throws SAXException, IOException, ParserConfigurationException {
		// Creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		// Creamos un documentbuilder
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		// Parseamos el XML y obtenemos una representación DOM
		dom = db.parse(fichero);	
	}

	public void parseDocument() {
		// Obtenemos el elemento raíz
		Element docElement = dom.getDocumentElement();

		// Obtenemos el nodelist de elementos
		NodeList librosXml = docElement.getElementsByTagName("libro");
		if (librosXml != null && librosXml.getLength() > 0) {
			for (int i = 0; i < librosXml.getLength(); i++) {
				// Obtenemos un elemento de la lista (libro)
				Element libroXml = (Element) librosXml.item(i);
				// Obtenemos un libro
				LibroVO libro = getLibro(libroXml);
				// Lo añadimos al array
				libros.add(libro);
			}
		}
	}
	
	private LibroVO getLibro(Element libroXml){
		String titulo,anyo,editor,paginas;
		ArrayList<String> autores = new ArrayList<String>();

		//Para cada elemento libro, obtenemos sus parametros
		titulo = getTextValue(libroXml, "titulo");
		autores = getAutores(libroXml);
		anyo = getAttributeFromNode(libroXml, "titulo","anyo");
		editor = getTextValue(libroXml, "editor");
		paginas = getTextValue(libroXml, "paginas");

		//Creamos un nuevo libro con los elementos leídos del nodo
		LibroVO libro = new LibroVO(titulo,titulo,autores,anyo,editor,paginas);

		return libro;		
		
	}
	
	private String getTextValue(Element libroXml, String tagName) {
		String textVal = null;
		NodeList nl = libroXml.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element element = (Element)nl.item(0);
			textVal = element.getFirstChild().getNodeValue();
		}		
		return textVal;
	}
	
	private String getAttributeFromNode(Element libroXml, String tagName, String attribute) {
		NodeList nl = libroXml.getElementsByTagName(tagName);
		Element element = (Element)nl.item(0);
		if (element.hasAttribute(attribute)) {
			return element.getAttribute(attribute);
		}
		return null;	
	}
	
	private ArrayList<String> getAutores(Element e) {
		ArrayList<String> autores = new ArrayList<String>();
		
		NodeList autoresXml = e.getElementsByTagName("nombre");
		if(autoresXml != null && autoresXml.getLength() > 0) {
			for(int i = 0; i < autoresXml.getLength(); i++) {
				Element element = (Element)autoresXml.item(i);
				String autor = element.getFirstChild().getNodeValue();
				autores.add(autor);
			}
		}		
		return autores;
	}
	
	private int getIntValue(Element ele, String tagName) {				
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	
	public void print(){

		Iterator<LibroVO> it = libros.iterator();
		while(it.hasNext()) {
			LibroVO l =(LibroVO) it.next();
			System.out.println(l.toString());
		}
	}
	
	

}