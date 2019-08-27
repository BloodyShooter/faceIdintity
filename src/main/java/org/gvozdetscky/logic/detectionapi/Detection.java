package org.gvozdetscky.logic.detectionapi;

import org.gvozdetscky.logic.CmdClass;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Detection {


    public String faceIdintity(String pathImage1, String pathImage2) {
        CmdClass cmd = new CmdClass();

        String out = cmd.run("C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\venv\\Scripts\\python.exe " +
                          "C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\script\\face_idintity.py " +
                pathImage1 + " " + pathImage2);

        return out;
    }

    public String url(String pathImage1, String pathImage2) {

        System.out.println("1");

        try {
            URL serverUrl =
                    new URL("http://localhost:5000/api/test");

            HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();

            File image1 = new File(pathImage1);
            File image2 = new File(pathImage2);

            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Content-Type", "multipart/form-data;");

            OutputStream outputStreamToRequestBody = urlConnection.getOutputStream();
            BufferedWriter httpRequestBodyWriter =
                    new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));

            httpRequestBodyWriter.write("Content-Disposition: form-data;"
                    + "name=\"my_image1\";");
            httpRequestBodyWriter.flush();

            FileInputStream inputStreamToImageFile1 = new FileInputStream(image1);
            FileInputStream inputStreamToImageFile2 = new FileInputStream(image2);

            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while((bytesRead = inputStreamToImageFile1.read(dataBuffer)) != -1) {
                outputStreamToRequestBody.write(dataBuffer, 0, bytesRead);
            }

            httpRequestBodyWriter.write("name=\"my_image2\";");
            httpRequestBodyWriter.flush();

            while((bytesRead = inputStreamToImageFile1.read(dataBuffer)) != -1) {
                outputStreamToRequestBody.write(dataBuffer, 0, bytesRead);
            }

            outputStreamToRequestBody.flush();

            outputStreamToRequestBody.close();
            httpRequestBodyWriter.close();

            System.out.println(urlConnection.getResponseCode());

            BufferedReader httpResponseReader =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String lineRead;
            while((lineRead = httpResponseReader.readLine()) != null) {
                System.out.println(lineRead);
            }

            httpResponseReader.close();

            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
