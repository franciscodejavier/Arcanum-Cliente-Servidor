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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import es.studium.math.Consule;
import es.studium.math.Fondo;
import es.studium.math.Numerum;
import es.studium.math.Selector;

public class FiliusNuevoJuego
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
    static String numeroSecreto = Numerum.NumeroAleatorio();
    Border raise = BorderFactory.createEtchedBorder(0);

    public FiliusNuevoJuego() {
        System.out.println("N\u00famero secreto: " + numeroSecreto);
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
                FiliusNuevoJuego.this.labels[0] = new JLabel("[ok] Iniciando sistema");
                FiliusNuevoJuego.this.labels[0].setForeground(new Color(255, 255, 255, 255));
                gbc.gridx = 0;
                gbc.gridy = 0;
                FiliusNuevoJuego.this.panel.add((Component)FiliusNuevoJuego.this.labels[0], gbc);
                FiliusNuevoJuego.this.scrollPane.setViewportView(FiliusNuevoJuego.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusNuevoJuego.this.labels[1] = new JLabel("[ok] Iniciando cargador de arranque");
                FiliusNuevoJuego.this.labels[1].setForeground(new Color(255, 255, 255, 255));
                gbc.gridx = 0;
                gbc.gridy = 1;
                FiliusNuevoJuego.this.panel.add((Component)FiliusNuevoJuego.this.labels[1], gbc);
                FiliusNuevoJuego.this.scrollPane.setViewportView(FiliusNuevoJuego.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusNuevoJuego.this.labels[2] = new JLabel("[error] Disipadores condensados");
                FiliusNuevoJuego.this.labels[2].setForeground(Color.RED);
                gbc.gridx = 0;
                gbc.gridy = 2;
                FiliusNuevoJuego.this.panel.add((Component)FiliusNuevoJuego.this.labels[2], gbc);
                FiliusNuevoJuego.this.scrollPane.setViewportView(FiliusNuevoJuego.this.panel);
                try {
                    Thread.sleep(500);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusNuevoJuego.this.labels[3] = new JLabel("[ok] Recondensando procesos");
                FiliusNuevoJuego.this.labels[3].setForeground(Color.GREEN);
                gbc.gridx = 0;
                gbc.gridy = 3;
                FiliusNuevoJuego.this.panel.add((Component)FiliusNuevoJuego.this.labels[3], gbc);
                FiliusNuevoJuego.this.scrollPane.setViewportView(FiliusNuevoJuego.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusNuevoJuego.this.labels[4] = new JLabel("[ok] Preparando mec\u00e1nica cu\u00e1ntica");
                FiliusNuevoJuego.this.labels[4].setForeground(new Color(255, 255, 255, 255));
                gbc.gridx = 0;
                gbc.gridy = 4;
                FiliusNuevoJuego.this.panel.add((Component)FiliusNuevoJuego.this.labels[4], gbc);
                FiliusNuevoJuego.this.scrollPane.setViewportView(FiliusNuevoJuego.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                FiliusNuevoJuego.this.labels[5] = new JLabel("[ok] Sistema iniciado");
                FiliusNuevoJuego.this.labels[5].setForeground(new Color(255, 255, 255, 255));
                gbc.gridx = 0;
                gbc.gridy = 5;
                FiliusNuevoJuego.this.panel.add((Component)FiliusNuevoJuego.this.labels[5], gbc);
                FiliusNuevoJuego.this.scrollPane.setViewportView(FiliusNuevoJuego.this.panel);
                try {
                    Thread.sleep(200);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                int i22 = 0;
                while (i22 < 6) {
                    FiliusNuevoJuego.this.panel.remove(FiliusNuevoJuego.this.labels[i22]);
                    ++i22;
                }
                FiliusNuevoJuego.this.labels[0] = new JLabel("Bienvenido al sistema secreto ARCANUM");
                FiliusNuevoJuego.this.labels[0].setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 0;
                FiliusNuevoJuego.this.panel.add((Component)FiliusNuevoJuego.this.labels[0], gbc);
                FiliusNuevoJuego.this.scrollPane.setViewportView(FiliusNuevoJuego.this.panel);
                try {
                    Thread.sleep(1000);
                }
                catch (Exception i22i) {
                    // empty catch block
                }
                FiliusNuevoJuego.this.labels[1] = new JLabel("[Error] en el inicio de sesi\u00f3n");
                FiliusNuevoJuego.this.labels[1].setForeground(Color.RED);
                gbc.gridx = 0;
                gbc.gridy = 1;
                FiliusNuevoJuego.this.panel.add((Component)FiliusNuevoJuego.this.labels[1], gbc);
                FiliusNuevoJuego.this.scrollPane.setViewportView(FiliusNuevoJuego.this.panel);
                try {
                    Thread.sleep(1000);
                }
                catch (Exception i22i) {
                    // empty catch block
                }
                FiliusNuevoJuego.this.labels[2] = new JLabel("Que comience el juego:");
                FiliusNuevoJuego.this.labels[2].setForeground(Color.WHITE);
                gbc.gridx = 0;
                gbc.gridy = 2;
                FiliusNuevoJuego.this.panel.add((Component)FiliusNuevoJuego.this.labels[2], gbc);
                FiliusNuevoJuego.this.scrollPane.setViewportView(FiliusNuevoJuego.this.panel);
                try {
                    Thread.sleep(1000);
                }
                catch (Exception i22i) {
                    // empty catch block
                }
                FiliusNuevoJuego.this.add(FiliusNuevoJuego.this.txtNumeroJugador);
                FiliusNuevoJuego.this.txtNumeroJugador.setBounds(170, 400, 200, 40);
                FiliusNuevoJuego.this.txtNumeroJugador.setBackground(Color.BLACK);
                FiliusNuevoJuego.this.txtNumeroJugador.setForeground(Color.WHITE);
                FiliusNuevoJuego.this.add(FiliusNuevoJuego.this.btnOk);
                FiliusNuevoJuego.this.btnOk.setBounds(380, 400, 90, 40);
                FiliusNuevoJuego.this.btnOk.setBackground(Color.BLACK);
                FiliusNuevoJuego.this.btnOk.setForeground(Color.WHITE);
                FiliusNuevoJuego.this.txtNumeroJugador.setBorder(FiliusNuevoJuego.this.raise);
                FiliusNuevoJuego.this.txtNumeroJugador.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
                FiliusNuevoJuego.this.btnOk.setBorder(FiliusNuevoJuego.this.raise);
                FiliusNuevoJuego.this.btnOk.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
            }
        });
        hilo.start();
        this.setSize(639, 480);
        this.addWindowListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.txtNumeroJugador.addKeyListener(this);
        this.txtNumeroJugador.requestFocus();
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
                String respuesta = Consule.Consulta(numeroSecreto, this.numeroJugador);
                this.labels[this.contadorLineas] = new JLabel("arcanum # ./numerum " + this.numeroJugador + " >> " + respuesta);
                this.labels[this.contadorLineas].setForeground(new Color(255, 255, 255, 255));
                gbc.gridx = 0;
                gbc.gridy = this.contadorLineas;
                this.panel.add((Component)this.labels[this.contadorLineas], gbc);
                this.scrollPane.setViewportView(this.panel);
                ++this.contador;
                ++this.contadorLineas;
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
                    this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar().getMaximum());
                    this.txtNumeroJugador.requestFocus();
                    this.txtNumeroJugador.addFocusListener(new Selector());
                    this.txtNumeroJugador.setBorder(this.raise);
                    this.txtNumeroJugador.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
                    this.btnOk.setBorder(this.raise);
                    this.btnOk.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
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