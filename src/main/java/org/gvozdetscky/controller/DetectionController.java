package org.gvozdetscky.controller;

import org.gvozdetscky.model.Result;

public interface DetectionController {

    Result detection(String pathImage1, String pathImage2);
}
