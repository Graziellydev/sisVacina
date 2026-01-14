package app.ui.components;

import app.ui.theme.UITheme;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {

    public TitleLabel(String text) {
        super(text, SwingConstants.CENTER);
        setFont(UITheme.TITULO);
        setForeground(UITheme.AZUL_TITULO);
        setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
    }
}
