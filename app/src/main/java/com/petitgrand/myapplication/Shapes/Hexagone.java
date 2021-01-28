package com.petitgrand.myapplication.Shapes;

public class Hexagone extends Shape {
    private static float[] coords = {
            0, 0, 0,
            -1, 0, 0,
            -0.33f, 1, 0,
            0.33f, 1, 0,
            1, 0, 0,
            0.33f, -1, 0,
            -0.33f, -1, 0
    };

    private static short[] indices = {0, 1, 2,  0, 2, 3,  0, 3, 4,  0, 4, 5,  0, 5, 6,   0, 6, 1};

    private static float[] color = {0.8f, 0.4f, 0 ,1};

    public Hexagone(float x, float y) {
        super(coords, indices, color, new float[] {x,y}, 1, false);
    }
}
