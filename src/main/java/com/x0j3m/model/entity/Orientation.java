package com.x0j3m.model.entity;

import java.util.List;

public enum Orientation {
    LEFT,
    RIGHT,
    CENTER;

    public static List<Orientation> getOrientations() {
        return List.of(Orientation.values());
    }
}
