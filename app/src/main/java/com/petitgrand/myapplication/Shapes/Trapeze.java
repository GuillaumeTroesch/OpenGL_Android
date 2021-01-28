package com.petitgrand.myapplication.Shapes;

public class Trapeze extends Shape{
    private static float[] coords = {
            -1, 0.5f, 0,
            1, 0.5f, 0,
            -0.5f, -0.5f, 0,
            0.5f, -0.5f, 0,
    };

    private static short[] indices = {0, 1, 2, 1, 2, 3};

    private static float[] color = {1, 0.5f, 0.38f ,1};

    public Trapeze(float x, float y) {
        super(coords, indices, color, new float[] {x,y}, 1.1f, false);
    }
}
