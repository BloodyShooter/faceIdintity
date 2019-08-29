package org.gvozdetscky.model;

import java.util.List;

public class Result {

    private List<Image> images;

    private float evclidDeistance;

    public float getEvclidDeistance() {
        return evclidDeistance;
    }

    public void setEvclidDeistance(float evclidDeistance) {
        this.evclidDeistance = evclidDeistance;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
