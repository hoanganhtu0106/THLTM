/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loaibokitu;

/**
 *
 * @author user
 */
import java.net.*;
import java.util.*;

public class Loaibokitu {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        int port = 808;

        // a. Gửi thông điệp chứa mã sinh viên và mã câu hỏi
        String msg = ";B15DCCN001;06D6800D";
        byte[] sb = msg.getBytes();
        DatagramPacket sp = new DatagramPacket(sb, sb.length, ip, port);
        s.send(sp);

        // b. Nhận thông điệp từ server
        byte[] rb = new byte[1024];
        DatagramPacket rp = new DatagramPacket(rb, rb.length);
        s.receive(rp);
        String res = new String(rp.getData(), 0, rp.getLength());

        // Phân tách thành requestId và strInput
        String[] parts = res.split(";", 2);
        String id = parts[0];
        String input = parts[1];

        // c. Xử lý chuỗi: loại bỏ ký tự đặc biệt, số, ký tự trùng
        StringBuilder out = new StringBuilder();
        Set<Character> seen = new HashSet<>();
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch) && !seen.contains(ch)) {
                seen.add(ch);
                out.append(ch);
            }
        }

        // Gửi kết quả lại cho server theo định dạng "requestId;strOutput"
        String result = id + ";" + out.toString();
        byte[] fb = result.getBytes();
        DatagramPacket fp = new DatagramPacket(fb, fb.length, ip, port);
        s.send(fp);

        // d. Đóng socket
        s.close();
    }
}
