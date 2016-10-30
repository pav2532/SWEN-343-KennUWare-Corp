package com.kennuware.customersupport.Utilities;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 * Created by benjamin on 10/30/16.
 */
public class HttpUtils {

    public static String handle(HttpPost request, Object data, String newURI){
        try {

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            request.setURI(new URI(newURI));
            request.addHeader("content-type", "application/json");

            Gson gson = new Gson();
            StringEntity body = new StringEntity(gson.toJson(data));
            request.setEntity(body);

            System.out.println("Executing request " + request.getRequestLine().toString());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            return httpClient.execute(request, responseHandler);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
