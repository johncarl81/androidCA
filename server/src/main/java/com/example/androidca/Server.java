package com.example.androidca;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author John Ericksen
 */
public class Server {

    public static void main(String[] args) {

        new Server().run();
    }

    public void run() {

        try {

            ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(9999);
            System.out.println("Server is running...");
            while (true) {
                System.out.println("Waiting for connection");
                Socket socket = serverSocket.accept();
                System.out.println("Socket connected");
                socket.getOutputStream().write("hello".getBytes());
                System.out.println("Wrote to socket \"hello\"");
                socket.close();
                System.out.println("Socket closed");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
