package cherkaoui.ikbal.hiba.a98cards20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Jeu {
    //Vector<Integer> listOfCards ;
    int nbCarte, index;

    // public Jeu(){}
    ArrayList<Integer> listOfCards ;
   // Vector<Integer>  CarteUtiliser; // checker les index pour prendre pour la nouvelle carte un autre index pas utiliser
    public Jeu(){
        listOfCards = new ArrayList<Integer>();
        nbCarte = 97;
        InitialisationCards(listOfCards);
        index=0;


    }
    // Le paquet de carte pour  commencer
    public void InitialisationCards(ArrayList<Integer> listOfCards) {

        for (int i = 1; i <= nbCarte; i++) { // remplie le tableau de carte
            this.listOfCards.add(i);
        }
        Collections.shuffle(this.listOfCards); // randomise le tableau de carte
        //listOfCards.get(0) =carte1
        // qyabd deposer carte +1 sur la carte modifier mais doit pas etre le meme index que les  autres
    }

    public int setcart (){
        index++;
        return listOfCards.get(index);
    }

    public boolean infsup(boolean signe,int val1,int val2){
        if (signe)
        {
            if(val1 > val2)
                return true;
            else
                return false;
        }
        else
            {
                if(val1 < val2)
                return true;
            else
                return false;

             }
    }

    public boolean checkdefaite ()
    {

        return false;
    }

}
