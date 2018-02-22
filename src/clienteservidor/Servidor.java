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

public class Servidor extends JFrame implements WindowListener, MouseListener, KeyListener {
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

    public Servidor() {
      
    	hiloBtnOkk = new Thread(new Runnable(){
            @Override
            public void run() {
                numeroSecretoServidor = txtNumeroSecreto.getText();
                if (numeroSecretoServidor.length() != 4) {
                    nuevaLinea("[ERROR] 4 d\u00edgitos", "RED");
                } else {
                    Creacion2();
                    nuevaLinea("[!] Cifrando n\u00famero secreto", "WHITE");
                    nuevaLinea("[!] N\u00famero secreto cifrado", "GREEN");
                }
            }
        });
        
        setLayout(null);
        setTitle("Arcanum Servidor");
        setLocationRelativeTo(null);
        setSize(639, 480);
        setVisible(true);
        setResizable(false);
        add(fondo);
        fondo.setLayout(null);
        fondo.setBackground("img/monitor.jpg");
        fondo.setVisible(true);
        fondo.setOpaque(false);
        fondo.setBounds(0, 0, 639, 480);
        getContentPane().add(panel);
        fondo.add(scrollPane);
        scrollPane.setBounds(156, 57, 337, 290);
        panel.setBackground(new Color(1, 1, 1, 250));
        panel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(1, 1, 1, 300);
        gbc.anchor = 18;
      
        Thread hiloInicio = new Thread(new Runnable(){
            @Override
            public void run() {
                Encendido();
                Creacion();
                btnOkk.addActionListener(event -> {
                    hiloBtnOkk.start();
                }
                );
                btnOk.addActionListener(event -> {
                    BtnOk();
                }
                );
                try {
                    socketServicio = new ServerSocket(5555);
                    nuevaLinea("Servicio en escucha en puerto: 5555", "BLUE");
                    miServicio = socketServicio.accept();
                    Thread hilo = new Thread(new Runnable(){
                        @Override
                        public void run() {
                            while (opcion) {
                                recibirDatos();
                            }
                        }
                    });
                    hilo.start();
                }
                catch (Exception ex) {
                    nuevaLinea("Error al abrir los sockets", "RED");
                }
            }
        });
        hiloInicio.start();
        
        addWindowListener(this);
        addMouseListener(this);
        addKeyListener(this);
        txtNumeroServidor.addKeyListener(this);
    }

