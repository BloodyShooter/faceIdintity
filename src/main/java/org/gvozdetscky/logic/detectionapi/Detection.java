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

    private static final String URL_SERVER = "http://127.0.0.1:5000/api/test";
    private static final String PATH_TO_PYTHON = "C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\venv\\Scripts\\python.exe ";
    private static final String PATH_TO_SCRYPT = "C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\script\\face_idintity.py ";

    public String faceIdintity(String pathImage1, String pathImage2) {
        CmdClass cmd = new CmdClass();

        String executeCmdFaceIdintity = PATH_TO_PYTHON + PATH_TO_SCRYPT + pathImage1 + " " + pathImage2;

        return cmd.run(executeCmdFaceIdintity);
    }

    public String faceIdintityUrl(String pathImage1, String pathImage2) {

        String responseText = "";

        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();

            HttpPost httppost = new HttpPost(URL_SERVER);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            ContentBody cbFile1 = new FileBody(new File(pathImage1));
            ContentBody cbFile2 = new FileBody(new File(pathImage2));
            builder.addPart("my_image1", cbFile1);
            builder.addPart("my_image2", cbFile2);
            HttpEntity MultipartEntity = builder.build();

            httppost.setEntity(MultipartEntity);
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity resEntity = response.getEntity();
            responseText = EntityUtils.toString(resEntity,"UTF-8");


            httpclient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseText;
    }
}
