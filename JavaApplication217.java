/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication217;

package ThiThuUDP;
import java.io.*;
import java.net.*;
import java.util.*;
public class MaxMin {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;

        // a. Gửi mã sinh viên và mã câu hỏi
        String code = ";B21DCCN319;Bca0Dehp";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);

        // b. Nhận dữ liệu từ server
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        String tmp = new String(dpNhan.getData()).trim();
        System.out.println(tmp);

        // c. Xử lý chuỗi và gửi lại kết quả
        tmp = tmp.replace(';', ' ');
        tmp = tmp.replace(',', ' ');
        String[] tmp1 = tmp.trim().split("\\s+");
        String rqID = tmp1[0];
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i < tmp1.length; i++) a.add(Integer.parseInt(tmp1[i]));
        Collections.sort(a);
        int nho = a.get(0);
        int lon = a.get(a.size() - 1);
        String res = String.format("%s;%d,%d", rqID, lon, nho); // Sửa ở đây
        System.out.println(res);
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);

        // d. Đóng socket
        socket.close();
    }
}
package ThiThuUDP;
import java.io.*;
import java.net.*;
import java.util.*;
public class MaxMin2 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;

        // a. Gửi mã sinh viên và mã câu hỏi
        String code = ";B21DCCN319;Aisji6xy";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);

        // b. Nhận dữ liệu từ server
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        String tmp = new String(dpNhan.getData()).trim();
        System.out.println(tmp);

        // c. Xử lý chuỗi và gửi lại kết quả
        tmp = tmp.replace(';', ' ');
        tmp = tmp.replace(',', ' ');
        String[] tmp1 = tmp.trim().split("\\s+");
        String rqID = tmp1[0];
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i < tmp1.length; i++) a.add(Integer.parseInt(tmp1[i]));
        Collections.sort(a);
        int nho = a.get(1);
        int lon = a.get(a.size() - 2);
        String res = String.format("%s;%d,%d", rqID, lon, nho); 
        System.out.println(res);
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);

        // d. Đóng socket
        socket.close();
    }
}
package ThiThuUDP;
import java.io.*;
import java.net.*;
import java.util.*;
public class VietHoa {
    public static void main(String[] args) throws IOException{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        //a.
        String code = ";B21DCCN319;oC4JH4si";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        String s = new String(dpNhan.getData()).trim();
        System.out.println(s);
        //c.
        s = s.replace(';', ' ');
        String []s1 = s.split("\\s+");
        String rqID = s1[0];
        ArrayList<String>a = new ArrayList<>();
        for(int i = 1;i<s1.length;i++) a.add(s1[i]);
        String res = rqID + ";";
        for(String x: a) res+=Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase() + " ";
        System.out.println(res);
        //
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);
        socket.close();
    }
}

