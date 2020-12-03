/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.discovery.DiscoveredPrinter;
import com.zebra.sdk.printer.discovery.DiscoveryException;
import com.zebra.sdk.printer.discovery.DiscoveryHandler;
import com.zebra.sdk.printer.discovery.NetworkDiscoverer;
import com.zebra.sdk.printer.discovery.UsbDiscoverer;
import com.zebra.sdk.printer.discovery.ZebraPrinterFilter;
import demo_zpl.dto.DiscoveredPrinterDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author davidgomez
 */
public class FindPrinterlocalBroadcastService {

    final class DiscoveryHandlerImpl implements DiscoveryHandler {

        private final List<DiscoveredPrinter> printers = new ArrayList<>();

        public void foundPrinter(final DiscoveredPrinter printer) {
            printers.add(printer);
        }

        public void discoveryFinished() {
            System.out.println("Discovered " + printers.size() + " printers.");
        }

        public void discoveryError(final String message) {
            System.out.println("An error occurred during discovery : " + message);
        }
    };

    public synchronized List<DiscoveredPrinterDto> findLocalWirelessPrinters() throws Exception {
        final DiscoveryHandlerImpl discoveryHandler = new DiscoveryHandlerImpl();
        try {
            System.out.println("Starting Wireless printer discovery...");
            NetworkDiscoverer.localBroadcast(discoveryHandler);
            //Time to wait subnetSearch in base on 3rd parameter
            Thread.sleep(5000);
        } catch (DiscoveryException de) {
            de.printStackTrace();
            throw new DiscoveryException(de.getMessage());
        } catch (final Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
        return discoveryHandler.printers.stream()
                .map(dp -> new DiscoveredPrinterDto(dp))
                .collect(Collectors.toList());
    }

    public synchronized List<DiscoveredPrinterDto> findLocalUsbPrinters() throws Exception {
        final DiscoveryHandlerImpl discoveryHandler = new DiscoveryHandlerImpl();
        try {
            System.out.println("Starting USB printer discovery...");
            UsbDiscoverer.getZebraUsbPrinters(new ZebraPrinterFilter());
            //Time to wait subnetSearch in base on 3rd parameter
            Thread.sleep(5000);
        } catch (ConnectionException de) {
            de.printStackTrace();
            throw new ConnectionException(de.getMessage());
        } catch (final Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
        return discoveryHandler.printers.stream()
                .map(dp -> new DiscoveredPrinterDto(dp))
                .collect(Collectors.toList());
    }
}
