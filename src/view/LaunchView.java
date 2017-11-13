package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class LaunchView extends JFrame {

	private JPanel contentPane;
	private JTextField txtFichero, txtTitulo, txtAutor, txtAnyo, txtEditor, txtPaginas;
	private JButton btnParsear, btnSerializar, btnAnadirAutor, btnEliminar;
	private JList<String> list;
	private JButton btnAnadirLibro;
	private JLabel results;

	public LaunchView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Introduzca el nombre de un fichero:");
		lblNewLabel.setBounds(27, 11, 254, 19);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblNewLabel);
		
		txtFichero = new JTextField();
		txtFichero.setBounds(27, 33, 293, 33);
		contentPane.add(txtFichero);
		txtFichero.setColumns(10);
		
		btnParsear = new JButton("Parsear");
		btnParsear.setBounds(341, 33, 100, 33);
		contentPane.add(btnParsear);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 77, 485, 2);
		contentPane.add(separator);
		
		JPanel serializar = new JPanel();
		serializar.setBounds(27, 94, 414, 242);
		contentPane.add(serializar);
		serializar.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Serializar libros");
		lblNewLabel_1.setBounds(0, 0, 225, 14);
		serializar.add(lblNewLabel_1);
		
		list = new JList<String>();
	    list.setModel(new DefaultListModel());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(248, 175, 166, 151);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(241, 23, 173, 151);
		scrollPane.setViewportView(list);
		serializar.add(scrollPane);
		
		JPanel camposLibro = new JPanel();
		camposLibro.setBounds(0, 23, 225, 217);
		serializar.add(camposLibro);
		camposLibro.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Titulo:");
		lblNewLabel_2.setBounds(15, 0, 189, 14);
		camposLibro.add(lblNewLabel_2);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(0, 14, 225, 29);
		camposLibro.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Autor:");
		lblNewLabel_3.setBounds(15, 43, 189, 14);
		camposLibro.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 57, 225, 29);
		camposLibro.add(panel);
		panel.setLayout(null);
		
		txtAutor = new JTextField();
		txtAutor.setBounds(0, 0, 182, 29);
		panel.add(txtAutor);
		txtAutor.setColumns(10);
		
		btnAnadirAutor = new JButton("+");
		btnAnadirAutor.setBounds(184, 0, 41, 28);
		panel.add(btnAnadirAutor);
		
		JLabel lblNewLabel_4 = new JLabel("A\u00F1o:");
		lblNewLabel_4.setBounds(15, 89, 189, 14);
		camposLibro.add(lblNewLabel_4);
		
		txtAnyo = new JTextField();
		txtAnyo.setBounds(0, 103, 225, 29);
		camposLibro.add(txtAnyo);
		txtAnyo.setColumns(10);
				
		JLabel lblNewLabel_5 = new JLabel("Editor:");
		lblNewLabel_5.setBounds(15, 132, 189, 14);
		camposLibro.add(lblNewLabel_5);
		
		txtEditor = new JTextField();
		txtEditor.setBounds(0, 146, 225, 29);
		camposLibro.add(txtEditor);
		txtEditor.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("P\u00E1ginas:");
		lblNewLabel_6.setBounds(15, 175, 189, 14);
		camposLibro.add(lblNewLabel_6);
		
		txtPaginas = new JTextField();
		txtPaginas.setBounds(0, 189, 225, 29);
		camposLibro.add(txtPaginas);
		txtPaginas.setColumns(10);
		
		btnEliminar = new JButton("Eliminar Autor");
		btnEliminar.setBounds(241, 185, 173, 23);
		serializar.add(btnEliminar);
		
		btnAnadirLibro = new JButton("A\u00F1adir Libro");
		btnAnadirLibro.setBounds(241, 217, 173, 23);
		serializar.add(btnAnadirLibro);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 377, 414, 33);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnSerializar = new JButton("Serializar");
		btnSerializar.setBounds(159, 0, 100, 33);
		panel_1.add(btnSerializar);
		
		results = new JLabel("");
		results.setBounds(27, 347, 414, 19);
		contentPane.add(results);
		this.setTitle("Ficheros con formato");
		this.setVisible(true);
	}

	public JTextField getTxtFichero() {
		return txtFichero;
	}

	public void setTxtFichero(JTextField txtFichero) {
		this.txtFichero = txtFichero;
	}

	public JButton getBtnParsear() {
		return btnParsear;
	}

	public void setBtnParsear(JButton btnParsear) {
		this.btnParsear = btnParsear;
	}

	public JButton getBtnSerializar() {
		return btnSerializar;
	}

	public void setBtnSerializar(JButton btnSerializar) {
		this.btnSerializar = btnSerializar;
	}
	
	public void showError(String m){
		JOptionPane.showMessageDialog(this.contentPane, m, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public JTextField getTxtTitulo() {
		return txtTitulo;
	}

	public void setTxtTitulo(JTextField txtTitulo) {
		this.txtTitulo = txtTitulo;
	}

	public JTextField getTxtAutor() {
		return txtAutor;
	}

	public void setTxtAutor(JTextField txtAutor) {
		this.txtAutor = txtAutor;
	}

	public JTextField getTxtanyo() {
		return txtAnyo;
	}

	public void setTxtanyo(JTextField txtanyo) {
		this.txtAnyo = txtanyo;
	}

	public JTextField getTxtEditor() {
		return txtEditor;
	}

	public void setTxtEditor(JTextField txtEditor) {
		this.txtEditor = txtEditor;
	}

	public JTextField getTxtPaginas() {
		return txtPaginas;
	}

	public void setTxtPaginas(JTextField txtPaginas) {
		this.txtPaginas = txtPaginas;
	}

	public JButton getBtnAnadir() {
		return btnAnadirAutor;
	}

	public void setBtnAnadir(JButton btnAnadir) {
		this.btnAnadirAutor = btnAnadir;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}

	public JButton getBtnAnadirAutor() {
		return btnAnadirAutor;
	}

	public void setBtnAnadirAutor(JButton btnAnadirAutor) {
		this.btnAnadirAutor = btnAnadirAutor;
	}

	public JButton getBtnAnadirLibro() {
		return btnAnadirLibro;
	}

	public void setBtnAnadirLibro(JButton btnAnadirLibro) {
		this.btnAnadirLibro = btnAnadirLibro;
	}	
	
	public JTextField getTxtAnyo() {
		return txtAnyo;
	}

	public void setTxtAnyo(JTextField txtAnyo) {
		this.txtAnyo = txtAnyo;
	}

	public JLabel getResults() {
		return results;
	}

	public void setResults(JLabel results) {
		this.results = results;
	}

	public void limpiarCampos() {
		txtTitulo.setText("");
		txtAutor.setText("");
		txtAnyo.setText("");
		txtEditor.setText("");
		txtPaginas.setText("");
		DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
		model.clear();
	}
	
	public void anadirItemLista(String item) {
	    DefaultListModel<String> lm;
		lm = (DefaultListModel<String>) list.getModel();
		
		lm.addElement(item);
		list.setModel(lm);	
	}
	
	public void eliminarItemLista(String item) {
	    DefaultListModel<String> lm;
		lm = (DefaultListModel<String>) list.getModel();
		
		lm.removeElement(item);
		list.setModel(lm);	
	}
	
}
