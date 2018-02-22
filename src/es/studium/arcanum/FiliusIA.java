package es.studium.arcanum;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import es.studium.ia.PatricIA;
import es.studium.math.Consule;
import es.studium.math.Fondo;
import es.studium.math.Numerum;

public class FiliusIA
extends JFrame
implements WindowListener,
MouseListener,
KeyListener {
    private static final long serialVersionUID = 1;
    JTextField txtNumeroJugador = new JTextField();
    JButton btnOk = new JButton("Ok");
    Fondo fondo = new Fondo();
    JScrollPane scrollPane = new JScrollPane(22, 32);
    JPanel panel = new JPanel();
    JLabel[] labels = new JLabel[1000];
    int contadorLineas = 3;
    int contador = 0;
    boolean ganador = false;
    String numeroJugador = "";
    Border raise = BorderFactory.createEtchedBorder(0);
    static String numeroSecretoIA = Numerum.NumeroAleatorio();
    String numeroIA = PatricIA.IA();
    JPasswordField txtNumeroSecreto = new JPasswordField();
    JButton btnOkk = new JButton("Encrypt");
    static String numeroSecretoJugador = "";

    public FiliusIA() {
        System.out.println("N\u00famero secreto IA: " + numeroSecretoIA);
        this.setLayout(null);
        this.setTitle("Arcanum Ludum");
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
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 300);
        gbc.anchor = 18;
        Thread hilo = new Thread(new Runnable(){

            @Override
            public void run() {
                FiliusIA.this.labels[0] = new JLabel("[ok] Iniciando sistema");
                FiliusIA.this.labels[0].setForeground(new Color(255, 255, 255, 255));
                gbc.gridx = 0;
                gbc.gridy = 0;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[0], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusIA.this.labels[1] = new JLabel("[ok] Iniciando cargador de arranque");
                FiliusIA.this.labels[1].setForeground(new Color(255, 255, 255, 255));
                gbc.gridx = 0;
                gbc.gridy = 1;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[1], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusIA.this.labels[2] = new JLabel("[error] IA Intentando acceder");
                FiliusIA.this.labels[2].setForeground(Color.RED);
                gbc.gridx = 0;
                gbc.gridy = 2;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[2], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                try {
                    Thread.sleep(500);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusIA.this.labels[3] = new JLabel("[ok] Contratacando");
                FiliusIA.this.labels[3].setForeground(Color.GREEN);
                gbc.gridx = 0;
                gbc.gridy = 3;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[3], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusIA.this.labels[4] = new JLabel("[ok] Preparando exploits");
                FiliusIA.this.labels[4].setForeground(new Color(255, 255, 255, 255));
                gbc.gridx = 0;
                gbc.gridy = 4;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[4], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusIA.this.labels[5] = new JLabel("[ok] Sistema iniciado");
                FiliusIA.this.labels[5].setForeground(new Color(255, 255, 255, 255));
                gbc.gridx = 0;
                gbc.gridy = 5;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[5], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                int i22 = 0;
                while (i22 < 6) {
                    FiliusIA.this.panel.remove(FiliusIA.this.labels[i22]);
                    ++i22;
                }
                FiliusIA.this.labels[0] = new JLabel("Bienvenido al sistema secreto ARCANUM");
                FiliusIA.this.labels[0].setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 0;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[0], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                try {
                    Thread.sleep(1000);
                }
                catch (Exception i22i) {
                    // empty catch block
                }
                FiliusIA.this.labels[1] = new JLabel("[Error] Recibiendo ataques IA");
                FiliusIA.this.labels[1].setForeground(Color.RED);
                gbc.gridx = 0;
                gbc.gridy = 1;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[1], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                try {
                    Thread.sleep(1000);
                }
                catch (Exception i22i) {
                    // empty catch block
                }
                FiliusIA.this.labels[2] = new JLabel("Que comience el juego, \u00a1contrataca!:");
                FiliusIA.this.labels[2].setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 2;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[2], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                try {
                    Thread.sleep(1000);
                }
                catch (Exception i22i) {
                    // empty catch block
                }
                FiliusIA.this.labels[FiliusIA.this.contadorLineas] = new JLabel("[!] Introduzca su n\u00famero secreto, defienda su m\u00e1quina");
                FiliusIA.this.labels[FiliusIA.this.contadorLineas].setForeground(Color.BLUE);
                gbc.gridx = 0;
                gbc.gridy = FiliusIA.this.contadorLineas;
                FiliusIA.this.panel.add((Component)FiliusIA.this.labels[FiliusIA.this.contadorLineas], gbc);
                FiliusIA.this.scrollPane.setViewportView(FiliusIA.this.panel);
                ++FiliusIA.this.contadorLineas;
                FiliusIA.this.add(FiliusIA.this.txtNumeroSecreto);
                FiliusIA.this.txtNumeroSecreto.setBounds(170, 400, 200, 40);
                FiliusIA.this.txtNumeroSecreto.setBackground(Color.RED);
                FiliusIA.this.txtNumeroSecreto.setForeground(Color.WHITE);
                FiliusIA.this.add(FiliusIA.this.btnOkk);
                FiliusIA.this.btnOkk.setBounds(380, 400, 90, 40);
                FiliusIA.this.btnOkk.setBackground(Color.RED);
                FiliusIA.this.btnOkk.setForeground(Color.WHITE);
                FiliusIA.this.txtNumeroJugador.setBorder(FiliusIA.this.raise);
                FiliusIA.this.txtNumeroJugador.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
                FiliusIA.this.btnOk.setBorder(FiliusIA.this.raise);
                FiliusIA.this.btnOk.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
            }
        });
        hilo.start();
        this.btnOkk.addActionListener(event -> {
            numeroSecretoJugador = this.txtNumeroSecreto.getText();
            System.out.println("N\u00famero secreto Jugador " + numeroSecretoJugador);
            if (numeroSecretoJugador.length() != 4) {
                this.labels[this.contadorLineas] = new JLabel("[[!] Error 4 digits");
                this.labels[this.contadorLineas].setForeground(Color.RED);
                gbc.gridx = 0;
                gbc.gridy = this.contadorLineas;
                this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                this.scrollPane.setViewportView(this.panel);
                ++this.contadorLineas;
            } else {
                this.remove(this.btnOkk);
                this.remove(this.txtNumeroSecreto);
                this.add(this.txtNumeroJugador);
                this.txtNumeroJugador.setBounds(170, 400, 200, 40);
                this.txtNumeroJugador.setBackground(Color.BLACK);
                this.txtNumeroJugador.setForeground(Color.WHITE);
                this.add(this.btnOk);
                this.btnOk.setBounds(380, 400, 90, 40);
                this.btnOk.setBackground(Color.BLACK);
                this.btnOk.setForeground(Color.WHITE);
                this.labels[this.contadorLineas] = new JLabel("[!] Cifrando n\u00famero secreto");
                this.labels[this.contadorLineas].setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = this.contadorLineas;
                this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                this.scrollPane.setViewportView(this.panel);
                ++this.contadorLineas;
                this.labels[this.contadorLineas] = new JLabel("[!] N\u00famero secreto cifrado");
                this.labels[this.contadorLineas].setForeground(Color.GREEN);
                gbc.gridx = 0;
                gbc.gridy = this.contadorLineas;
                this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                this.scrollPane.setViewportView(this.panel);
                ++this.contadorLineas;
            }
        }
        );
        this.setSize(639, 480);
        this.addWindowListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.txtNumeroJugador.addKeyListener(this);
        this.btnOk.addActionListener(event -> {
            this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar().getMaximum());
            this.numeroJugador = this.txtNumeroJugador.getText();
            if (this.numeroJugador.length() != 4) {
                this.labels[this.contadorLineas] = new JLabel("[root@arcanum ~]# [!] Error 4 digits");
                this.labels[this.contadorLineas].setForeground(Color.RED);
                gbc.gridx = 0;
                gbc.gridy = this.contadorLineas;
                this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                this.scrollPane.setViewportView(this.panel);
                ++this.contadorLineas;
            } else {
                String respuesta = Consule.Consulta(numeroSecretoIA, this.numeroJugador);
                this.labels[this.contadorLineas] = new JLabel("arcanum # ./numerum " + this.numeroJugador + " >> " + respuesta);
                this.labels[this.contadorLineas].setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = this.contadorLineas;
                this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                this.scrollPane.setViewportView(this.panel);
                ++this.contador;
                ++this.contadorLineas;
                String respuestaia = Consule.Consulta(numeroSecretoJugador, this.numeroIA);
                this.labels[this.contadorLineas] = new JLabel("arcanum # ./ia " + this.numeroIA + " << " + respuestaia);
                this.labels[this.contadorLineas].setForeground(Color.GRAY);
                gbc.gridx = 0;
                gbc.gridy = this.contadorLineas;
                this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                this.scrollPane.setViewportView(this.panel);
                ++this.contadorLineas;
                this.numeroIA = PatricIA.IA(this.numeroIA, respuestaia);
                if (respuesta.equals("mmmm")) {
                    this.ganador = true;
                    this.labels[this.contadorLineas] = new JLabel("[hacked] Tienes acceso root");
                    this.labels[this.contadorLineas].setForeground(Color.GREEN);
                    gbc.gridx = 0;
                    gbc.gridy = this.contadorLineas;
                    this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                    this.scrollPane.setViewportView(this.panel);
                    ++this.contadorLineas;
                    this.labels[this.contadorLineas] = new JLabel("Intentos realizados: " + this.contador);
                    this.labels[this.contadorLineas].setForeground(Color.WHITE);
                    gbc.gridx = 0;
                    gbc.gridy = this.contadorLineas;
                    this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                    this.scrollPane.setViewportView(this.panel);
                    ++this.contadorLineas;
                    this.labels[this.contadorLineas] = new JLabel("root # _");
                    this.labels[this.contadorLineas].setForeground(Color.WHITE);
                    gbc.gridx = 0;
                    gbc.gridy = this.contadorLineas;
                    this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                    this.scrollPane.setViewportView(this.panel);
                    ++this.contadorLineas;
                    this.txtNumeroJugador.setBorder(this.raise);
                    this.txtNumeroJugador.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
                    this.btnOk.setBorder(this.raise);
                    this.btnOk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
                    this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar().getMaximum());
                    this.btnOk.setText("Salir");
                    this.btnOk.addActionListener(newEvent -> {
                       // thread.interrupt();
                        this.setVisible(false);
                        new es.studium.arcanum.PaterClass();
                    }
                    );
                } else if (respuestaia.equals("mmmm")) {
                    this.ganador = true;
                    this.labels[this.contadorLineas] = new JLabel("[error] Intrusi\u00f3n en el sistema");
                    this.labels[this.contadorLineas].setForeground(Color.RED);
                    gbc.gridx = 0;
                    gbc.gridy = this.contadorLineas;
                    this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                    this.scrollPane.setViewportView(this.panel);
                    ++this.contadorLineas;
                    this.labels[this.contadorLineas] = new JLabel("Intentos realizados: " + this.contador);
                    this.labels[this.contadorLineas].setForeground(Color.WHITE);
                    gbc.gridx = 0;
                    gbc.gridy = this.contadorLineas;
                    this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                    this.scrollPane.setViewportView(this.panel);
                    ++this.contadorLineas;
                    this.labels[this.contadorLineas] = new JLabel("IA te ha hackeado");
                    this.labels[this.contadorLineas].setForeground(Color.RED);
                    gbc.gridx = 0;
                    gbc.gridy = this.contadorLineas;
                    this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                    this.scrollPane.setViewportView(this.panel);
                    ++this.contadorLineas;
                    this.txtNumeroJugador.setBorder(this.raise);
                    this.txtNumeroJugador.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));
                    this.btnOk.setBorder(this.raise);
                    this.btnOk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));
                    this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar().getMaximum());
                    this.btnOk.setText("Salir");
                    this.btnOk.addActionListener(newEvent -> {
                        //thread.interrupt();
                        this.setVisible(false);
                        new es.studium.arcanum.PaterClass();
                    }
                    );
                }
            }
        }
        );
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        this.setVisible(false);
        new es.studium.arcanum.PaterClass();
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == 10) {
            this.btnOk.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}