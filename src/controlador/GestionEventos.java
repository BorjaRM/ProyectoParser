package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import modelo.GestionDatos;
import modelo.LibroVO;
import modelo.Marshaller;
import modelo.Parser;
import view.LaunchView;

public class GestionEventos {
	private GestionDatos model;
	private LaunchView view;
	private ActionListener al_Parsear, al_Serializar, al_AnadirAutor, al_Eliminar, al_AnadirLibro;
	
	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}
	
	public void control() {
		// Gestionamos el evento del boton "Parsear"
		al_Parsear = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					call_ParsearFichero();
				} catch (SAXException e) {
					view.showError("Indique un fichero XML adecuado");
				} catch (IOException e) {
					view.showError("Indique un fichero XML adecuado");
				} catch (ParserConfigurationException e) {
					view.showError("Se ha producido un error");
				}
			}
		};
		view.getBtnParsear().addActionListener(al_Parsear);

		// Gestionamos el evento del boton "Serializar"
		al_Serializar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					call_SerializarLibro();
				} catch (TransformerException e) {
					view.showError("Se ha producido un error");
				} catch (ParserConfigurationException e) {
					view.showError("Se ha producido un error");
				}
			}
		};
		view.getBtnSerializar().addActionListener(al_Serializar);
		
		// Gestionamos el evento del boton "Añadir +"
		al_AnadirAutor = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_AnadirAutor();
			}
		};
		view.getBtnAnadir().addActionListener(al_AnadirAutor);
		
		// Gestionamos el evento del boton "Eliminar Autor"
		al_Eliminar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_EliminarAutor();
			}
		};
		view.getBtnEliminar().addActionListener(al_Eliminar);

		// Gestionamos el evento del boton "Añadir Libro"
		al_AnadirLibro = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_AnadirLibro();
			}
		};
		view.getBtnAnadirLibro().addActionListener(al_AnadirLibro);
	}
	
	private void call_ParsearFichero() throws SAXException, IOException, ParserConfigurationException {
		String fichero = view.getTxtFichero().getText();
		
		Parser parser=new Parser();
		//parser.parseFicheroXml("C:\\Users\\Borja\\eclipse-workspace\\FicherosConFormato\\src\\xml\\"+fichero);
		parser.parseFicheroXml("src\\xml\\"+fichero);
		parser.parseDocument();
		parser.print();
		view.getResults().setText("Mostrando resultados por consola");
	}
	
	private void call_SerializarLibro() throws TransformerException, ParserConfigurationException {
		ArrayList<LibroVO> libros;
		
		
		// Obtenemos los libros del modelo
		libros = model.getLibros();

		// Comprobamos que existen libros
		if(libros.isEmpty())
			return;
		
		// Creamos el archivo XML
		Marshaller marshaller = new Marshaller(libros);
		marshaller.crearDocumento();
		marshaller.crearArbolDOM();
		
		File file = new File("src\\xml\\MiBiblioteca.xml");
		
		marshaller.escribirDocumentAXml(file);
		
		// Mostramos un mensaje para indicar cuantos libros fueron serializados
		view.getResults().setText(file.getName()+" creado con exito. "+libros.size()+" libro/s ha/n sido serializado/s");
	}	
	
	private void call_AnadirAutor() {
		String unAutor = null;

		// Obtenemos el nombre del autor
		if (view.getTxtAutor().getText().length() > 0) {
			unAutor = view.getTxtAutor().getText();
		} else {
			return;
		}
		
		// Guardamos el nombre del autor en el ArrayList autores que tenemos en el modelo
		model.guardarAutor(unAutor);
		
		// Añadimos el nombre del autor al DefaultListModel y asignamos este a la lista para que se vea en la vista
		view.anadirItemLista(unAutor);	
	}
	
	private void call_EliminarAutor() {
		int posicion;
		String autor;
		
		// Obtenemos el autor seleccionado (o -1 en caso de no haberlo)
		posicion = view.getList().getSelectedIndex();
		
		if (posicion > -1) {
			autor = model.getAutores().get(posicion);
			model.eliminarAutor(autor);
			// Actualizamos el DefaultListModel para que se actualice la vista
			view.eliminarItemLista(autor);
		}		
	}
	
	private void call_AnadirLibro() {
		String titulo,año,editor,paginas;
		ArrayList<String> autor;
		
		// Obtenemos el valor de los TextFields
		titulo = view.getTxtTitulo().getText();
		año = view.getTxtanyo().getText();
		editor = view.getTxtEditor().getText();
		paginas = view.getTxtPaginas().getText();
		
		// Recuperamos el ArrayList de autores que tenemos en el modelo
		autor = model.getAutores();
		
		// Consideramos que todos los campos son obligatorios para crear el libro
		if (titulo.length() != 0 && año.length() != 0 && autor.size() != 0 && editor.length() != 0 && paginas.length() != 0) {	
			model.guardarLibro(titulo, titulo, autor, año, editor, paginas);
			view.getResults().setText("El libro \""+titulo+"\" ha sido guardado correctamente");
			view.limpiarCampos();
		} else
			view.showError("Debe rellenar todos los campos para crear un nuevo libro");
		model.vaciarArrayAutores();
	}
}
