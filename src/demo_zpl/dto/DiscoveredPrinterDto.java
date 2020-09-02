/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.dto;

import com.zebra.sdk.printer.discovery.DiscoveredPrinter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author davidgomez
 */
public class DiscoveredPrinterDto {

    private String ipAddress;
    private Map<String, String> properties = new HashMap<>();
    private String strProperties;

    private DiscoveredPrinterDto() {
    }

    public DiscoveredPrinterDto(final String ipAddress, final Map<String, String> properties, final String propertiesStr) {
        this.ipAddress = ipAddress;
        this.properties = properties;
        this.strProperties = propertiesStr;
    }

    public DiscoveredPrinterDto(final DiscoveredPrinter discoveredPrinter) {
        this.ipAddress = discoveredPrinter.address;
        this.properties = discoveredPrinter.getDiscoveryDataMap();
        this.strProperties = generateStrProperties(properties);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public String getStrProperties() {
        return strProperties;
    }

    private String generateStrProperties(final Map<String, String> properties) {
        final StringBuilder sb = new StringBuilder();
        for (final String key : properties.keySet()) {
            sb.append(key)
                    .append(": ")
                    .append(properties.get(key))
                    .append("\r\n");
        }
        return sb.toString();
    }

}
