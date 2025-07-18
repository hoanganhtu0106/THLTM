/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binarysum;
import java.net.*;

public class BinarySum {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 808;

        // a. Gửi thông điệp ";studentCode;qCode"
        String code = ";B15DCCN000;XbYdNZ3";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);

        // b. Nhận "requestId;b1,b2"
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);

        String s1 = new String(dpNhan.getData(), 0, dpNhan.getLength());
        String[] sTmp = s1.trim().split(";");
        String rI = sTmp[0];
        String[] bin = sTmp[1].split(",");

        // c. Tính tổng hai chuỗi nhị phân
        int sum = Integer.parseInt(bin[0], 2) + Integer.parseInt(bin[1], 2);
        String res = rI + ";" + sum;

        // Gửi kết quả
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);

        socket.close();
    }
}
