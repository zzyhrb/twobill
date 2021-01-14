package com.ry.bill.sys.Socket;

import java.net.ServerSocket;

/**
 * @author CKFuture
 * @since 2021-01-05
 * @description Socket服务端启动类
 **/
public class SocketServer {

    private static final int PORT = 8400;//监听的端口号
    private static final String HostName="127.0.0.1";//服务器IP

    public static void main(String[] args) throws Exception{
        SocketServerListenHandler socketServerListenHandler = new SocketServerListenHandler(HostName,PORT);
        socketServerListenHandler.listenClientConnect();
    }

}








