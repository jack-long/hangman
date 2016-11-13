package client_server;

import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws Exception {
        // 设置sever端的链接
        ServerSocket s = new ServerSocket(8888);
        Socket s1 = s.accept();// sever等待链接
        System.out.println(s1.getInetAddress().getHostAddress() + " is online!");
        // 实例化输出流
        DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
        // 实例化输入流
        DataInputStream dis = new DataInputStream(s1.getInputStream());
        // 实例化两个线程的对象
        Thread msr = new MyServerReader(dis);
        Thread msw = new MyServerWriter(dos);
        // 启动线程
        msr.start();
        msw.start();
    }
}

// 创建一个线程用来进行接收,读取数据
class MyServerReader extends Thread {
    private DataInputStream dis;

    public MyServerReader(DataInputStream dis) {
        this.dis = dis;
    }

    public void run() {
        String msg;
        try {
            while (true) {
                msg = dis.readUTF();
                System.out.println("Client:" + msg);
                if (msg.equals("bye")) {
                    System.out.println("Client off line,program exit!");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

// 创建一个进程用来写入并发送数据
class MyServerWriter extends Thread {
    private DataOutputStream dos;

    public MyServerWriter(DataOutputStream dos) {
        this.dos = dos;
    }

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg;
        try {
            while (true) {
                msg = br.readLine();
                dos.writeUTF(msg);
                if (msg.equals("bye")) {
                    System.out.println("Server off line!");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}