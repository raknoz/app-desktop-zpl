/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author davidgomez
 */
public class GuiUtils {

    public static void updateStatusBarOnGui(final JLabel jLabel, final String statusMessage,
            final Color color, final boolean isReset) {
        SwingUtilities.invokeLater(() -> {
            jLabel.setText(statusMessage);
            jLabel.setBackground(color);
            jLabel.setOpaque(!isReset);
            final Font font1 = new Font("Courier", Font.BOLD, 15);
            jLabel.setFont(font1);
            jLabel.repaint();
        });
    }

    public static void resetLabelStatus(final JLabel jLabel) {
        updateStatusBarOnGui(jLabel, "status", new Color(242, 241, 240), true);
    }

    public static void updateProgeressBarOnGui(final JProgressBar jpb, final String statusMessage,
            final Color color, final boolean isReset) {
        SwingUtilities.invokeLater(() -> {
            jpb.setMaximum(100);
            jpb.setIndeterminate(true);
        });
    }

    public static void centreWindow(final Window frame) {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public static void setPanelEnabled(final JPanel jpanel, final Boolean isEnabled) {
        SwingUtilities.invokeLater(() -> {
            jpanel.setEnabled(isEnabled);
            final Component[] components = jpanel.getComponents();
            for (final Component component : components) {
                if (component instanceof JPanel) {
                    setPanelEnabled((JPanel) component, isEnabled);
                }
                component.setEnabled(isEnabled);
            }
        });
    }
    
    public static void setButtonProperties(final JButton jButton, final String text, final Boolean isEnabled) {
         SwingUtilities.invokeLater(() -> {
             jButton.setText(text);
             jButton.setEnabled(isEnabled);
         });
    }
}
