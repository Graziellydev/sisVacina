package app.ui;

import javax.swing.*;
import java.awt.*;

public class TemaUI {

    public static void aplicarTema() {
        UIManager.put("Button.background", new Color(30, 144, 255));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Panel.background", new Color(230, 240, 255));
        UIManager.put("Label.foreground", new Color(0, 51, 102));
    }
}
