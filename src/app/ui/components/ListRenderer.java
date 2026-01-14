package app.ui.components;

import app.ui.theme.UITheme;

import javax.swing.*;
import java.awt.*;

public class ListRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);

        label.setFont(UITheme.TEXTO_PADRAO);
        label.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        if (isSelected) {
            label.setBackground(UITheme.AZUL_PRINCIPAL);
            label.setForeground(Color.WHITE);
        } else {
            label.setBackground(UITheme.FUNDO_CREME);
            label.setForeground(UITheme.TEXTO);
        }

        return label;
    }
}
