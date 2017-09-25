package com.example.androidca;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author John Ericksen
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = SSLSocketFactory.getDefault().createSocket("localhost", 9999);

        InputStream stream = socket.getInputStream();

        byte[] buff = new byte[128];
        int len = 0;
        while((len = stream.read(buff)) > -1) {
            System.out.write(buff, 0, len);
        }
    }
}
