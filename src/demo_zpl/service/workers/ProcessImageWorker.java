/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service.workers;

import demo_zpl.gui.Main;
import demo_zpl.service.ImageConverterService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author davidgomez
 */
public class ProcessImageWorker extends SwingWorker<String, Void> {

    private final JButton btnAcction;
    private final JTextArea textArea;
    private final String pathFile;

    public ProcessImageWorker(final JButton btnAction, final JTextArea textArea, final String pathFile) {
        this.btnAcction = btnAction;
        this.textArea = textArea;
        this.pathFile = pathFile;
    }

    @Override
    protected String doInBackground() throws Exception {
        // Simulate doing something useful.
        btnAcction.setText("Working...");
        btnAcction.setEnabled(false);
        Thread.sleep(1000);
        // Here we can return some object of whatever type
        // we specified for the first template parameter.
        // (in this case we're auto-boxing 'true').

        // The type we pass to publish() is determined
        // by the second template parameter.
        // publish(i);
        return ImageConverterService.main(pathFile);
    }

    // Can safely update the GUI from this method.
    @Override
    protected void process(List<Void> chunks) {
        // Here we receive the values that we publish().
        // They may come grouped in chunks.
    }

    // Can safely update the GUI from this method.
    @Override
    protected void done() {
        try {
            // Retrieve the return value of doInBackground.
            btnAcction.setText("Generate zpl logo");
            btnAcction.setEnabled(true);
            final String value = get();
            textArea.setText(value);
        } catch (InterruptedException e) {
            // This is thrown if the thread's interrupted.
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        } catch (ExecutionException e) {
            // This is thrown if we throw an exception from doInBackground.
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
