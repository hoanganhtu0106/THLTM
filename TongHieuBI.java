/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tonghieubi;

/**
 *
 * @author user
 */
import java.net.*;
import java.math.BigInteger;

public class TongHieuBI {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 807;

        // a. Gửi chuỗi ";studentCode;qCode"
        String code = ";B15DCCN010;D3F9A7B8";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);

        // b. Nhận "requestId;a;b"
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);

        String s1 = new String(dpNhan.getData(), 0, dpNhan.getLength());
        String[] sTmp = s1.trim().split(";");

        String rI = sTmp[0];
        BigInteger a = new BigInteger(sTmp[1]);
        BigInteger b = new BigInteger(sTmp[2]);

        // c. Tính tổng và hiệu
        BigInteger tong = a.add(b);
        BigInteger hieu = a.subtract(b);
        String res = String.format("%s;%s;%s", rI, tong.toString(), hieu.toString());

        // d. Gửi kết quả
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);

        socket.close();
    }
}
