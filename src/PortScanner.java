import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 15.12.13.
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
public class PortScanner {
    private int minPort = 1;
    private int maxPort = 65535;
    private String host = "192.168.1.1";
    private int timeout = 100;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMinPort() {
        return minPort;
    }

    public void setMinPort(int minPort) {
        this.minPort = minPort;
    }

    public int getMaxPort() {
        return maxPort;
    }

    public void setMaxPort(int maxPort) {
        this.maxPort = maxPort;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<Integer> scan() {
        try {
            InetAddress inetAddress = InetAddress.getByName(getHost());
            return scan(inetAddress);
        } catch (IOException ioe) {
            return null;
        }
    }

    List<Integer> scan(InetAddress inetAddress) {
        List<Integer> openPortsList = new ArrayList<Integer>(0xFF);
        System.out.println("scanning ports: ");
        for (int port = minPort; port <= maxPort; port++) {
            System.out.print(port);
            try {
                InetSocketAddress inetSocketAddress
                        = new InetSocketAddress(inetAddress, port);
                Socket socket = new Socket();
                socket.connect(inetSocketAddress, timeout);
                System.out.println(" opened");
                openPortsList.add(port);
                socket.close();
            } catch (IOException ioe) {
                System.out.println("");
            }
        }
        return openPortsList;
    }
}
