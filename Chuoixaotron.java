/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chuoixaotron;

/**
 *
 * @author user
 */
import java.net.*;
import java.util.*;

public class Chuoixaotron {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        // a. Gửi chuỗi theo định dạng ";studentCode;qCode"
        String msg = ";B15DCCN009;F3E8B2D4";
        byte[] sendData = msg.getBytes();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 807);
        socket.send(sendPacket);

        // b. Nhận thông điệp từ server
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Nhận từ server: " + response);

        // c. Xử lý chuỗi
        // Ví dụ: "veM3xgA1g:4,IPFfgEanY:5,aWXlSzDwe:2,PHupvPc:3,PR3gH8ahN:6,UEEKHLIt:7,M6dpWTE:1"
        String[] parts = response.split(";", 2);
        String requestId = parts[0];
        String shuffledString = parts[1];

        String[] segments = shuffledString.split(",");
        String[] ordered = new String[segments.length];

        for (String segment : segments) {
            String[] temp = segment.split(":");
            String str = temp[0];
            int pos = Integer.parseInt(temp[1]) - 1;
            ordered[pos] = str;
        }

        // Ghép lại chuỗi theo thứ tự
        StringBuilder result = new StringBuilder();
        for (String s : ordered) result.append(s);

        String finalMsg = requestId + ";" + result.toString();
        System.out.println("Chuỗi gửi lại server: " + finalMsg);

        // d. Gửi kết quả về server
        byte[] finalData = finalMsg.getBytes();
        DatagramPacket returnPacket = new DatagramPacket(finalData, finalData.length, serverAddress, 807);
        socket.send(returnPacket);

        socket.close();
    }
}
