package app.ui.components;

import app.ui.theme.UITheme;

import javax.swing.*;
import java.awt.*;

public class TexturedPanel extends JPanel {

    public TexturedPanel() {
        setBackground(UITheme.FUNDO_CREME);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // textura leve (pontilhado)
        g.setColor(new Color(0, 0, 0, 10));
        for (int x = 0; x < getWidth(); x += 6) {
            for (int y = 0; y < getHeight(); y += 6) {
                g.fillOval(x, y, 1, 1);
            }
        }
    }
}
