import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        // a. Gửi thông điệp với định dạng ";studentCode;qCode"
        String studentCode = "B15DCCN001";
        String qCode = "B34D51E0";
        String message = ";" + studentCode + ";" + qCode;

        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 808;

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
        socket.send(sendPacket);

        // b. Nhận thông điệp từ server: "requestId;str1;str2"
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String received = new String(receivePacket.getData(), 0, receivePacket.getLength());

        String[] parts = received.split(";", 3);
        String requestId = parts[0];
        String str1 = parts[1];
        String str2 = parts[2];

        // c. Loại bỏ các ký tự trong str1 mà xuất hiện trong str2, giữ nguyên thứ tự
        StringBuilder result = new StringBuilder();
        for (char c : str1.toCharArray()) {
            if (str2.indexOf(c) == -1) {
                result.append(c);
            }
        }

        // Gửi lại server với định dạng "requestId;strOutput"
        String output = requestId + ";" + result.toString();
        byte[] resultData = output.getBytes();
        DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, serverAddress, serverPort);
        socket.send(resultPacket);

        // d. Đóng socket
        socket.close();
    }
}
