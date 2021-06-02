package cherkaoui.ikbal.hiba.a98cards20;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout LinearLayout,TopMenu,Smenu,Layout1,Layout2,Range1,Range2,Ltop,Lbottom;

    Chronometer chronometer;
    Button btnSet,btnMenu;

    TextView TcarteRes,TcarteSc;
    TextView Tcarte1,Tcarte2,Tcarte3,Tcarte4,Tcarte5,Tcarte6,Tcarte7,Tcarte8;
    TextView Tpile1,Tpile2,Tpile3,Tpile4;

    Ecouteur ec;
    EcouteurDrag ed;
    EcouteurTouch et;

    Jeu jeu;

    //List<Integer> ListOfCards = new ArrayList<>();
    int UserScore;
    int tempCarte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chrono);
        chronometer.start();
        chronometer.setFormat("Duree : %s");

        btnMenu = findViewById(R.id.menubtn);
        //btnSet = findViewById(R.id.btnSoumettre);

        TcarteRes = findViewById(R.id.cartesrestantes);
        TcarteSc = findViewById(R.id.score);
        ec = new Ecouteur();
        btnMenu.setOnClickListener(ec);
       // btnSet.setOnClickListener(ec);

        jeu=new Jeu();

        ed = new EcouteurDrag();
        et = new EcouteurTouch();

        Tcarte1 = findViewById(R.id.titrecarte1);
        Tcarte2 = findViewById(R.id.titrecarte2);
        Tcarte3 = findViewById(R.id.titrecarte3);
        Tcarte4 = findViewById(R.id.titrecarte4);
        Tcarte5 = findViewById(R.id.titrecarte5);
        Tcarte6 = findViewById(R.id.titrecarte6);
        Tcarte7 = findViewById(R.id.titrecarte7);
        Tcarte8 = findViewById(R.id.titrecarte8);

        Tpile1 = findViewById(R.id.Carte98_1);
        Tpile2 = findViewById(R.id.carte98_2);
        Tpile3 = findViewById(R.id.carte0_1);
        Tpile4 = findViewById(R.id.carte0_2);

        LinearLayout = findViewById(R.id.LinearLayout);
        TopMenu = findViewById(R.id.topm);
        Smenu = findViewById(R.id.sousmenu);
        Layout1 = findViewById(R.id.Llayout1);
        Layout2 = findViewById(R.id.Llayout2);
        Range1 = findViewById(R.id.range1);
        Range2 = findViewById(R.id.range2);
        Ltop = findViewById(R.id.LayoutTop);
        Lbottom = findViewById(R.id.LayoutBottom);



        // pour ajouter les ecouteurs aux linear layouts et pour set les texts de debut du jeu
        for (int i =0; i < Layout1.getChildCount();i++){
            Layout1.getChildAt(i).setOnDragListener(ed);

        }
        for (int i =0; i <Layout2.getChildCount();i++){
            Layout2.getChildAt(i).setOnDragListener(ed);
        }
        for (int i =0; i < Range1.getChildCount();i++){
            Range1.getChildAt(i).setOnTouchListener(et);
        }
        for (int i =0; i < Range2.getChildCount();i++){
            Range2.getChildAt(i).setOnTouchListener(et);
        }

        Tcarte1.setText(String.valueOf(jeu.setcart()));
        Tcarte2.setText(String.valueOf(jeu.setcart()));
        Tcarte3.setText(String.valueOf(jeu.setcart()));
        Tcarte4.setText(String.valueOf(jeu.setcart()));
        Tcarte5.setText(String.valueOf(jeu.setcart()));
        Tcarte6.setText(String.valueOf(jeu.setcart()));
        Tcarte7.setText(String.valueOf(jeu.setcart()));
        Tcarte8.setText(String.valueOf(jeu.setcart()));


    }


    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {


        }
    }

    private class EcouteurDrag implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            View carte = (View) event.getLocalState(); // avoir la carte invisible
            ConstraintLayout consCarte = (ConstraintLayout)v;

            if(event.getAction() == DragEvent.ACTION_DRAG_ENDED){

                consCarte.setBackground(
                        (getDrawable(R.drawable.blank_munchkin_door_card_template_by_cornixt_d5cwcin)));
                carte.setVisibility(View.VISIBLE);

            }
            else if(event.getAction() == DragEvent.ACTION_DRAG_ENTERED){

                consCarte.setBackground(
                        (getDrawable(R.drawable.blank_munchkin_treasure_card_template_by_cornixt_d5cwco2)));
            }
            else if(event.getAction() == DragEvent.ACTION_DRAG_EXITED){
                consCarte.setBackground(
                        (getDrawable(R.drawable.blank_munchkin_door_card_template_by_cornixt_d5cwcin)));

            }
            else if(event.getAction() ==  DragEvent.ACTION_DROP){



                String textPile =((TextView)consCarte.getChildAt(0)).getText().toString();
                int valeurPile = Integer.parseInt(textPile);

                System.out.println( GetPile(consCarte.getId()));

                ConstraintLayout constraitCrate = (ConstraintLayout)carte;
                String texteCarte = ((TextView)constraitCrate.getChildAt(0)).getText().toString();
                int valeurCarte = Integer.parseInt(texteCarte);

                if (GetPile(consCarte.getId()) == 1 || GetPile(consCarte.getId()) == 2) // si c'est les piles du haut
                {

                    if(jeu.infsup(true,valeurPile,valeurCarte)) // ont compare la valeur de la pile avec la carte
                    {
                        //ont change le texte de la pile
                        ((TextView)consCarte.getChildAt(0)).setText(String.valueOf(valeurCarte));

                        // ont change la valeur de la carte
                        ((TextView)constraitCrate.getChildAt(0)).setText(String.valueOf(jeu.setcart()));
                        // ont enleve 1 a nbcart
                        jeu.nbCarte--;
                        UserScore +=100;
                    }
                }
                if (GetPile(consCarte.getId()) == 3 || GetPile(consCarte.getId()) == 4)
                {

                    if(jeu.infsup(false,valeurPile,valeurCarte))
                    {
                        //ont change le texte de la pile
                        ((TextView)consCarte.getChildAt(0)).setText(String.valueOf(valeurCarte));

                        // ont change la valeur de la carte
                        ((TextView)constraitCrate.getChildAt(0)).setText(String.valueOf(jeu.setcart()));
                        // ont enleve 1 a nbcart
                        jeu.nbCarte--;
                        UserScore +=100;
                    }
                }
                TcarteRes.setText("Cartes restantes : " + jeu.nbCarte);
                // on mais a jour le score
                TcarteSc.setText("Score : " +UserScore );

                if (jeu.nbCarte == 0)
                {
                    // tu a gagner
                    chronometer.stop();
                    Intent i = null;
                    i = new Intent(MainActivity.this,Menudefin.class);
                    i.putExtra("Score", UserScore);
                    i.putExtra("Condition", true);
                    finish();
                    startActivity(i);
                }

                else if (jeu.checkdefaite())
                {
                    chronometer.stop();
                    Intent i = null;
                    i = new Intent(MainActivity.this,Menudefin.class);
                    i.putExtra("Score", UserScore);
                    i.putExtra("Condition", false);
                    finish();
                    startActivity(i);
                }



            }

            return true;
        }


    int GetPile (int id)
    {
        if (id == R.id.pile1)
            return 1;
        else if (id == R.id.pile2)
            return 2;
        else if (id == R.id.pile3)
            return 3;
        else if (id == R.id.pile4)
            return 4;
        else
            return 0;

    }


    }

    private class EcouteurTouch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(null,shadowBuilder,v,0);
                v.setVisibility(View.INVISIBLE);
            }

            return true;
        }


    }
}




