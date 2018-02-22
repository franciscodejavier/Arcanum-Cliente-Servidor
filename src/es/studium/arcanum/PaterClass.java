package es.studium.arcanum;

import es.studium.arcanum.FiliusIA;
//import es.studium.arcanum.FiliusIAvsIA;
//import es.studium.arcanum.FiliusMultijugador;
//import es.studium.arcanum.FiliusNuevoJuego;
//import es.studium.arcanum.mas.Autor;
//import es.studium.arcanum.mas.ComoJugar;
//import es.studium.arcanum.mas.Licencia;
import es.studium.clientserver.ArcanumCliente;
import es.studium.clientserver.ArcanumServidor;
import es.studium.math.Fondo;
import java.awt.Component;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PaterClass
extends JFrame
implements WindowListener,
ActionListener {
    private static final long serialVersionUID = 1;
    Fondo fondo = new Fondo();

    public PaterClass() {
        this.MenuBar();
        this.setLayout(null);
        this.setTitle("Arcanum Servidor");
        this.setLocationRelativeTo(null);
        this.setSize(639, 480);
        this.setVisible(true);
        this.setResizable(false);
        this.add(this.fondo);
        this.fondo.setLayout(null);
        this.fondo.setBackground("img/monitori.jpg");
        this.fondo.setVisible(true);
        this.fondo.setOpaque(false);
        this.fondo.setBounds(0, 0, 639, 480);
        this.setVisible(true);
        this.addWindowListener(this);
    }

    private void MenuBar() {
        JMenuBar menubar = new JMenuBar();
        JMenu menuJugar = new JMenu("Jugar");
        JMenuItem mnNuevoJuego = new JMenuItem("Juego r\u00e1pido", new ImageIcon(new ImageIcon("img/jugador.png").getImage().getScaledInstance(30, 30, 4)));
        mnNuevoJuego.setToolTipText("Juega s\u00f3lo, averigua el secreto");
        JMenuItem mnMultijugador = new JMenuItem("Multijugador", new ImageIcon(new ImageIcon("img/multijugador.png").getImage().getScaledInstance(30, 30, 4)));
        mnMultijugador.setToolTipText("Juega contra un amigo en el apasionante modo de pantalla dividida");
        JMenuItem mnIA = new JMenuItem("Modo IA", new ImageIcon(new ImageIcon("img/ia.png").getImage().getScaledInstance(30, 30, 4)));
        mnIA.setToolTipText("Una Inteligencia Artificial quiere hackearte, \u00a1imp\u00eddelo!");
        JMenuItem mnIAvsIA = new JMenuItem("IA vs IA", new ImageIcon(new ImageIcon("img/iavsia.png").getImage().getScaledInstance(30, 30, 4)));
        mnIAvsIA.setToolTipText("Dos IAs compitiendo entre s\u00ed");
        JMenuItem mnSalir = new JMenuItem("Salir", new ImageIcon(new ImageIcon("img/salir.png").getImage().getScaledInstance(30, 30, 4)));
        mnSalir.setToolTipText("Salir de nuestro software.");
        JMenu menuOnline = new JMenu("Modo Online");
        JMenuItem mnServidor = new JMenuItem("Servidor", new ImageIcon(new ImageIcon("img/servidor.png").getImage().getScaledInstance(30, 30, 4)));
        mnSalir.setToolTipText("Salir de nuestro software.");
        JMenuItem mnCliente = new JMenuItem("Cliente", new ImageIcon(new ImageIcon("img/cliente.png").getImage().getScaledInstance(30, 30, 4)));
        mnSalir.setToolTipText("Salir de nuestro software.");
        JMenu menuSobre = new JMenu("Sobre");
        JMenuItem mnLicencia = new JMenuItem("Sobre licencia", new ImageIcon(new ImageIcon("img/licencia.png").getImage().getScaledInstance(30, 30, 4)));
        mnSalir.setToolTipText("Salir de nuestro software.");
        JMenuItem mnAutor = new JMenuItem("Sobre autor", new ImageIcon(new ImageIcon("img/autor.png").getImage().getScaledInstance(30, 30, 4)));
        mnSalir.setToolTipText("Salir de nuestro software.");
        JMenuItem mnComoJugar = new JMenuItem("C\u00f3mo jugar", new ImageIcon(new ImageIcon("img/comojugar.png").getImage().getScaledInstance(30, 30, 4)));
        mnSalir.setToolTipText("\u00bfNo entiendes el juego? Te lo explicamos.");
        menubar.add(menuJugar);
        menuJugar.add(mnNuevoJuego);
        menuJugar.add(mnMultijugador);
        menuJugar.add(mnIA);
        menuJugar.add(mnIAvsIA);
        menuJugar.add(mnSalir);
        menubar.add(menuOnline);
        menuOnline.add(mnServidor);
        menuOnline.add(mnCliente);
        menubar.add(menuSobre);
        menuSobre.add(mnLicencia);
        menuSobre.add(mnAutor);
        menuSobre.add(mnComoJugar);
        this.setJMenuBar(menubar);
        mnNuevoJuego.addActionListener(event -> {
            new es.studium.arcanum.FiliusNuevoJuego();
            this.setVisible(false);
        }
        );
        mnMultijugador.addActionListener(event -> {
           // new es.studium.arcanum.FiliusMultijugador();
            this.setVisible(false);
        }
        );
        mnIA.addActionListener(event -> {
            new es.studium.arcanum.FiliusIA();
            this.setVisible(false);
        }
        );
        mnIAvsIA.addActionListener(event -> {
            //new es.studium.arcanum.FiliusIAvsIA();
            this.setVisible(false);
        }
        );
        mnSalir.addActionListener(event -> {
            System.exit(0);
        }
        );
        mnServidor.addActionListener(event -> {
            new es.studium.clientserver.ArcanumServidor();
            this.setVisible(false);
        }
        );
        mnCliente.addActionListener(event -> {
            new es.studium.clientserver.ArcanumCliente();
            this.setVisible(false);
        }
        );
        mnLicencia.addActionListener(event -> {
            //new es.studium.arcanum.mas.Licencia();
        }
        );
        mnAutor.addActionListener(event -> {
           // new es.studium.arcanum.mas.Autor();
        }
        );
        mnComoJugar.addActionListener(event -> {
           // new es.studium.arcanum.mas.ComoJugar();
        }
        );
    }

    public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
        new es.studium.arcanum.PaterClass();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }
}