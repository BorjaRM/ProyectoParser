package principal;

import controlador.GestionEventos;
import modelo.GestionDatos;
import view.LaunchView;

public class Inicio {

	public static void main(String[] args) {
		
		// Creamos el model
		GestionDatos model = new GestionDatos();
		// Creamos la vista
		LaunchView view = new LaunchView();
		// Creamos el controlador y llamamos al metodo control
		GestionEventos controller = new GestionEventos(model, view);
		controller.control();
	}

}
