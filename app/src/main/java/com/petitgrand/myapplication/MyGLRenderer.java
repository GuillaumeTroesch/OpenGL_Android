/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.petitgrand.myapplication;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.petitgrand.myapplication.Shapes.Biche;
import com.petitgrand.myapplication.Shapes.Card;
import com.petitgrand.myapplication.Shapes.Hexagone;
import com.petitgrand.myapplication.Shapes.Ours;
import com.petitgrand.myapplication.Shapes.Pentagone;
import com.petitgrand.myapplication.Shapes.Shape;
import com.petitgrand.myapplication.Shapes.Square;
import com.petitgrand.myapplication.Shapes.Trapeze;
import com.petitgrand.myapplication.Shapes.Triangle;

/* MyGLRenderer implémente l'interface générique GLSurfaceView.Renderer */

public class MyGLRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = "MyGLRenderer";
    private Triangle triangle;
    private Square square;
    private Trapeze trapeze;
    private Pentagone pentagone;
    private Hexagone hexagone;
    private Biche biche;
    private Ours ours;

    private Card card1;
    private Card card2;
    private Shape shapeCard1;
    private Shape shapeCard2;
    private int idShapeCard1;
    private int idShapeCard2;

    // Les matrices habituelles Model/View/Projection

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];

    private float[] mSquarePosition = {0.0f, 0.0f};

    /* Première méthode équivalente à la fonction init en OpenGLSL */
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // la couleur du fond d'écran
        GLES30.glClearColor(0.9f, 0.9f, 0.9f, 1.0f);

        triangle = new Triangle(-8.5f,8.5f);
        square = new Square(-6.5f,8.5f);
        trapeze = new Trapeze(-4,8.5f);
        pentagone = new Pentagone(-1.5f ,8.5f);
        hexagone = new Hexagone(1 ,8.5f);
        biche = new Biche(3.65f, 8.5f);
        ours = new Ours(7.5f,8.5f);

        card1 = new Card(-5, 0);
        card2 = new Card(5, 0);
        shapeCard1 = new Triangle(-5, 0);
        shapeCard2 = new Triangle(5, 0);
    }

    /* Deuxième méthode équivalente à la fonction Display */
    @Override
    public void onDrawFrame(GL10 unused) {
        float[] scratch = new float[16]; // pour stocker une matrice

        // glClear rien de nouveau on vide le buffer de couleur et de profondeur */
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);

        /* on utilise une classe Matrix (similaire à glm) pour définir nos matrices P, V et M*/

        /* Pour le moment on va utiliser une projection orthographique
           donc View = Identity
         */

        /*pour positionner la caméra mais ici on n'en a pas besoin*/

        // Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.setIdentityM(mViewMatrix,0);
        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        Matrix.setIdentityM(mModelMatrix,0);
        /* Pour définir une translation on donne les paramètres de la translation
        et la matrice (ici mModelMatrix) est multipliée par la translation correspondante
         */
        Matrix.translateM(mModelMatrix, 0, mSquarePosition[0], mSquarePosition[1], 0);
        /* scratch est la matrice PxVxM finale */
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mModelMatrix, 0);

        /* on appelle la méthode dessin du carré élémentaire */
        if (idShapeCard1!=-1)
        {
            shapeCard1 = convertIntToShape(idShapeCard1, -5, 0); //Je voulais mettre ce code dans la fonction appelee par bouton,
            // mais must be called dans onSurfaceChanged(), onSurfaceCreated() ou onDrawFrame()
            idShapeCard1 = -1;
        }
        if (idShapeCard2!=-1)
        {
            shapeCard2 = convertIntToShape(idShapeCard2, 5, 0);
            idShapeCard2 = -1;
        }

        triangle.draw(scratch);
        square.draw(scratch);
        trapeze.draw(scratch);
        pentagone.draw(scratch);
        hexagone.draw(scratch);
        biche.draw(scratch);
        ours.draw(scratch);

        card1.draw(scratch);
        card2.draw(scratch);
        if (shapeCard1!=null)
            shapeCard1.draw(scratch);
        shapeCard2.draw(scratch);
    }

    /* équivalent au Reshape en OpenGLSL */
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        /* ici on aurait pu se passer de cette méthode et déclarer
        la projection qu'à la création de la surface !!
         */
        GLES30.glViewport(0, 0, width, height);
        Matrix.orthoM(mProjectionMatrix, 0, -10.0f, 10.0f, -10.0f, 10.0f, -1.0f, 1.0f);
    }

    /* La gestion des shaders ... */
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES30.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES30.GL_FRAGMENT_SHADER)
        int shader = GLES30.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);

        return shader;
    }

    public void setCard1(int card)
    { idShapeCard1 = card; }

    public void setCard2(int card)
    { idShapeCard2 = card; }

    private Shape convertIntToShape(int id_carte, float x, float y)
    {
        switch (id_carte)
        {
            case OpenGLES20Activity.CARTE_TRIANGLE:
                return new Triangle(x, y);
            case OpenGLES20Activity.CARTE_SQUARE:
                return new Square(x, y);
            case OpenGLES20Activity.CARTE_TRAPEZE:
                return new Trapeze(x, y);
            case OpenGLES20Activity.CARTE_PENTAGONE:
                return new Pentagone(x, y);
            case OpenGLES20Activity.CARTE_HEXAGONE:
                return new Hexagone(x, y);
            case OpenGLES20Activity.CARTE_BICHE:
                return new Biche(x, y);
            case OpenGLES20Activity.CARTE_OURS:
                return new Ours(x, y);
        }
        return null;
    }
}
