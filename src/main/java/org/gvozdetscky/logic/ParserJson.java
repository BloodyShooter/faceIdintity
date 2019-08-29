package org.gvozdetscky.logic;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.gvozdetscky.model.Box;
import org.gvozdetscky.model.Image;
import org.gvozdetscky.model.Result;

import java.util.Arrays;

public class ParserJson {

    public Result parseJson(String json) {
        JsonParser parser = new JsonParser();

        JsonElement jsonElement = parser.parse(json);

        JsonObject rootObject = jsonElement.getAsJsonObject();

        float evclideDistance = rootObject.get("evclide_distance").getAsFloat();

        Image image1 = parseImage(rootObject.get("image1").getAsJsonObject());
        Image image2 = parseImage(rootObject.get("image2").getAsJsonObject());

        Result result = new Result();

        result.setEvclidDeistance(evclideDistance);
        result.setImages(Arrays.asList(image1, image2));

        return result;
    }

    private Image parseImage(JsonObject imageJson) {


        JsonArray boxJson = imageJson.get("box").getAsJsonArray();

        Box box = new Box();

        box.setLeft(boxJson.get(0).getAsInt());
        box.setTop(boxJson.get(1).getAsInt());
        box.setRight(boxJson.get(2).getAsInt() - boxJson.get(0).getAsInt());
        box.setBottom(boxJson.get(3).getAsInt() - boxJson.get(1).getAsInt());

        Image image = new Image();

        image.setBox(box);

        System.out.println(box);

        return image;
    }
}
