package com.petitgrand.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer renderer;

    public MyGLSurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // Création d'un context OpenGLES 2.0
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);

        // Création du renderer qui va être lié au conteneur View créé
        renderer = new MyGLRenderer();
        setRenderer(renderer);

        // Option pour indiquer qu'on redessine uniquement si les données changent
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        //
//        return true;
//    }

    public void setCard1(int card)
    {
        renderer.setCard1(card);
        requestRender();
    }

    public void setCard2(int card)
    {
        renderer.setCard2(card);
        requestRender();
    }
}
