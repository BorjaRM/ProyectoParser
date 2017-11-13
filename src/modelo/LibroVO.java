package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class LibroVO implements Serializable{
	private String identificador;
	private String titulo;
	private ArrayList<String> autores;
	private String año;
	private String editor;
	private String paginas;
	
	public LibroVO(String identificador, String titulo, ArrayList<String> autores, String año, String editor, String paginas) {
		this.identificador = identificador;
		this.titulo = titulo;
		this.autores = autores;
		this.año = año;
		this.editor = editor;
		this.paginas = paginas;
	}
	
	@Override
	public String toString() {
		String descripcion = "Titulo: "+titulo+"\nAutor/es: \n"+autoresToString()+"Año: "+año+"\nEditor: "+editor+"\nPáginas: "+paginas+"\n";
		return descripcion;
	}

	private String autoresToString() {
		StringBuilder autores = new StringBuilder();
		for (String a: this.autores) {
			autores.append(" - " + a + "\n");
		}
		return autores.toString();
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}
}