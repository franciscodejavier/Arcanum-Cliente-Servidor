package initium;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import matematicas.Fondo;

public class Initium
extends JFrame
implements WindowListener,
ActionListener {
    private static final long serialVersionUID = 1;
    Fondo fondo = new Fondo();

    public Initium() {
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
        menubar.add(menuJugar);
        menuJugar.add(mnNuevoJuego);
        menuJugar.add(mnIA);
        menuJugar.add(mnSalir);
        menubar.add(menuOnline);
        menuOnline.add(mnServidor);
        menuOnline.add(mnCliente);
        this.setJMenuBar(menubar);
        mnNuevoJuego.addActionListener(event -> {
            new initium.Ludum();
            this.setVisible(false);
        }
        );
        mnIA.addActionListener(event -> {
            new initium.vsIA();
            this.setVisible(false);
        }
        );
        mnSalir.addActionListener(event -> {
            System.exit(0);
        }
        );
        mnServidor.addActionListener(event -> {
            new clienteservidor.Servidor();
            this.setVisible(false);
        }
        );
        mnCliente.addActionListener(event -> {
            new clienteservidor.Cliente();
            this.setVisible(false);
        }
        );
    }

    public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
        new initium.Initium();
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