    public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
        new clienteservidor.Servidor();
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
            miServicio.close();
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
            btnOk.doClick();
        }
    }
    public void keyReleased(KeyEvent ke) {}
    public void keyTyped(KeyEvent ke) {}
    
    public void nuevaLinea(String texto, String color) {
        labels[contadorLineas] = new JLabel(texto);
        if (color.equals("RED")) {
            labels[contadorLineas].setForeground(Color.RED);
        } else if (color.equals("WHITE")) {
            labels[contadorLineas].setForeground(Color.WHITE);
        } else if (color.equals("GREEN")) {
            labels[contadorLineas].setForeground(Color.GREEN);
        } else if (color.equals("GRAY")) {
            labels[contadorLineas].setForeground(Color.GRAY);
        } else if (color.equals("BLUE")) {
            labels[contadorLineas].setForeground(Color.BLUE);
        } else {
            labels[contadorLineas].setForeground(Color.WHITE);
        }
        gbc.gridx = 0;
        gbc.gridy = contadorLineas;
        panel.add((Component)labels[contadorLineas], gbc);
        scrollPane.setViewportView(panel);
        ++contadorLineas;
        try {
            Thread.sleep(500);
        }
        catch (Exception exception) {
            // empty catch block
        }
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }

    public void enviarDatos(String datos) {
        try {
            outputStream = miServicio.getOutputStream();
            salidaDatos = new DataOutputStream(outputStream);
            salidaDatos.writeUTF(datos);
            salidaDatos.flush();
        }
        catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recibirDatos() {
        try {
            inputStream = miServicio.getInputStream();
            entradaDatos = new DataInputStream(inputStream);
            numeroCliente = entradaDatos.readUTF();
            boolean esNumero = true;
            try {
                Integer.parseInt(numeroCliente);
            }
            catch (Exception e) {
                esNumero = false;
            }
            if (esNumero) {
                respuesta = Consule.Consulta(numeroSecretoServidor, numeroCliente);
                nuevaLinea("<< " + numeroCliente + " --> " + respuesta, "GRAY");
                enviarDatos(respuesta);
            } else {
                ++contador;
                nuevaLinea(">>" + numeroServidor + " --> " + numeroCliente, "WHITE");
                if (numeroCliente.equals("exploited")) {
                    Perdedor();
                    nuevaLinea("[ERROR] Error en sistema", "RED");
                    nuevaLinea("[ERROR] System HACKED", "RED");
                    nuevaLinea("[hacked] Has sido hackeado por cliente", "RED");
                    nuevaLinea("[!] Perdiendo control del servidor...", "WHITE");
                    nuevaLinea("cliente@system# Lo siento", "RED");
                    cerrarTodo();
                } else if (numeroCliente.equals("mmmm")) {
                    Ganador();
                    enviarDatos("exploited");
                    nuevaLinea("[hacked] Has hackeado al cliente", "GREEN");
                    nuevaLinea("[!] Intentos realizados " + contador, "WHITE");
                    nuevaLinea("cliente@system# Enhorabuena", "GREEN");
                    cerrarTodo();
                }
            }
        }
        catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Ganador() {
        txtNumeroServidor.setEditable(false);
        txtNumeroServidor.setBorder(raise);
        txtNumeroServidor.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
        btnOk.setBorder(raise);
        btnOk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
        btnOk.setText("Salir");
        btnOk.addActionListener(newEvent -> {
            setVisible(false);
        }
        );
    }

    public void Perdedor() {
        txtNumeroServidor.setEditable(false);
        txtNumeroServidor.setBorder(raise);
        txtNumeroServidor.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));
        btnOk.setBorder(raise);
        btnOk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));
        btnOk.setText("Salir");
        btnOk.addActionListener(newEvent -> {
            setVisible(false);
        }
        );
    }

    public void cerrarTodo() {
        try {
            opcion = false;
            salidaDatos.close();
            entradaDatos.close();
            socketServicio.close();
            miServicio.close();
        }
        catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Encendido() {
        nuevaLinea("[!] Iniciando sistema...", "WHITE");
        nuevaLinea("[!] Sistema iniciado", "WHITE");
        nuevaLinea("[!] Aplicando reglas de firewall", "WHITE");
        nuevaLinea("[!] Hardening sistema...", "WHITE");
        nuevaLinea("[!] Sistema iniciado correctamente", "WHITE");
        nuevaLinea("[process] Introduzca el secreto", "BLUE");
    }

    public void Creacion() {
        add(txtNumeroSecreto);
        txtNumeroSecreto.setBounds(170, 400, 200, 40);
        txtNumeroSecreto.setBackground(Color.RED);
        txtNumeroSecreto.setForeground(Color.WHITE);
        add(btnOkk);
        btnOkk.setBounds(380, 400, 90, 40);
        btnOkk.setBackground(Color.RED);
        btnOkk.setForeground(Color.WHITE);
        txtNumeroServidor.setBorder(raise);
        txtNumeroServidor.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
        btnOk.setBorder(raise);
        btnOk.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
    }

    public void Creacion2() {
        remove(btnOkk);
        remove(txtNumeroSecreto);
        add(txtNumeroServidor);
        txtNumeroServidor.setBounds(170, 400, 200, 40);
        txtNumeroServidor.setBackground(Color.BLACK);
        txtNumeroServidor.setForeground(Color.WHITE);
        add(btnOk);
        btnOk.setBounds(380, 400, 90, 40);
        btnOk.setBackground(Color.BLACK);
        btnOk.setForeground(Color.WHITE);
    }

    public void BtnOk() {
        numeroServidor = txtNumeroServidor.getText();
        if (numeroServidor.length() != 4) {
            nuevaLinea("[ERROR] 4 d\u00edgitos", "RED");
        } else {
            enviarDatos(numeroServidor);
        }
    }
}

