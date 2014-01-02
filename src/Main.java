import java.util.List;

/**
 * Created by ilya on 16.12.13.
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            howToUse();
            return;
        }

        String host = args[0];
        System.out.println("Scanning host "+host);

        PortScanner scanner = new PortScanner();

        if (args.length==2) {
            if (args[1].indexOf("-")>-1) {
                // range of ports pointed out
                String[] ports = args[1].split("-");
                try {
                    int minPort = Integer.parseInt(ports[0]);
                    int maxPort = Integer.parseInt(ports[1]);
                    scanner.setMinPort(minPort);
                    scanner.setMaxPort(maxPort);
                } catch (NumberFormatException nfe) {
                    System.out.println("Wrong ports!");
                    return;
                }
            } else {
                // one port pointed out
                try {
                    int port = Integer.parseInt(args[1]);
                    scanner.setMinPort(port);
                    scanner.setMaxPort(port);
                } catch (NumberFormatException nfe) {
                    System.out.println("Wrong port!");
                    return;
                }
            }
        }

        scanner.setHost(host);
        List<Integer> openPortsList = scanner.scan();
        if (openPortsList != null) {
            if (openPortsList.size() >0) {
                System.out.println("List of opened ports:");
                for (Integer openedPort : openPortsList) {
                    System.out.println(openedPort);
                }
            } else {
                System.out.println("No opened ports!");
            }
        } else {
            System.out.println("Error happened!");
        }
    }

    static void howToUse() {
        System.out.println("Examples:");
        System.out.println("java PortScanner 192.168.1.100 1-1024");
        System.out.println("java PortScanner 192.168.1.100 1099");
        System.out.println("java PortScanner 192.168.1.100 (this scans all ports from 1 to 65535)");
    }
}
