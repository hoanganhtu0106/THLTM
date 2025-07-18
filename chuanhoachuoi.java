import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109"); // IP server
        int port = 808;

        // a. Gửi chuỗi mã sinh viên và mã câu hỏi
        String studentCode = "B15DCCN001";
        String qCode = "5B35BCC1";
        String sendData = ";" + studentCode + ";" + qCode;
        byte[] sendBuffer = sendData.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, port);
        socket.send(sendPacket);

        // b. Nhận chuỗi từ server
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);
        String received = new String(receivePacket.getData(), 0, receivePacket.getLength());

        // Tách requestId và data
        String[] parts = received.split(";", 2);
        String requestId = parts[0];
        String data = parts[1];

        // c. Chuẩn hóa chuỗi: viết hoa chữ cái đầu, còn lại viết thường
        String[] words = data.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        String normalized = sb.toString().trim();

        // Gửi lại cho server
        String response = requestId + ";" + normalized;
        byte[] responseBuffer = response.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, serverAddress, port);
        socket.send(responsePacket);

        // d. Đóng socket
        socket.close();
    }
}
