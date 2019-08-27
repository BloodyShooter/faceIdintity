package org.gvozdetscky.logic.detectionapi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.gvozdetscky.logic.CmdClass;

import java.io.*;

public class Detection {


    public String faceIdintity(String pathImage1, String pathImage2) {
        CmdClass cmd = new CmdClass();

        String out = cmd.run("C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\venv\\Scripts\\python.exe " +
                          "C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\script\\face_idintity.py " +
                pathImage1 + " " + pathImage2);

        return out;
    }

    public String faceIdintityUrl(String pathImage1, String pathImage2) {

        String responseText = "";

        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();

            HttpPost httppost = new HttpPost("http://127.0.0.1:5000/api/test");
            File file1 = new File(pathImage1);
            File file2 = new File(pathImage2);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            ContentBody cbFile1 = new FileBody(file1, "image/jpeg");
            ContentBody cbFile2 = new FileBody(file2, "image/jpeg");
            builder.addPart("my_image1", cbFile1);
            builder.addPart("my_image2", cbFile2);
            HttpEntity MultipartEntity = builder.build();

            httppost.setEntity(MultipartEntity);
            System.out.println("executing request " + httppost.getRequestLine());
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity resEntity = response.getEntity();
            responseText = EntityUtils.toString(resEntity,"UTF-8");


            System.out.println(response.getStatusLine());
            System.out.println(responseText);

            httpclient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseText;
    }
}
