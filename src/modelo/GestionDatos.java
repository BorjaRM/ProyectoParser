package modelo;

import java.util.ArrayList;

public class GestionDatos {
	
	private ArrayList<LibroVO> libros;
	private ArrayList<String> autoresTemp;

	
	public GestionDatos() {
		libros = new ArrayList<LibroVO>();
		autoresTemp = new ArrayList<String>();
	}
	
	public void guardarAutor(String unAutor) {
		autoresTemp.add(unAutor);
	}

	public void guardarLibro(String id, String titulo, ArrayList<String> autor, String año, String editor, String paginas) {
		LibroVO unLibro = new LibroVO(id, titulo, autor, año, editor, paginas);
		libros.add(unLibro);
	}
	
	public void vaciarArrayAutores() {
		autoresTemp = new ArrayList<String>();
	}
	
	public void eliminarAutor(String unAutor) {
		autoresTemp.remove(unAutor);
	}

	public ArrayList<LibroVO> getLibros() {
		return libros;
	}

	public void setLibros(ArrayList<LibroVO> libros) {
		this.libros = libros;
	}

	public ArrayList<String> getAutores() {
		return autoresTemp;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autoresTemp = autores;
	}
}
