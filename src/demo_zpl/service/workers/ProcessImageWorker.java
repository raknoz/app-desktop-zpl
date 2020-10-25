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
public class ProcessImageWorker {

    private final JButton btnProcess;
    private final JTextArea txtDisplay;
    private final String pathFile;

    public ProcessImageWorker(final JButton btnProcess, final JTextArea txtDisplay, final String pathFile) {
        this.btnProcess = btnProcess;
        this.txtDisplay = txtDisplay;
        this.pathFile = pathFile;
    }

    public void process() throws Exception {

        SwingWorker<String, Integer> worker = new SwingWorker<String, Integer>() {

            @Override
            protected String doInBackground() throws Exception {
                // Simulate doing something useful.
                btnProcess.setText("Working...");
                btnProcess.setEnabled(false);
                Thread.sleep(1000);
                // Here we can return some object of whatever type
                // we specified for the first template parameter.
                // (in this case we're auto-boxing 'true').

                // The type we pass to publish() is determined
                // by the second template parameter.
                // publish(i);
                return ImageConverterService.main(pathFile);
            }

            @Override
            // Can safely update the GUI from this method.
            protected void process(List<Integer> chunks) {
                // Here we receive the values that we publish().
                // They may come grouped in chunks.
            }

            // Can safely update the GUI from this method.
            @Override
            protected void done() {
                try {
                    // Retrieve the return value of doInBackground.
                    btnProcess.setText("Generate zpl logo");
                    btnProcess.setEnabled(true);
                    final String value = get();
                    txtDisplay.setText(value);
                } catch (InterruptedException e) {
                    // This is thrown if the thread's interrupted.
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                } catch (ExecutionException e) {
                    // This is thrown if we throw an exception from doInBackground.
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }

            }

        };
        worker.execute();
    }
}
