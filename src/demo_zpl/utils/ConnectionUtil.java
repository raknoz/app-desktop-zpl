/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.utils;

import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.DriverPrinterConnection;
import com.zebra.sdk.comm.TcpConnection;
/**
 *
 * @author davidgomez
 */
public class ConnectionUtil {

    public static Connection getConnection(final String ipAddress, final int portNumber) throws ConnectionException {
        try {
            return new TcpConnection(ipAddress, portNumber);
        } catch (NumberFormatException e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    public static Connection getConnectionUSB() {
        DriverPrinterConnection conn = null;
        try {
            conn = new DriverPrinterConnection("ZDesigner ZQ520 (ZPL)");
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
