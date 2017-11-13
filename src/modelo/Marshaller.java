package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {
	
	private Document dom = null;
	private ArrayList<LibroVO> libros = null;

	public Marshaller(ArrayList<LibroVO> libros) {
		this.libros = libros;
	}
	
	public void crearDocumento() throws ParserConfigurationException{
		// Creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// Creamos un documentbuilder
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		// Creamos una instancia de DOM 
		dom = db.newDocument();	
	}
	
	public void crearArbolDOM() {

		// Creamos el elemento raíz "biblioteca"
		Element documentElement = dom.createElement("biblioteca");
		dom.appendChild(documentElement);

		// Recorremos
		Iterator it = libros.iterator();
		while (it.hasNext()) {
			LibroVO l = (LibroVO) it.next();
			// Para cada objeto libro creamos el elemento <libro> y lo añadimos a la raíz
			Element libroElement = setLibro(l);
			documentElement.appendChild(libroElement);
		}
	}
	
	private Element setLibro(LibroVO libro) {

		// Creamos el elemento libro
		Element libroElement = dom.createElement("libro");

		// Creamos el elemento titulo con su atributo año y con el nodo de texto y lo añadimos al elemento libro
		Element tituloElement = dom.createElement("titulo");		
		Text tituloTexto = dom.createTextNode(libro.getTitulo());
		tituloElement.appendChild(tituloTexto);
		tituloElement.setAttribute("anyo", libro.getAño());
		libroElement.appendChild(tituloElement);
		
		// Creamos el elemento autor y lo añadimos al elemento persona
		Element autorElement = dom.createElement("autor");
		libroElement.appendChild(autorElement);
		
		// Creamos el elemento nombre y lo añadimos al elemento autor
		for (int i = 0; i < libro.getAutores().size(); i++) {
			Element nombreElement = dom.createElement("nombre");
			Text nombreText = dom.createTextNode(libro.getAutores().get(i));
			nombreElement.appendChild(nombreText);
			autorElement.appendChild(nombreElement);
		}
		
		// Creamos el elemento editor y el nodo de texto y lo añadimos al elemento libro
		Element editorElement = dom.createElement("editor");
		Text editorTexto = dom.createTextNode(libro.getEditor());
		editorElement.appendChild(editorTexto);
		libroElement.appendChild(editorElement);
		
		// Creamos el elemento paginas y el nodo de texto y lo añadimos al elemento libro
		Element paginasElement = dom.createElement("paginas");
		Text paginasTexto = dom.createTextNode(libro.getPaginas());
		paginasElement.appendChild(paginasTexto);
		libroElement.appendChild(paginasElement);
		
		return libroElement;
	}
	
	public void escribirDocumentAXml(File file) throws TransformerException {

		// Creamos una instacia para escribir el resultado
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		// Especificamos dónde escribimos y la fuente de datos
		StreamResult result = new StreamResult(file);
		DOMSource source = new DOMSource(dom);
		trans.transform(source, result);

	}

}