import java.net.*;
import java.util.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String msv = "B15DCCN007";
        String qCode = "AB12CD34";
        String host = "203.162.10.109";
        int port = 808;

        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(host);

        // a. Gửi chuỗi ";B15DCCN007;AB12CD34"
        String msg = ";" + msv + ";" + qCode;
        byte[] data = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, port);
        socket.send(sendPacket);

        // b. Nhận chuỗi "requestId;data"
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);
        String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
        String[] parts = received.split(";", 2);
        String requestId = parts[0];
        String dataStr = parts[1];

        // c. Đếm và sắp xếp
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : dataStr.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> sorted = new ArrayList<>(freq.entrySet());
        sorted.sort((a, b) -> {
            int cmp = b.getValue().compareTo(a.getValue());
            return cmp != 0 ? cmp : a.getKey().compareTo(b.getKey());
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sorted.size(); i++) {
            Map.Entry<Character, Integer> e = sorted.get(i);
            sb.append(e.getKey()).append(":").append(e.getValue());
            if (i < sorted.size() - 1) sb.append(",");
        }

        String result = requestId + ";" + sb.toString();
        byte[] replyData = result.getBytes();
        DatagramPacket replyPacket = new DatagramPacket(replyData, replyData.length, address, port);
        socket.send(replyPacket);

        // d. Kết thúc
        socket.close();
    }
}
