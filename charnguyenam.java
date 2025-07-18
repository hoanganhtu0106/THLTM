import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws Exception {
        // a. Tạo socket
        DatagramSocket client = new DatagramSocket();

        // b. Gửi chuỗi có dạng ";studentCode;qCode"
        String msg = ";B15DCCN008;CD34EF56";
        byte[] send = msg.getBytes();
        InetAddress ip = InetAddress.getByName("localhost");
        DatagramPacket dpSend = new DatagramPacket(send, send.length, ip, 808);
        client.send(dpSend);

        // c. Nhận phản hồi từ server (dạng "requestId;data")
        byte[] buff = new byte[1024];
        DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
        client.receive(dpReceive);

        String received = new String(dpReceive.getData(), 0, dpReceive.getLength());
        // Tách thành requestId và data
        String[] parts = received.split(";", 2);
        String requestId = parts[0];
        String data = parts[1];

        // d. Phân tích từ và kiểm tra nguyên âm
        String[] words = data.split(" ");
        StringBuilder result = new StringBuilder();
        for (String w : words) {
            if (isAllVowels(w)) {
                if (result.length() > 0) result.append(",");
                result.append(w);
            }
        }

        // e. Gửi lại kết quả lên server theo dạng "requestId;word1,word2,..."
        String finalMsg = requestId + ";" + result.toString();
        byte[] rep = finalMsg.getBytes();
        DatagramPacket dpRep = new DatagramPacket(rep, rep.length, ip, 808);
        client.send(dpRep);

        // f. Đóng socket
        client.close();
    }

    // Hàm kiểm tra toàn bộ ký tự có là nguyên âm không
    public static boolean isAllVowels(String s) {
        s = s.toLowerCase();
        for (char c : s.toCharArray()) {
            if ("aeiou".indexOf(c) == -1) return false;
        }
        return true;
    }
}
