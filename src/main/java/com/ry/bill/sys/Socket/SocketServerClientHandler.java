package com.ry.bill.sys.Socket;

import java.io.InputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author CKFuture
 * @since 2021-01-05
 * @description 服务端客户消息处理线程类
 **/
public class SocketServerClientHandler extends Thread{

    //每个消息通过Socket进行传输
    private Socket clientConnectSocket;
    //客户端地址
    private SocketAddress clientAddress;

    public SocketServerClientHandler(Socket clientConnectSocket){
        this.clientConnectSocket = clientConnectSocket;
        clientAddress =clientConnectSocket.getRemoteSocketAddress();
    }

    @Override
    public void run(){
        try {
            InputStream inputStream = clientConnectSocket.getInputStream();
            while (true) {
                byte[] data = new byte[100];
                int len;
                while ((len = inputStream.read(data)) != -1) {
                    String message = new String(data, 0, len);
                    System.out.println(clientAddress+": " + message);
                    clientConnectSocket.getOutputStream().write(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

