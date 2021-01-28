package com.petitgrand.myapplication.Shapes;

import com.petitgrand.myapplication.Shapes.Shape;

import java.nio.FloatBuffer;

public class Triangle extends Shape {
    private FloatBuffer vertexBuffer;

    private static float[] coords = {   // in counterclockwise order:
            0.0f,  0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    };

    private static short[] indices = { 0, 1, 2}; //Un simple triangle

    private static float[] color = { 1, 0.7843f, 0.5686f, 1.0f };

    public Triangle(float x, float y) {
        super(coords, indices, color, new float[] {x,y},1, false);
    }
}
