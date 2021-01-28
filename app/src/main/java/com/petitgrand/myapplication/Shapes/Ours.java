package com.petitgrand.myapplication.Shapes;

import com.petitgrand.myapplication.Shapes.Shape;

public class Ours extends Shape {
    private static float[] coords = {
            0, 0, 0, //0
            -1, 0, 0, //1
            -0.7f, 0.5f, 0,
            -0.5f, 0.7f,0,
            0.5f, 0.7f, 0,
            0.7f, 0.5f, 0, //5
            1, 0, 0,
            0.7f, -0.4f, 0,
            0, -0.7f, 0,
            -0.7f, -0.4f, 0, //9
            -0.9f, 0.8f, 0,
            0.9f, 0.8f, 0
             };

    private static short[] indices = {
            0, 1, 2,
            0, 2, 3,
            0, 3, 4,
            0, 4, 5,
            0, 5, 6,
            0, 6, 7,
            0, 7, 8,
            0, 8, 9,
            0, 9, 1,
            10, 2, 3,
            11, 4, 5};

    private static float[] color = {0.4f, 0.2f, 0 ,1};

    public Ours(float x, float y) {
        super(coords, indices, color, new float[] {x,y}, 2, false);
    }
}
