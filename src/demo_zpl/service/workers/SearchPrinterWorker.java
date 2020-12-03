/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service.workers;

import demo_zpl.dto.DiscoveredPrinterDto;
import demo_zpl.service.FindPrinterlocalBroadcastService;
import demo_zpl.utils.GuiUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

/**
 *
 * @author davidgomez
 */
public class SearchPrinterWorker extends SwingWorker<Void, Void> {

    private final JButton btnAcction;
    private final FindPrinterlocalBroadcastService fps;
    private final List<DiscoveredPrinterDto> discoveredPrinters;
    
    private final Map<String, String> mapDiscoveredPrinters;
    private final JComboBox jComboBox;

    public SearchPrinterWorker(final JButton btnAcction,
            final JComboBox jComboBox, final Map<String, String> mapDiscoveredPrinters) {
        this.btnAcction = btnAcction;
        this.fps = new FindPrinterlocalBroadcastService();
        this.discoveredPrinters = new ArrayList<>();
        this.mapDiscoveredPrinters = mapDiscoveredPrinters;
        this.jComboBox = jComboBox;
    }

    private SearchPrinterWorker() {
        this.btnAcction = null;
        this.fps = null;
        this.discoveredPrinters = null;
         this.mapDiscoveredPrinters = null;
        this.jComboBox = null;
    }

    @Override
    protected Void doInBackground() throws Exception {
        GuiUtils.setButtonProperties(this.btnAcction, "Searching...", Boolean.FALSE);
        this.discoveredPrinters.addAll(fps.findLocalWirelessPrinters());
        this.discoveredPrinters.addAll(fps.findLocalUsbPrinters());
        return null;
    }

    @Override
    // Can safely update the GUI from this method.
    protected void process(List<Void> chunks) {
        // Here we receive the values that we publish().
        // They may come grouped in chunks.
    }

    @Override
    protected void done() {
        GuiUtils.setButtonProperties(this.btnAcction, "Search Printers", Boolean.TRUE);
        if (this.discoveredPrinters != null && !this.discoveredPrinters.isEmpty()) {
            for (final DiscoveredPrinterDto printer : this.discoveredPrinters) {
                this.mapDiscoveredPrinters.put(printer.getIpAddress(), printer.getStrProperties());
                this.jComboBox.addItem(printer.getIpAddress());
            }
            this.jComboBox.setEnabled(Boolean.TRUE);
        }
    }
    
    public List<DiscoveredPrinterDto> getDiscoveredPrinters() {
        return this.discoveredPrinters;
    }   
}
