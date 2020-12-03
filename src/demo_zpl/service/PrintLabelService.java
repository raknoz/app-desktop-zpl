/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import demo_zpl.utils.ConnectionUtil;
import java.nio.charset.StandardCharsets;

/**
 * @author davidgomez
 */
public class PrintLabelService {

    public void sendLabelToPrintIP(final String ipAddress, final int port, final String zebraLabel) throws ConnectionException, Exception {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnectionIP(ipAddress, port);
            printerConnection.open();
            printerConnection.write(zebraLabel.getBytes(StandardCharsets.UTF_8));
        } catch (final ConnectionException ex) {
            ex.printStackTrace();
            throw new ConnectionException("Connection fail");
        } catch (final Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error in function");
        } finally {
            try {
                if (printerConnection != null) {
                    printerConnection.close();
                }
            } catch (final ConnectionException e) {
                e.printStackTrace();
                throw new ConnectionException("Connection fail");
            }
        }
    }

    public void sendLabelToPrintUSB(final String usbName, final String zebraLabel) throws ConnectionException, Exception {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnectionUSB(usbName);
            printerConnection.open();
            printerConnection.write(zebraLabel.getBytes(StandardCharsets.UTF_8));
        } catch (final ConnectionException ex) {
            ex.printStackTrace();
            throw new ConnectionException("Connection fail");
        } catch (final Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error in function");
        } finally {
            try {
                if (printerConnection != null) {
                    printerConnection.close();
                }
            } catch (final ConnectionException e) {
                e.printStackTrace();
                throw new ConnectionException("Connection fail");
            }
        }
    }

    /*
     * Returns the command for a test label depending on the printer control language
     * The test label is a box with the word "TEST" inside of it
     * 
     * _________________________
     * |                       |
     * |                       |
     * |        TEST           |
     * |                       |
     * |                       |
     * |_______________________|
     * 
     * 
     */
    private String getTestLabel() {
        return "^XA^POI^LH0,0^FO17,16^GB379,371,8^FS^FT65,255^A0N,135,134^FDTEST^FS^XZ";
    }
}
