package clienteservidor;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class HalldelaFama extends JFrame {
	private static final long serialVersionUID = 1L;

	static JTextField cab = new JTextField();
	static JTextField cab2 = new JTextField();
	static JTextField cab3 = new JTextField();

	private static JTextField campo = new JTextField();
	private static JTextField campo2 = new JTextField();

	JButton botonDescargar = new JButton("Descargar fichero");
	JButton botonSalir = new JButton("Salir");

	@SuppressWarnings("rawtypes")
	static JList listaDirec = new JList();
	private final Container c = getContentPane();

	static FTPClient cliente = new FTPClient();// cliente FTP
	String servidor = "192.168.1.108";
	String user = "secreto";
	String pasw = "b6qeyuge";
	boolean login;
	static String direcInicial = "/";

	static String direcSelec = direcInicial;
	static String ficheroSelec = "";

	public HalldelaFama() throws IOException {
		super("HALL DE LA FAMA");
		
		cliente.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		cliente.connect(servidor);
		login = cliente.login(user, pasw);
		cliente.changeWorkingDirectory(direcInicial);
		FTPFile[] files = cliente.listFiles();
		llenarLista(files, direcInicial);

		campo.setText("<< ARBOL DE DIRECTORIOS CONSTRUIDO >>");
		cab.setText("Servidor FTP: " + servidor);
		cab2.setText("Usuario: " + user);
		cab3.setText("DIRECTORIO RAIZ: " + direcInicial);

		listaDirec.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane barraDesplazamiento = new JScrollPane(listaDirec);
		barraDesplazamiento.setPreferredSize(new Dimension(335, 420));
		barraDesplazamiento.setBounds(new Rectangle(5, 5, 335, 420));
		botonDescargar.setBounds(5, 430, 150, 50);
		botonSalir.setBounds(190, 430, 150, 50);
		c.add(botonDescargar);
		c.add(botonSalir);
		c.add(barraDesplazamiento);
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 500);
		setVisible(true);

		listaDirec.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent lse) {

				String fic = "";
				
				if (lse.getValueIsAdjusting()) {
					ficheroSelec = "";
					fic = listaDirec.getSelectedValue().toString();
				}
				
				if (listaDirec.getSelectedIndex() == 0) {
					
					if (!fic.equals(direcInicial)) {
						
						try {
							cliente.changeToParentDirectory();
							direcSelec = cliente.printWorkingDirectory();
							FTPFile[] ff2 = null;
							// directorio de trabajo actual=directorio padre
							cliente.changeWorkingDirectory(direcSelec);
							// se obtienen ficheros y directorios
							ff2 = cliente.listFiles();
							campo.setText("");
							// se llena la lista con ficheros del directorio padre
							llenarLista(ff2, direcSelec);
						} 
						catch (IOException e) {e.printStackTrace();}
					}
				}
					
				else {
					
					if (fic.contains("(DIR)")) {

						try {
							fic = fic.substring(6);
							String direcSelec2 = "";
							
							if (direcSelec.equals("/")) direcSelec2 = direcSelec + fic;
							else direcSelec2 = direcSelec + "/" + fic;
							FTPFile[] ff2 = null;
							cliente.changeWorkingDirectory(direcSelec2);
							ff2 = cliente.listFiles();
							campo.setText("DIRECTORIO: " + fic + ", " + ff2.length + " elementos");
							direcSelec = direcSelec2;
							llenarLista(ff2, direcSelec);
							
						} 
						catch (IOException e2) {e2.printStackTrace();}
					} 
					
					else {
						
						ficheroSelec = direcSelec;
						if (direcSelec.equals("/")) direcSelec += fic;
						else direcSelec += "/" + fic;
						campo.setText("FICHERO SELECCIONADO: " + ficheroSelec);
						ficheroSelec = fic;

					} 
				} 
				
				campo2.setText("DIRECTORIO ACTUAL: " + direcSelec);
			}
		});
		
		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {cliente.disconnect();} 
				catch (IOException e1) {e1.printStackTrace();}
				System.exit(0);
			}
		}); 
		
		botonDescargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String directorio = direcSelec;
				if (!direcSelec.equals("/")) directorio = directorio + "/";
				if (!direcSelec.equals("")) {DescargarFichero(directorio + ficheroSelec, ficheroSelec);}
			}
		});
	}

	// CLASE PARA RELLENAR LA LISTA
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void llenarLista(FTPFile[] files, String direc2) {
		if (files == null) return;

		DefaultListModel modeloLista = new DefaultListModel();
		modeloLista = new DefaultListModel();

		listaDirec.setForeground(Color.blue);
		Font fuente = new Font("Courier", Font.PLAIN, 12);
		listaDirec.setFont(fuente);

		listaDirec.removeAll();
		try {cliente.changeWorkingDirectory(direc2);} 
		catch (IOException e) {e.printStackTrace();}
		direcSelec = direc2; 

		modeloLista.addElement(direc2);

		for (int i = 0; i < files.length; i++) {
			if (!(files[i].getName()).equals(".") && !(files[i].getName()).equals("..")) {
				String f = files[i].getName();
				
				if (files[i].isDirectory()) f = "(DIR) " + f;
				modeloLista.addElement(f);
			} 
		}
		
		try {listaDirec.setModel(modeloLista);} 
		catch (NullPointerException n) {;}
	}

	// CLASE PARA DESCARGAR FICHEROS
	private void DescargarFichero(String NombreCompleto, String nombreFichero) {
		File file;
		String archivoyCarpetaDestino = "";
		String carpetaDestino = "";
		JFileChooser f = new JFileChooser();

		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		f.setDialogTitle("Selecciona el Directorio donde Descargar el Fichero");
		int returnVal = f.showDialog(null, "Descargar");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = f.getSelectedFile();
			carpetaDestino = (file.getAbsolutePath()).toString();
			archivoyCarpetaDestino = carpetaDestino + File.separator + nombreFichero;
			
			try {
				cliente.setFileType(FTP.BINARY_FILE_TYPE);
				BufferedOutputStream out = new BufferedOutputStream( new FileOutputStream(archivoyCarpetaDestino));
				if (cliente.retrieveFile(NombreCompleto, out)) JOptionPane.showMessageDialog(null, nombreFichero + " => Se ha descargado correctamente ...");
				else JOptionPane.showMessageDialog(null, nombreFichero + " => No se ha podido descargar ..."); 
				out.close();
			} 
			catch (IOException e1) {e1.printStackTrace();}
		}
	} 
}