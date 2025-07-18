/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package missing;

/**
 *
 * @author user
 */
import java.net.*;
import java.util.*;

public class Missing {
    public static void main(String[] args) throws Exception {
        DatagramSocket sP = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int port = 807;

        String dG = ";B15DCCN001;371EA16D";
        byte[] dN = dG.getBytes();
        DatagramPacket gP = new DatagramPacket(dN, dN.length, sA, port);
        sP.send(gP);

        byte[] buf = new byte[1024];
        DatagramPacket nP = new DatagramPacket(buf, buf.length);
        sP.receive(nP);
        String res = new String(nP.getData(), 0, nP.getLength());

        String[] tmp = res.split(";");
        String reqId = tmp[0];
        int n = Integer.parseInt(tmp[1]);
        String[] arr = tmp[2].split(",");
        Set<Integer> exist = new HashSet<>();
        for (String a : arr) exist.add(Integer.parseInt(a));
        List<Integer> miss = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!exist.contains(i)) miss.add(i);
        }

        StringBuilder sb = new StringBuilder(reqId + ";");
        for (int i = 0; i < miss.size(); i++) {
            sb.append(miss.get(i));
            if (i < miss.size() - 1) sb.append(",");
        }
        byte[] rep = sb.toString().getBytes();
        DatagramPacket rP = new DatagramPacket(rep, rep.length, sA, port);
        sP.send(rP);

        sP.close();
    }
}
