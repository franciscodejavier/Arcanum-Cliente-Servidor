package es.studium.clientserver;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import es.studium.math.Consule;
import es.studium.math.Fondo;

public class ArcanumServidor extends JFrame implements WindowListener, MouseListener, KeyListener {
    private static final long serialVersionUID = 1;
    
    JTextField txtNumeroServidor = new JTextField();
    JButton btnOk = new JButton("Ok"); 					
    Fondo fondo = new Fondo();							
    Border raise = BorderFactory.createEtchedBorder(0);
    JScrollPane scrollPane = new JScrollPane(22, 32);
    JPanel panel = new JPanel();
    JLabel[] labels = new JLabel[1000];
    int contadorLineas = 0;
    int contador = 0;
    static String respuesta = "espera";
    GridBagConstraints gbc = new GridBagConstraints();
    JPasswordField txtNumeroSecreto = new JPasswordField();
    JButton btnOkk = new JButton("Encrypt");
    Socket miServicio;
    ServerSocket socketServicio;
    OutputStream outputStream;
    InputStream inputStream;
    DataOutputStream salidaDatos;
    DataInputStream entradaDatos;
    static String numeroSecretoServidor = "";
    static String numeroServidor = "";
    static String numeroCliente = "";
    static boolean existsNumeroSecretoCliente = false;
    private boolean opcion = true;
    Thread hiloBtnOkk;

    public ArcanumServidor() {
      
    	this.hiloBtnOkk = new Thread(new Runnable(){
            @Override
            public void run() {
                ArcanumServidor.numeroSecretoServidor = ArcanumServidor.this.txtNumeroSecreto.getText();
                if (ArcanumServidor.numeroSecretoServidor.length() != 4) {
                    ArcanumServidor.this.nuevaLinea("[ERROR] 4 d\u00edgitos", "RED");
                } else {
                    ArcanumServidor.this.Creacion2();
                    ArcanumServidor.this.nuevaLinea("[!] Cifrando n\u00famero secreto", "WHITE");
                    ArcanumServidor.this.nuevaLinea("[!] N\u00famero secreto cifrado", "GREEN");
                }
            }
        });
        
        this.setLayout(null);
        this.setTitle("Arcanum Servidor");
        this.setLocationRelativeTo(null);
        this.setSize(639, 480);
        this.setVisible(true);
        this.setResizable(false);
        this.add(this.fondo);
        this.fondo.setLayout(null);
        this.fondo.setBackground("img/monitor.jpg");
        this.fondo.setVisible(true);
        this.fondo.setOpaque(false);
        this.fondo.setBounds(0, 0, 639, 480);
        this.getContentPane().add(this.panel);
        this.fondo.add(this.scrollPane);
        this.scrollPane.setBounds(156, 57, 337, 290);
        this.panel.setBackground(new Color(1, 1, 1, 250));
        this.panel.setLayout(new GridBagLayout());
        this.gbc.insets = new Insets(1, 1, 1, 300);
        this.gbc.anchor = 18;
      
        Thread hiloInicio = new Thread(new Runnable(){
            @Override
            public void run() {
                ArcanumServidor.this.Encendido();
                ArcanumServidor.this.Creacion();
                ArcanumServidor.this.btnOkk.addActionListener(event -> {
                    ArcanumServidor.this.hiloBtnOkk.start();
                }
                );
                ArcanumServidor.this.btnOk.addActionListener(event -> {
                    ArcanumServidor.this.BtnOk();
                }
                );
                try {
                    ArcanumServidor.this.socketServicio = new ServerSocket(5555);
                    ArcanumServidor.this.nuevaLinea("Servicio en escucha en puerto: 5555", "BLUE");
                    ArcanumServidor.this.miServicio = ArcanumServidor.this.socketServicio.accept();
                    Thread hilo = new Thread(new Runnable(){
                        @Override
                        public void run() {
                            while (ArcanumServidor.this.opcion) {
                                ArcanumServidor.this.recibirDatos();
                            }
                        }
                    });
                    hilo.start();
                }
                catch (Exception ex) {
                    ArcanumServidor.this.nuevaLinea("Error al abrir los sockets", "RED");
                }
            }
        });
        hiloInicio.start();
        
        this.addWindowListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.txtNumeroServidor.addKeyListener(this);
    }

