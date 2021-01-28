package com.petitgrand.myapplication.Shapes;

public class Biche extends Shape{
    private static float[] coords = new float[]{
        0, 0, 0, //0
        0, -0.8f, 0,
        -0.2f, -0.7f, 0,
        -0.3f, -0.4f, 0,
        -0.6f, -0.1f, 0,
        -0.65f, 0.3f, 0, //5
        -0.4f, 0.5f, 0,
        0.4f, 0.5f, 0,
        0.65f, 0.3f, 0,
        0.6f, -0.1f, 0,
        0.3f, -0.4f, 0, //10
        0.2f, -0.7f, 0,
        -1, 1, 0, //12
        -0.8f, 0.3f,0,
        1, 1, 0, //14
        0.8f, 0.3f,0,
    };

    private static short[] indices = {
        0, 1, 2,  0, 2, 3,  0, 3, 4,  0, 4, 5,
        0, 5, 6,  0, 6, 7,  0, 7, 8, 0, 8, 9,
        0, 9, 10,  0, 10, 11,  0, 11, 1,
        12, 5, 6,   12, 13, 5,
        14, 7, 8,   14, 15, 8
    };

    private static float[] color = {0.6f, 0.3f, 0 ,1};

    public Biche(float x, float y) {
        super(coords, indices, color, new float[] {x,y}, 1.8f, false);
    }
}
