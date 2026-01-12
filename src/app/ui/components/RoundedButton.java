package app.ui.components;

import app.ui.theme.UITheme;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setFont(UITheme.SUBTITULO);
        setForeground(Color.WHITE);
        setBackground(UITheme.AZUL_PRINCIPAL);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setPreferredSize(new Dimension(200, 38));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

        super.paintComponent(g);
        g2.dispose();
    }
}
