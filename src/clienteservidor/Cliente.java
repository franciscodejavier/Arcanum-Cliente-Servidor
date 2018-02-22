package clienteservidor;

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

import matematicas.Consule;
import matematicas.Fondo;

public class Cliente extends JFrame implements WindowListener, MouseListener, KeyListener {
    private static final long serialVersionUID = 1;
    
    JTextField txtNumeroCliente = new JTextField();
    JButton btnOk = new JButton("Ok");
    Fondo fondo = new Fondo();
    JScrollPane scrollPane = new JScrollPane(22, 32);
    JPanel panel = new JPanel();
    JLabel[] labels = new JLabel[1000];
    int contadorLineas = 0;
    int contador = 0;
    Border raise = BorderFactory.createEtchedBorder(0);
    static String respuesta = "espera";
    GridBagConstraints gbc = new GridBagConstraints();
    JPasswordField txtNumeroSecreto = new JPasswordField();
    JButton btnOkk = new JButton("Encrypt");
    JTextField txtIP = new JTextField("127.0.0.1");
    JButton btnIP = new JButton("TCP/IP");
    Socket miServicio;
    ServerSocket socketServicio;
    private Socket socketCliente;
    OutputStream outputStream;
    InputStream inputStream;
    DataOutputStream SalidaDatos;
    DataInputStream entradaDatos;
    static String numeroSecretoCliente = "";
    static String numeroServidor = "";
    static String numeroCliente = "";
    static boolean existsNumeroSecretoServidor = false;
    static boolean opcion = true;
    static String ip = "";
    Thread hiloBtnOkk;

