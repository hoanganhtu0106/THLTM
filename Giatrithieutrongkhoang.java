/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package giatrithieutrongkhoang;

/**
 *
 * @author user
 */
import java.net.*;
import java.util.*;

public class Giatrithieutrongkhoang {
    public static void main(String[] args) throws Exception {
        // a. Tạo socket và địa chỉ server
        DatagramSocket s = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        int port = 807;

        // b. Gửi thông điệp ";studentCode;qCode"
        String msv = "B15DCCN001";
        String q = "73457A17";
        String msgSend = ";" + msv + ";" + q;
        byte[] dSend = msgSend.getBytes();
        DatagramPacket pSend = new DatagramPacket(dSend, dSend.length, ip, port);
        s.send(pSend);

        // c. Nhận thông điệp từ server: "requestId;n;A1,A2,...,Am"
        byte[] dRecv = new byte[1024];
        DatagramPacket pRecv = new DatagramPacket(dRecv, dRecv.length);
        s.receive(pRecv);
        String msgRecv = new String(pRecv.getData(), 0, pRecv.getLength());

        // d. Phân tích thông điệp
        String[] parts = msgRecv.split(";");
        String reqId = parts[0];
        int n = Integer.parseInt(parts[1]);
        Set<Integer> appeared = new HashSet<>();
        if (parts.length > 2) {
            String[] nums = parts[2].split(",");
            for (String x : nums) appeared.add(Integer.parseInt(x));
        }

        // e. Tìm giá trị còn thiếu trong khoảng [1..n]
        List<String> missing = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!appeared.contains(i)) missing.add(String.valueOf(i));
        }

        // f. Gửi lại: "requestId;B1,B2,..."
        String msgRes = reqId + ";" + String.join(",", missing);
        byte[] dRes = msgRes.getBytes();
        DatagramPacket pRes = new DatagramPacket(dRes, dRes.length, ip, port);
        s.send(pRes);

        // g. Đóng socket
        s.close();
    }
}
