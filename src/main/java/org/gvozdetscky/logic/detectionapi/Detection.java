package org.gvozdetscky.logic.detectionapi;

import org.gvozdetscky.logic.CmdClass;

public class Detection {


    public void faceIdintity(String pathImage1, String pathImage2) {
        CmdClass cmd = new CmdClass();

        cmd.run("C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\venv\\Scripts\\python.exe C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\script\\face_idintity.py");
    }
}
