package com.quaishi.vzr.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Laughing_Vzr on 2016/5/26.
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务启动于端口：" + port);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                // 创建线程处理请求
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if (serverSocket != null) {
                System.out.println("服务关闭....");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
