package com.petitgrand.myapplication.Shapes;

public class Card extends Shape{
    private static float[] cord = {
            -1, 1, 0,
            -1, -1, 0,
            1,  -1, 0,
            1,  1, 0};

    private static short[] indices = { 0, 1, 2, 3};

    private static float[] color = {0, 0.5f, 0.1f ,1};

    public Card(float x, float y) {
        super(cord, indices, color, new float[] {x,y}, 4.5f, true);
    }
}