    public Cliente() {
    	
        this.hiloBtnOkk = new Thread(new Runnable(){
            @Override
            public void run() {
                Cliente.numeroSecretoCliente = Cliente.this.txtNumeroSecreto.getText();
                if (Cliente.numeroSecretoCliente.length() != 4) {
                    Cliente.this.nuevaLinea("[ERROR] 4 d\u00edgitos", "RED");
                } else {
                    Cliente.this.Creacion2();
                    Cliente.this.nuevaLinea("[!] Cifrando n\u00famero secreto", "WHITE");
                    Cliente.this.nuevaLinea("[!] N\u00famero secreto cifrado", "GREEN");
                }
            }
        });
        
        this.setLayout(null);
        this.setTitle("Arcanum Cliente");
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
                Cliente.this.Encendido();
                Cliente.this.IP();
                Cliente.this.btnIP.addActionListener(event -> {
                    Cliente.this.BtnIP();
                }
                );
                Cliente.this.btnOkk.addActionListener(event -> {
                    Cliente.this.hiloBtnOkk.start();
                }
                );
                Cliente.this.btnOk.addActionListener(event -> {
                    Cliente.this.BtnOK();
                }
                );
            }
        });
        hiloInicio.start();
        
        this.addWindowListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.txtNumeroCliente.addKeyListener(this);
    }

    public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
        new clienteservidor.Cliente();
    }

    public void mouseClicked(MouseEvent me) {}
    public void windowActivated(WindowEvent arg0) {}
    public void windowClosed(WindowEvent arg0) {}
    public void windowClosing(WindowEvent arg0) {System.exit(0);}
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
            this.outputStream = this.socketCliente.getOutputStream();
            this.SalidaDatos = new DataOutputStream(this.outputStream);
            this.SalidaDatos.writeUTF(datos);
            this.SalidaDatos.flush();
        }
        catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escucharDatos(Socket socket) {
        try {
            this.inputStream = socket.getInputStream();
            this.entradaDatos = new DataInputStream(this.inputStream);
            numeroServidor = this.entradaDatos.readUTF();
            boolean esNumero = true;
            try {
                Integer.parseInt(numeroServidor);
            }
            catch (Exception e) {
                esNumero = false;
            }
            if (esNumero) {
                respuesta = Consule.Consulta(numeroSecretoCliente, numeroServidor);
                this.nuevaLinea("<< " + numeroServidor + " --> " + respuesta, "GRAY");
                this.enviarDatos(respuesta);
            } else {
                ++this.contador;
                this.nuevaLinea(">>" + numeroCliente + " --> " + numeroServidor, "WHITE");
                if (numeroServidor.equals("exploited")) {
                    this.Perdedor();
                    this.nuevaLinea("[ERROR] Error en sistema", "RED");
                    this.nuevaLinea("[ERROR] System HACKED", "RED");
                    this.nuevaLinea("[hacked] Has sido hackeado por server", "RED");
                    this.nuevaLinea("[!] Perdiendo control de equipo...", "WHITE");
                    this.nuevaLinea("server@system# Lo siento", "RED");
                    this.cerrarTodo();
                } else if (numeroServidor.equals("mmmm")) {
                    this.Ganador();
                    this.enviarDatos("exploited");
                    this.nuevaLinea("[hacked] Has hackeado al servidor", "GREEN");
                    this.nuevaLinea("[!] Intentos realizados " + this.contador, "WHITE");
                    this.nuevaLinea("server@system# Enhorabuena", "GREEN");
                    this.cerrarTodo();
                }
            }
        }
        catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Ganador() {
        this.txtNumeroCliente.setEditable(false);
        this.txtNumeroCliente.setBorder(this.raise);
        this.txtNumeroCliente.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
        this.btnOk.setBorder(this.raise);
        this.btnOk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
        this.btnOk.setText("Salir");
        this.btnOk.addActionListener(newEvent -> {
            this.setVisible(false);
        }
        );
    }

    public void Perdedor() {
        this.txtNumeroCliente.setEditable(false);
        this.txtNumeroCliente.setBorder(this.raise);
        this.txtNumeroCliente.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));
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
            opcion = false;
            this.SalidaDatos.close();
            this.entradaDatos.close();
            this.socketCliente.close();
        }
        catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Encendido() {
        this.nuevaLinea("[!] Iniciando sistema...", "WHITE");
        this.nuevaLinea("[!] Sistema iniciado", "WHITE");
        this.nuevaLinea("[!] Aplicando reglas de firewall", "WHITE");
        this.nuevaLinea("[!] Hardening sistema...", "WHITE");
        this.nuevaLinea("[!] Sistema iniciado correctamente", "WHITE");
        this.nuevaLinea("[process] Requerida IP de v\u00edctima", "GREEN");
    }

    public void IP() {
        this.add(this.txtIP);
        this.txtIP.setBounds(170, 400, 200, 40);
        this.txtIP.setBackground(Color.GREEN);
        this.txtIP.setForeground(Color.WHITE);
        this.add(this.btnIP);
        this.btnIP.setBounds(380, 400, 90, 40);
        this.btnIP.setBackground(Color.GREEN);
        this.btnIP.setForeground(Color.WHITE);
        this.txtIP.setBorder(this.raise);
        this.txtIP.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
        this.btnIP.setBorder(this.raise);
        this.btnIP.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
    }

    public void Creacion() {
        this.remove(this.btnIP);
        this.remove(this.txtIP);
        this.add(this.txtNumeroSecreto);
        this.txtNumeroSecreto.setBounds(170, 400, 200, 40);
        this.txtNumeroSecreto.setBackground(Color.RED);
        this.txtNumeroSecreto.setForeground(Color.WHITE);
        this.add(this.btnOkk);
        this.btnOkk.setBounds(380, 400, 90, 40);
        this.btnOkk.setBackground(Color.RED);
        this.btnOkk.setForeground(Color.WHITE);
        this.txtNumeroCliente.setBorder(this.raise);
        this.txtNumeroCliente.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
        this.btnOk.setBorder(this.raise);
        this.btnOk.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
    }

    public void Creacion2() {
        this.remove(this.btnOkk);
        this.remove(this.txtNumeroSecreto);
        this.add(this.txtNumeroCliente);
        this.txtNumeroCliente.setBounds(170, 400, 200, 40);
        this.txtNumeroCliente.setBackground(Color.BLACK);
        this.txtNumeroCliente.setForeground(Color.WHITE);
        this.add(this.btnOk);
        this.btnOk.setBounds(380, 400, 90, 40);
        this.btnOk.setBackground(Color.BLACK);
        this.btnOk.setForeground(Color.WHITE);
    }

    public void BtnIP() {
        ip = this.txtIP.getText();
        try {
            this.socketCliente = new Socket(ip, 5555);
            Thread hilo1 = new Thread(new Runnable(){

                @Override
                public void run() {
                    while (Cliente.opcion) {
                        Cliente.this.escucharDatos(Cliente.this.socketCliente);
                    }
                }
            });
            hilo1.start();
        }
        catch (Exception ex) {
            this.nuevaLinea(ex.getMessage(), "RED");
        }
        this.Creacion();
        this.nuevaLinea("[process] Target IP Preparado", "WHITE");
        this.nuevaLinea("[process] Introduzca secreto", "BLUE");
    }

    public void BtnOK() {
        numeroCliente = this.txtNumeroCliente.getText();
        if (numeroCliente.length() != 4) {
            this.nuevaLinea("[ERROR] 4 d\u00edgitos", "RED");
        } else {
            this.enviarDatos(numeroCliente);
        }
    }

}

