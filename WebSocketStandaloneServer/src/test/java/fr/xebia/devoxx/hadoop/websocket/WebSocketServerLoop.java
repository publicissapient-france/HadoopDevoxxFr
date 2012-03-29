package fr.xebia.devoxx.hadoop.websocket;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class WebSocketServerLoop {

    @Test
    public void postToHadoopListener() {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(
                    "http://localhost:8082/hadoop");

            StringEntity input = new StringEntity("{\"count\":1,\"user\":\"@BFMT\",\"message\":\" \\\"Avec F. Hollande, ce n'est pas le changement maintenant mais lentement\\\", a ironisé N. Dupont-Aignan à Paris. #présidentielle\"}");
            for (int i = 0; i < 20; i++) {

                input.setContentType("application/json");
                postRequest.setEntity(input);

                HttpResponse response = httpClient.execute(postRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }

                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }

                Thread.sleep(5000);
            }

            httpClient.getConnectionManager().shutdown();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
