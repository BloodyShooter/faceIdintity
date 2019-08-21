package org.gvozdetscky.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdClass {

    public String run(String command) {

        try {

            Process proc = Runtime.getRuntime().exec(command);

            StringBuilder output = new StringBuilder();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));



            String line;

            while ((line = bufferedReader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitVal = proc.waitFor();

            if (exitVal == 0) {
                System.out.println("Команда выполнена");
                System.out.println(output);
            } else {
                System.out.println("Ошибка!!!!!");
            }


            return output.toString();


        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
