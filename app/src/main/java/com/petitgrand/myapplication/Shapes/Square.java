package com.petitgrand.myapplication.Shapes;

public class Square extends Shape {
    private static float[] coords = {
            -1, 1, 0,
            -1, -1, 0,
            1,  -1, 0,
            1,  1, 0};

    private static short[] indices = { 0, 1, 2, 0, 2, 3}; // Le carré est dessiné avec 2 triangles

    private static float[] color = {1, 0.6f, 0.42f ,1};

    public Square(float x, float y) {
        super(coords, indices, color, new float[] {x,y}, 0.5f, false);
    }
}
