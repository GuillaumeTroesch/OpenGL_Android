package com.petitgrand.myapplication.Shapes;

import android.opengl.GLES20;
import android.opengl.GLES30;

import com.petitgrand.myapplication.MyGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Arrays;

abstract public class Shape {
    final int COORDS_PER_VERTEX = 3; // nombre de coordonnées par vertex

    private final FloatBuffer vertexBuffer; // Pour le buffer des coordonnées des sommets du carré
    private final ShortBuffer indiceBuffer; // Pour le buffer des indices

    // #version 300 es does not do anything, maybe not supported, can't find error, and application is launched but no result

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private final String vertexShaderCode =
                    "uniform mat4 uMVPMatrix;\n"+
                    "attribute vec3 vPosition;\n" +
                    "attribute vec4 vCouleur;\n"+
                    "varying vec4 Couleur;\n"+
                    "varying vec3 Position;\n"+
                    "void main() {\n" +
                    "Position = vPosition;\n"+
                    "gl_Position = uMVPMatrix * vec4(vPosition,1.0);\n" +
                    ""+
                    "}\n";

    private final int idProgram;

    private float[] pos;
    private float[] coords;
    private short[] indices;
    private float[] color; //Couleur simple pour toute la figure

    private final int vertexStride = COORDS_PER_VERTEX * 4; // le pas entre 2 sommets : 4 bytes per vertex

    private int method;

    private int []linkStatus = {0};

    public Shape(float[] coord, short[] indices, float[] color, float[] pos, float taille, boolean empty)
    {
        this.pos = pos;
        this.color = color;
        this.indices = indices;
        coords = new float[coord.length];
        for (int i=0; i<coord.length; i++) {
            coords[i] = taille*coord[i]; //Set taille
            //Set position
            if (i % 3 == 0)
                coords[i] +=  pos[0];
            else if (i % 3 == 1)
                coords[i] +=  pos[1];
        }
        method = empty ? GLES30.GL_LINE_LOOP : GLES30.GL_TRIANGLES;

        /* Chargement des shaders */
        int vertexShader = MyGLRenderer.loadShader(
                GLES30.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(
                GLES30.GL_FRAGMENT_SHADER,
                fragmentShaderCode);
        idProgram = GLES30.glCreateProgram();             // create empty OpenGL Program
        GLES30.glAttachShader(idProgram, vertexShader);   // add the vertex shader to program
        GLES30.glAttachShader(idProgram, fragmentShader); // add the fragment shader to program
        GLES30.glLinkProgram(idProgram);                  // create OpenGL program executables
        GLES30.glGetProgramiv(idProgram, GLES30.GL_LINK_STATUS,linkStatus,0);

        // initialisation du buffer pour les vertex (4 bytes par float)
        ByteBuffer bb = ByteBuffer.allocateDirect(coords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(coords);
        vertexBuffer.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        indiceBuffer = dlb.asShortBuffer();
        indiceBuffer.put(indices);
        indiceBuffer.position(0);
    }

    public float[] getPosition() {
        return coords;
    }

    public void draw(float[] mvpMatrix) {
        // Add program to OpenGL environment
        GLES20.glUseProgram(idProgram);

        // get handle to shape's transformation matrix
        int IdMVPMatrix = GLES30.glGetUniformLocation(idProgram, "uMVPMatrix");

        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(IdMVPMatrix, 1, false, mvpMatrix, 0);

        // get handle to vertex shader's vPosition member et vCouleur member
        int idPosition = GLES30.glGetAttribLocation(idProgram, "vPosition");
        int idCouleur = GLES30.glGetAttribLocation(idProgram, "vCouleur");

        /* Activation des Buffers */
        GLES30.glEnableVertexAttribArray(idPosition);
        GLES30.glEnableVertexAttribArray(idCouleur);

        /* Lecture des Buffers */
        GLES30.glVertexAttribPointer(
                idPosition, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // initialisation du buffer pour les couleurs (4 bytes par float)
        int colorUniform = GLES20.glGetUniformLocation(idProgram, "vColor");
        GLES20.glUniform4fv(colorUniform, 1, color, 0);

        // Draw the square
        GLES30.glDrawElements(
                method, indices.length,
                GLES30.GL_UNSIGNED_SHORT, indiceBuffer);

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(idPosition);
        GLES30.glDisableVertexAttribArray(idCouleur);
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "pos=" + Arrays.toString(pos) +
                '}';
    }
}
