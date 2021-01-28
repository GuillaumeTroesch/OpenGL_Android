package com.petitgrand.myapplication.Shapes;

public class Pentagone extends Shape {
    private static float[] coords = {
        0, 0, 0,
       -0.7f, -1.0f, 0,
        -1.0f, 0.3f, 0,
        0, 1.0f, 0,
        1, 0.3f, 0,
        0.7f, -1, 0
    };

    private static short[] indices = {0, 1, 2,  0, 2, 3,  0, 3, 4,  0, 4, 5,  0, 5, 1};

    private static float[] color = {1, 0.4f, 0.3f ,1};

    public Pentagone(float x, float y) {
        super(coords, indices, color, new float[] {x,y}, 0.8f, false);
    }
}
