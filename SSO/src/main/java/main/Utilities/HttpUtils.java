package main.Utilities;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class HttpUtils {

    public String get(String newURI){
        String result = "";

        try {

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            try {

                HttpGet request = new HttpGet(newURI);
                request.addHeader("content-type", "application/json");

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

                result = httpClient.execute(request, responseHandler);

            } finally {
                httpClient.close();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String post(Object data, String newURI){

        String result = "";

        try {

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            try {

                HttpPost request = new HttpPost(newURI);
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

                result = httpClient.execute(request, responseHandler);

            } finally {
                httpClient.close();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}


