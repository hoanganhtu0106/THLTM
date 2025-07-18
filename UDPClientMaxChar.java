/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package udpclientmaxchar;

/**
 *
 * @author user
 */
import java.net.*;

public class UDPClientMaxChar {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 808;

        // a. Gửi ";studentCode;qCode"
        String code = ";B21DCCN001;EE29C059";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);

        // b. Nhận "requestId;data"
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);

        String s1 = new String(dpNhan.getData(), 0, dpNhan.getLength());
        String[] sTmp = s1.trim().split(";");
        String rI = sTmp[0];
        String data = sTmp[1];

        // c. Tìm ký tự xuất hiện nhiều nhất và các vị trí
        int[] count = new int[256];
        for (char c : data.toCharArray()) count[c]++;
        char maxChar = 0;
        for (int i = 0; i < 256; i++) {
            if (count[i] > count[maxChar]) maxChar = (char) i;
        }

        // Tìm vị trí xuất hiện (bắt đầu từ 0)
        StringBuilder pos = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == maxChar) pos.append(i).append(",");
        }

        // Ghép thông điệp gửi lại
        String res = rI + ";" + maxChar + ":" + pos;
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);

        socket.close();
    }
}
