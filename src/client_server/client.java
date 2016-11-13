package client_server;

import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws Exception, Exception {
        // 链接sever端
        Socket echoSocket = new Socket("127.0.0.1", 8888);
        // 实例化输入流
        DataInputStream in = new DataInputStream(echoSocket.getInputStream());
        // 实例化输出流
        DataOutputStream out = new DataOutputStream(echoSocket.getOutputStream());
        // 实例化两个进程
        Thread mcr = new MyClientReader(in);
        Thread mcw = new MyClientWriter(out);
        // 启动两个进程
        mcr.start();
        mcw.start();
    }
}

// 创建一个线程用来进行接收读取数据
class MyClientReader extends Thread {
    private DataInputStream in;

    public MyClientReader(DataInputStream in) {
        this.in = in;
    }

    public void run() {
        String msg;
        try {
            while (true) {
                msg = in.readUTF();
                System.out.println("Server:" + msg);
                if (msg.equals("bye")) {
                    System.out.println("Server off-line, exit!");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

// 创建一个进程用来写入并发送数据
class MyClientWriter extends Thread {
    private DataOutputStream dos;

    public MyClientWriter(DataOutputStream dos) {
        this.dos = dos;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg;
        try {
            while (true) {
                msg = br.readLine();
                dos.writeUTF(msg);
                if (msg.equals("bye")) {
                    System.out.println("Client off line, exit!");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}