/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import com.zebra.sdk.printer.discovery.DiscoveredPrinter;
import com.zebra.sdk.printer.discovery.DiscoveryException;
import com.zebra.sdk.printer.discovery.DiscoveryHandler;
import com.zebra.sdk.printer.discovery.NetworkDiscoverer;
import demo_zpl.dto.DiscoveredPrinterDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author davidgomez
 */
public class FindPrinterService {

    final class DiscoveryHandlerImpl implements DiscoveryHandler {

        private final List<DiscoveredPrinter> printers = new ArrayList<>();

        public void foundPrinter(DiscoveredPrinter printer) {
            printers.add(printer);
        }

        public void discoveryFinished() {
            System.out.println("Discovered " + printers.size() + " printers.");
        }

        public void discoveryError(String message) {
            System.out.println("An error occurred during discovery : " + message);
        }
    };

    public synchronized List<DiscoveredPrinterDto> findPrinter(final String range) throws Exception {
        final DiscoveryHandlerImpl discoveryHandler = new DiscoveryHandlerImpl();
        try {
            System.out.println("Starting printer discovery.");
            NetworkDiscoverer.subnetSearch(discoveryHandler, range, 6000);
            Thread.sleep(8000);
        } catch (DiscoveryException de) {
            de.printStackTrace();
            throw new Exception(de.getMessage());
        }
        return discoveryHandler.printers.stream()
                .map(dp -> new DiscoveredPrinterDto(dp))
                .collect(Collectors.toList());
    }
}