package UDP.B17DCAT176;
import java.net.*;
public class LietKeSNT {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        //a.
        String code = ";B17DCAT176;78CCQ6xD";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //
        String st = new String(dpNhan.getData());
        System.out.println(st);
        String []sTmp = st.trim().split(";");
        String rI = sTmp[0]; String s1 = sTmp[1];int n = Integer.parseInt(s1.trim());
        //
        String ans = "";int cnt = 0;
        for(int i = 2;i<=1000000;i++){
            if(check(i)){
                cnt++;
                ans+=String.format("%d,", i);
                if(cnt==n) break;
            }
        }
        ans = ans.substring(0, ans.length() - 1);
        ans = rI + ";" + ans;
        System.out.println(ans);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), sA, sP);
        socket.send(dpGui1);
    }
    public static boolean check(int n){
        for(int i = 2;i*i<=n;i++){
            if(n%i==0) return false;
        }
        return true;
    }
}
package UDP.B21DCCN001;
import java.net.*;
public class ChuanHoaChuoi {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        //a.
        String code = ";B21DCCN001;NRVwBVvx";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //
        String s1 = new String(dpNhan.getData());
        System.out.println(s1);
        String []sTmp = s1.trim().split(";");
        String rI = sTmp[0]; String s = sTmp[1];
        //Chuẩn hoá
        String []tmpS = s.trim().split("\\s+");
        s = "";
        for(String x: tmpS) s+=Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase() + " ";
        String res = rI + ";" + s;
        System.out.println(res);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);
    }
}
package UDP.B21DCCN001;
import java.net.*;
public class DemSoLanXH {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        //a.
        String code = ";B21DCCN001;pWk1YfFe";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //
        String s1 = new String(dpNhan.getData());
        System.out.println(s1);
        String []sTmp = s1.trim().split(";");
        String rI = sTmp[0]; String s = sTmp[1];
        //Đếm
        String res = ""; int []cnt = new int[10005];
        for(char x: s.toCharArray()) cnt[x]++;
        for(char x: s.toCharArray()){
            if(cnt[x]>0){
                res+=String.format("%d%c", cnt[x], x);
                cnt[x] = 0;
            }
        }
        res = rI + ";" + res;
        System.out.println(res);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);
    }
}
package UDP.B21DCCN001;
import java.net.*;
public class TimSoConThieu {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        //a.
        String code = ";B21DCCN001;XdHHmyiv";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //
        String s = new String(dpNhan.getData());
        System.out.println(s);
        String []sTmp = s.trim().split(";");
        String rI = sTmp[0]; int n = Integer.parseInt(sTmp[1]);
        String num = sTmp[2];
        //Xử lý mảng - Đếm
        int []cnt = new int[1000005];
        String []tmp = num.trim().split(",");
        for(int i = 0;i<tmp.length;i++) cnt[Integer.parseInt(tmp[i])]++;
        //Thiếu
        String res = rI + ";";
        for(int i = 1;i<=n;i++){
            if(cnt[i]==0) res+=String.format("%d,", i);
        }
        res = res.substring(0, res.length() - 1);
        System.out.println(res);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);
    }
}
package UDP.B21DCCN001;
import java.net.*;
public class TongCacChuSo {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        //a.
        String code = ";B21DCCN001;0Iend7Pp";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //
        String s1 = new String(dpNhan.getData());
        System.out.println(s1);
        String []sTmp = s1.trim().split(";");
        String rI = sTmp[0]; String s = sTmp[1];
        //Đếm
        int tong = 0;
        for(char x: s.toCharArray()) tong+=(x - '0');
        String res = String.format("%s;%d", rI, tong);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);
    }
}
package UDP.B21DCCN002;
import java.net.*;
public class LocKyTu {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        //a.
        String code = ";B21DCCN002;EbTMtFBH";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //
        String s1 = new String(dpNhan.getData());
        System.out.println(s1);
        String []sTmp = s1.trim().split(";");
        String rI = sTmp[0]; String s = sTmp[1];
        //Đếm
        int []cnt = new int[10005];
        for(char x: s.toCharArray()){ 
            if(Character.isAlphabetic(x)) cnt[x]++;
        }
        String res = "";
        for(char x: s.toCharArray()){ 
            if(cnt[x] > 0){
                res+=x;
                cnt[x] = 0;
            }
        }
        res = rI + ";" + res;
        System.out.println(res);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
        socket.send(dpGui1);
    }
}
package UDP.B21DCCN003;
import java.net.*;
public class MaHoaCaesar {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        //a.
        String code = ";B21DCCN003;vNIIU53V";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //
        String s1 = new String(dpNhan.getData());
        System.out.println(s1);
        String []sTmp = s1.trim().split(";");
        String rI = sTmp[0]; String s = sTmp[1]; int doDich = Integer.parseInt(sTmp[2].trim());
        //
        String ans = "";
        for(char x: s.toCharArray()){
            char base = Character.isUpperCase(x) ? 'A' : 'a';
            x = (char) ((x - base + doDich) % 26 + base);
            ans+=x;
        }
        ans = rI + ";" + ans;
        System.out.println(ans);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), sA, sP);
        socket.send(dpGui1);
    }
}
package UDP.B21DCCN003;
import java.net.*;
public class XHMax {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        //a.
        String code = ";B21DCCN003;KFPR3GWV";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //
        String s1 = new String(dpNhan.getData());
        System.out.println(s1);
        String []sTmp = s1.trim().split(";");
        String rI = sTmp[0]; String s = sTmp[1]; 
        //Đếm
        String ans = "";
        int []cnt = new int[1000];
        for(char x: s.toCharArray()) cnt[x]++;
        //Tìm max
        int Max = -1; char chr = ' ';
        for(char x: s.toCharArray()){
            if(cnt[x] > Max){
                Max = cnt[x];
                chr = x;
            }
        }
        ans = rI + ";" + chr + ":";
        //Lôi các vị trí có sẵn ra
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)==chr) ans+=String.format("%d,", i + 1);
        }
        System.out.println(ans);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), sA, sP);
        socket.send(dpGui1);
    }
}
package UDP.B21DCCN020;
import java.net.*;
public class HieuHaiTapTu {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2208;
        //a.
        String code = ";B21DCCN020;JQCO3izC";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //
        String st = new String(dpNhan.getData());
        System.out.println(st);
        String []sTmp = st.trim().split(";");
        String rI = sTmp[0]; String s1 = sTmp[1], s2 = sTmp[2];
        //
        String ans = "";
        int []cnt = new int[10005]; 
        for(char x: s2.toCharArray()) cnt[x]++;
        for(char x: s1.toCharArray()){
            if(cnt[x]==0) ans+=x;
        }
        System.out.println(ans);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), sA, sP);
        socket.send(dpGui1);
    }
}
