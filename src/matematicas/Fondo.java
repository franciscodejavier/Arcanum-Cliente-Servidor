/*
 * Decompiled with CFR 0_123.
 */
package matematicas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fondo
extends JPanel {
    private static final long serialVersionUID = 1;
    private Image background;

    @Override
    public void paintComponent(Graphics g) {
        int width = this.getSize().width;
        int height = this.getSize().height;
        if (this.background != null) {
            g.drawImage(this.background, 0, 0, width, height, null);
        }
        super.paintComponent(g);
    }

    public void setBackground(String imagePath) {
        this.setOpaque(false);
        this.background = new ImageIcon(imagePath).getImage();
        this.repaint();
    }
}

