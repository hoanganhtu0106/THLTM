/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tudiennguoc;

/**
 *
 * @author user
 */
import java.net.*;
import java.util.*;

public class Tudiennguoc {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        // a. Gửi thông điệp dạng ";studentCode;qCode"
        String dpgui = ";B15DCCN009;EF56GH78";
        byte[] sendData = dpgui.getBytes();
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, 808);
        socket.send(sendPacket);

        // b. Nhận thông điệp dạng "requestId;data"
        byte[] recvBuf = new byte[1024];
        DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
        socket.receive(recvPacket);
        String dpnhan = new String(recvPacket.getData(), 0, recvPacket.getLength());

        // c. Tách requestId và data, sắp xếp từ theo thứ tự từ điển ngược
        String[] parts = dpnhan.split(";", 2);
        String requestId = parts[0];
        String data = parts[1];

        String[] words = data.trim().split("\\s+");
        Arrays.sort(words, Collections.reverseOrder());
        String res = String.join(",", words);

        // d. Gửi lại theo định dạng "requestId;word1,word2,..."
        String tmp = requestId + ";" + res;
        byte[] sendBack = tmp.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(sendBack, sendBack.length, ip, 808);
        socket.send(sendPacket2);

        socket.close();
    }
}