    public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
        new es.studium.clientserver.ArcanumServidor();
    }

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void windowActivated(WindowEvent arg0) {}

    @Override
    public void windowClosed(WindowEvent arg0) {}

    @Override
    public void windowClosing(WindowEvent arg0) {
        System.exit(0);
        try {
            this.miServicio.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void windowDeactivated(WindowEvent arg0) {}
    public void windowDeiconified(WindowEvent arg0) {}
    public void windowIconified(WindowEvent arg0) {}
    public void windowOpened(WindowEvent arg0) {}
    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == 10) {
            this.btnOk.doClick();
        }
    }
    public void keyReleased(KeyEvent ke) {}
    public void keyTyped(KeyEvent ke) {}
    
    public void nuevaLinea(String texto, String color) {
        this.labels[this.contadorLineas] = new JLabel(texto);
        if (color.equals("RED")) {
            this.labels[this.contadorLineas].setForeground(Color.RED);
        } else if (color.equals("WHITE")) {
            this.labels[this.contadorLineas].setForeground(Color.WHITE);
        } else if (color.equals("GREEN")) {
            this.labels[this.contadorLineas].setForeground(Color.GREEN);
        } else if (color.equals("GRAY")) {
            this.labels[this.contadorLineas].setForeground(Color.GRAY);
        } else if (color.equals("BLUE")) {
            this.labels[this.contadorLineas].setForeground(Color.BLUE);
        } else {
            this.labels[this.contadorLineas].setForeground(Color.WHITE);
        }
        this.gbc.gridx = 0;
        this.gbc.gridy = this.contadorLineas;
        this.panel.add((Component)this.labels[this.contadorLineas], this.gbc);
        this.scrollPane.setViewportView(this.panel);
        ++this.contadorLineas;
        try {
            Thread.sleep(500);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar().getMaximum());
    }

    public void enviarDatos(String datos) {
        try {
            this.outputStream = this.miServicio.getOutputStream();
            this.salidaDatos = new DataOutputStream(this.outputStream);
            this.salidaDatos.writeUTF(datos);
            this.salidaDatos.flush();
        }
        catch (IOException ex) {
            Logger.getLogger(ArcanumServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recibirDatos() {
        try {
            this.inputStream = this.miServicio.getInputStream();
            this.entradaDatos = new DataInputStream(this.inputStream);
            numeroCliente = this.entradaDatos.readUTF();
            boolean esNumero = true;
            try {
                Integer.parseInt(numeroCliente);
            }
            catch (Exception e) {
                esNumero = false;
            }
            if (esNumero) {
                respuesta = Consule.Consulta(numeroSecretoServidor, numeroCliente);
                this.nuevaLinea("<< " + numeroCliente + " --> " + respuesta, "GRAY");
                this.enviarDatos(respuesta);
            } else {
                ++this.contador;
                this.nuevaLinea(">>" + numeroServidor + " --> " + numeroCliente, "WHITE");
                if (numeroCliente.equals("exploited")) {
                    this.Perdedor();
                    this.nuevaLinea("[ERROR] Error en sistema", "RED");
                    this.nuevaLinea("[ERROR] System HACKED", "RED");
                    this.nuevaLinea("[hacked] Has sido hackeado por cliente", "RED");
                    this.nuevaLinea("[!] Perdiendo control del servidor...", "WHITE");
                    this.nuevaLinea("cliente@system# Lo siento", "RED");
                    this.cerrarTodo();
                } else if (numeroCliente.equals("mmmm")) {
                    this.Ganador();
                    this.enviarDatos("exploited");
                    this.nuevaLinea("[hacked] Has hackeado al cliente", "GREEN");
                    this.nuevaLinea("[!] Intentos realizados " + this.contador, "WHITE");
                    this.nuevaLinea("cliente@system# Enhorabuena", "GREEN");
                    this.cerrarTodo();
                }
            }
        }
        catch (IOException ex) {
            Logger.getLogger(ArcanumServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Ganador() {
        this.txtNumeroServidor.setEditable(false);
        this.txtNumeroServidor.setBorder(this.raise);
        this.txtNumeroServidor.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
        this.btnOk.setBorder(this.raise);
        this.btnOk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
        this.btnOk.setText("Salir");
        this.btnOk.addActionListener(newEvent -> {
            this.setVisible(false);
        }
        );
    }

    public void Perdedor() {
        this.txtNumeroServidor.setEditable(false);
        this.txtNumeroServidor.setBorder(this.raise);
        this.txtNumeroServidor.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));
        this.btnOk.setBorder(this.raise);
        this.btnOk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));
        this.btnOk.setText("Salir");
        this.btnOk.addActionListener(newEvent -> {
            this.setVisible(false);
        }
        );
    }

    public void cerrarTodo() {
        try {
            this.opcion = false;
            this.salidaDatos.close();
            this.entradaDatos.close();
            this.socketServicio.close();
            this.miServicio.close();
        }
        catch (IOException ex) {
            Logger.getLogger(ArcanumServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Encendido() {
        this.nuevaLinea("[!] Iniciando sistema...", "WHITE");
        this.nuevaLinea("[!] Sistema iniciado", "WHITE");
        this.nuevaLinea("[!] Aplicando reglas de firewall", "WHITE");
        this.nuevaLinea("[!] Hardening sistema...", "WHITE");
        this.nuevaLinea("[!] Sistema iniciado correctamente", "WHITE");
        this.nuevaLinea("[process] Introduzca el secreto", "BLUE");
    }

    public void Creacion() {
        this.add(this.txtNumeroSecreto);
        this.txtNumeroSecreto.setBounds(170, 400, 200, 40);
        this.txtNumeroSecreto.setBackground(Color.RED);
        this.txtNumeroSecreto.setForeground(Color.WHITE);
        this.add(this.btnOkk);
        this.btnOkk.setBounds(380, 400, 90, 40);
        this.btnOkk.setBackground(Color.RED);
        this.btnOkk.setForeground(Color.WHITE);
        this.txtNumeroServidor.setBorder(this.raise);
        this.txtNumeroServidor.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
        this.btnOk.setBorder(this.raise);
        this.btnOk.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
    }

    public void Creacion2() {
        this.remove(this.btnOkk);
        this.remove(this.txtNumeroSecreto);
        this.add(this.txtNumeroServidor);
        this.txtNumeroServidor.setBounds(170, 400, 200, 40);
        this.txtNumeroServidor.setBackground(Color.BLACK);
        this.txtNumeroServidor.setForeground(Color.WHITE);
        this.add(this.btnOk);
        this.btnOk.setBounds(380, 400, 90, 40);
        this.btnOk.setBackground(Color.BLACK);
        this.btnOk.setForeground(Color.WHITE);
    }

    public void BtnOk() {
        numeroServidor = this.txtNumeroServidor.getText();
        if (numeroServidor.length() != 4) {
            this.nuevaLinea("[ERROR] 4 d\u00edgitos", "RED");
        } else {
            this.enviarDatos(numeroServidor);
        }
    }
}

