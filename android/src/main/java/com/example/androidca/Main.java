package com.example.androidca;

import android.app.ListActivity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.androidtransfuse.annotations.Activity;
import org.androidtransfuse.annotations.Intent;
import org.androidtransfuse.annotations.IntentFilter;
import org.androidtransfuse.annotations.IntentType;
import org.androidtransfuse.annotations.Layout;
import org.androidtransfuse.annotations.RegisterListener;

import javax.inject.Inject;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;

/**
 * @author John Ericksen
 */
@Activity(label = "Server Test")
@IntentFilter({
        @Intent(type = IntentType.ACTION, name = android.content.Intent.ACTION_MAIN),
        @Intent(type = IntentType.CATEGORY, name = android.content.Intent.CATEGORY_LAUNCHER)
})
@Layout(R.layout.main)
public class Main {

    @Inject
    Context context;

    @RegisterListener(R.id.connect_button)
    public View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                KeyStore trustStore = KeyStore.getInstance("BKS");
                InputStream trustStoreStream = context.getResources().openRawResource(R.raw.truststore);
                trustStore.load(trustStoreStream, "test1234".toCharArray());

                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(trustStore);

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

                Socket socket = sslContext.getSocketFactory().createSocket("ip", 9999);

                InputStream stream = socket.getInputStream();

                byte[] buff = new byte[128];
                int len;
                while ((len = stream.read(buff)) > -1) {
                    String output = new String(buff, 0, len);
                    display("Server sent: " + output);

                }
            } catch (Exception e) {
                display("Exception occured: " + e.getMessage());
                Log.e("androidCA", "Error listening to server", e);
            }
        }
    };

    private void display(String message) {
        Log.i("androidCA", message);
        Toast.makeText(context, message, 10).show();
    }
}
