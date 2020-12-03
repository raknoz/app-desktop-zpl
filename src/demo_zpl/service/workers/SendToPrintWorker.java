/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service.workers;

import com.zebra.sdk.comm.ConnectionException;
import demo_zpl.dto.DataToPrintDTO;
import demo_zpl.gui.Main;
import demo_zpl.service.PrintLabelService;
import demo_zpl.utils.GuiUtils;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author davidgomez
 */
public class SendToPrintWorker extends SwingWorker<Boolean, Void> {

    private final JPanel jpanel;
    private final JButton btnAcction;
    private final JLabel labelStatus;
    private final PrintLabelService printLabelService;
    private final DataToPrintDTO dataToPrintDTO;

    public SendToPrintWorker(final JPanel jpanel, final JLabel labelStatus,
            final JButton btnAction, final DataToPrintDTO dataToPrintDTO) {
        this.jpanel = jpanel;
        this.labelStatus = labelStatus;
        this.btnAcction = btnAction;
        this.printLabelService = new PrintLabelService();
        this.dataToPrintDTO = dataToPrintDTO;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        // Simulate doing something useful.
        btnAcction.setText("Working...");
        GuiUtils.updateStatusBarOnGui(labelStatus, "Sending...", Color.YELLOW, false);
        GuiUtils.setPanelEnabled(jpanel, Boolean.FALSE);

        switch (dataToPrintDTO.getTypeConnection()) {
            case WIRELESS_CONNECT:
                printLabelService
                        .sendLabelToPrintIP(dataToPrintDTO.getIpAddress(),
                                dataToPrintDTO.getPort(),
                                dataToPrintDTO.getTextToBePrinted());
                return true;
            case USB_CONNECT:
                printLabelService
                        .sendLabelToPrintUSB(dataToPrintDTO.getUsbIdName(),
                                dataToPrintDTO.getTextToBePrinted());
                return true;
            default:
                printLabelService
                        .sendLabelToPrintIP(dataToPrintDTO.getIpAddress(),
                                dataToPrintDTO.getPort(),
                                dataToPrintDTO.getTextToBePrinted());
                return true;
        }
    }

    @Override
    // Can safely update the GUI from this method.
    protected void process(List<Void> chunks) {
        // Here we receive the values that we publish().
        // They may come grouped in chunks.
    }

    @Override
    protected void done() {
        try {
            // Retrieve the return value of doInBackground.
            get();
            GuiUtils.updateStatusBarOnGui(labelStatus, "Done!", Color.GREEN, false);
        } catch (InterruptedException ie) {
            // This is thrown if the thread's interrupted.
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ie);
            GuiUtils.updateStatusBarOnGui(labelStatus, "Process Failed!", Color.RED, false);
        } catch (ExecutionException ee) {
            // This is thrown if we throw an exception from doInBackground.
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ee);
            //Validate exception of Zebra
            if (ee.getCause() instanceof ConnectionException) {
                GuiUtils.updateStatusBarOnGui(labelStatus, "Connection Failed!", Color.RED, false);
            } else {
                GuiUtils.updateStatusBarOnGui(labelStatus, "Process Failed!", Color.RED, false);
            }
        } finally {
            btnAcction.setText("Send Printer");
            GuiUtils.setPanelEnabled(jpanel, Boolean.TRUE);
        }
    }

}
