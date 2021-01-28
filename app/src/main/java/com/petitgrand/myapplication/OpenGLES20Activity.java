package com.petitgrand.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class OpenGLES20Activity extends Activity {

    // le conteneur View pour faire du rendu OpenGL
    private MyGLSurfaceView mGLView;
    public static final int CARTE_NONE = 0;
    public static final int CARTE_TRIANGLE = 1;
    public static final int CARTE_SQUARE = 2;
    public static final int CARTE_TRAPEZE = 3;
    public static final int CARTE_PENTAGONE = 4;
    public static final int CARTE_HEXAGONE = 5;
    public static final int CARTE_BICHE = 6;
    public static final int CARTE_OURS = 7;
    List<Integer>[] pileJoueurs;
    int cartePile;
    int cartePileTmp;
    int joueurCourant;
    int nbCartesAffilees;

    View linearLayoutButtonsParis;
    View linearLayoutButtonsChoix;
    Button boutonContinuer;
    TextView texteSuite;
    TextView[] texteCartesRestantes;
    TextView texteInfo;

    boolean pariValide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Pour le plein écran */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /* Définition de View pour cette activité */
        setContentView(R.layout.layout_main);
        mGLView = findViewById(R.id.glView);

        //Faire les piles de cartes
        List<Integer> pileTotale = new ArrayList<>();
        for (int i=0; i<8; i++)
            pileTotale.add(CARTE_TRIANGLE);
        for (int i=0; i<9; i++)
            pileTotale.add(CARTE_SQUARE);
        for (int i=0; i<9; i++)
            pileTotale.add(CARTE_TRAPEZE);
        for (int i=0; i<9; i++)
            pileTotale.add(CARTE_TRAPEZE);
        for (int i=0; i<9; i++)
            pileTotale.add(CARTE_PENTAGONE);
        for (int i=0; i<9; i++)
            pileTotale.add(CARTE_HEXAGONE);
        for (int i=0; i<9; i++)
            pileTotale.add(CARTE_BICHE);
        for (int i=0; i<8; i++)
            pileTotale.add(CARTE_OURS);
        Collections.shuffle(pileTotale);
        cartePile = pileTotale.get(61);
        cartePileTmp = cartePile;
        pileJoueurs = new List[5];
        for (int j=0; j<5; j++)
        {
            pileJoueurs[j] = new ArrayList<>();
            for (int i=0; i<12; i++)
                pileJoueurs[j].add(pileTotale.get(j*12+i));
        }

        pariValide = false;
        joueurCourant = 0;
        nbCartesAffilees = 0;

        mGLView.setCard1(CARTE_NONE);
        mGLView.setCard2(cartePile);

        linearLayoutButtonsParis = findViewById(R.id.boutonsParis);
        linearLayoutButtonsChoix = findViewById(R.id.boutonsChoix);
        boutonContinuer = findViewById(R.id.buttonContinuer);
        texteSuite = findViewById(R.id.textSuite);
        texteCartesRestantes = new TextView[5];
        texteCartesRestantes[0] = findViewById(R.id.textJ1);
        texteCartesRestantes[1] = findViewById(R.id.textJ2);
        texteCartesRestantes[2] = findViewById(R.id.textJ3);
        texteCartesRestantes[3] = findViewById(R.id.textJ4);
        texteCartesRestantes[4] = findViewById(R.id.textJ5);
        texteCartesRestantes[joueurCourant].setTextColor(Color.argb(255,0,0,255));
        texteInfo = findViewById(R.id.textInfo);

//        if (((ActivityManager)(getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().reqGlEsVersion >= 0x20000)
//            Log.d("Info", "OpenGlES 2 supported");
//        else
//            Log.d("Info", "OpenGlES 2 not supported on this device");
//
//        if (((ActivityManager)(getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().reqGlEsVersion >= 0x30000)
//            Log.d("Info", "OpenGlES 3 supported");
//        else
//            Log.d("Info", "OpenGlES 3 not supported on this device");
    }

    private int getCarteJoueur()
    { return pileJoueurs[joueurCourant].get(nbCartesAffilees); }

    private void checkPari(boolean ok)
    {
        mGLView.setCard1(pileJoueurs[joueurCourant].get(nbCartesAffilees));
        linearLayoutButtonsParis.setVisibility(View.GONE);
        linearLayoutButtonsChoix.setVisibility(View.VISIBLE);
        pariValide = ok;
        boutonContinuer.setEnabled(ok);
        if (ok)
        {
            nbCartesAffilees++;
            texteSuite.setText(""+nbCartesAffilees);
            if (nbCartesAffilees==pileJoueurs[joueurCourant].size())
            {
                texteInfo.setText("Vous êtes victorieux !");
                linearLayoutButtonsChoix.setVisibility(View.GONE);
                return;
            }
            texteInfo.setText("Bien joué !");
        }
        else
            texteInfo.setText("Retentez votre chance plus tard !");
    }

    public void ButtonMoins(View view)
    {
        checkPari(getCarteJoueur()<cartePileTmp);
    }

    public void ButtonEgal(View view)
    {
        checkPari(getCarteJoueur()==cartePileTmp);
    }

    public void ButtonPlus(View view)
    {
        checkPari(getCarteJoueur()>cartePileTmp);
    }

    public void ButtonContinuer(View view)
    {
        linearLayoutButtonsParis.setVisibility(View.VISIBLE);
        linearLayoutButtonsChoix.setVisibility(View.GONE);
        pariValide = false;
        cartePileTmp = pileJoueurs[joueurCourant].get(nbCartesAffilees-1);
        mGLView.setCard1(CARTE_NONE);
        mGLView.setCard2(cartePileTmp);
    }

    public void ButtonPasser(View view)
    {
        texteCartesRestantes[joueurCourant].setTextColor(Color.argb(230,75,75,75));
        linearLayoutButtonsParis.setVisibility(View.VISIBLE);
        linearLayoutButtonsChoix.setVisibility(View.GONE);
        if (pariValide)
        {
            cartePile = pileJoueurs[joueurCourant].get(nbCartesAffilees-1);
            for (int i=nbCartesAffilees-1; i>=0; i--)
                pileJoueurs[joueurCourant].remove(i);
        }
        cartePileTmp = cartePile;
        texteCartesRestantes[joueurCourant].setText(""+pileJoueurs[joueurCourant].size());
        joueurCourant++;
        if (joueurCourant>=5) joueurCourant=0;
        texteCartesRestantes[joueurCourant].setTextColor(Color.argb(255,0,0,255));
        nbCartesAffilees = 0;
        texteSuite.setText(""+0);
        mGLView.setCard1(CARTE_NONE);
        mGLView.setCard2(cartePile);
        pariValide = false;
        texteInfo.setText("Joueur "+(joueurCourant+1)+", pariez sur votre carte");
    }
}
