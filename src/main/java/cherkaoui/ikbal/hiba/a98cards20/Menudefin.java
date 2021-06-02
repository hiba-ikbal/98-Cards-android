package cherkaoui.ikbal.hiba.a98cards20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Menudefin extends AppCompatActivity {
    TextView score;
    TextView Condition;
    Ecouteur ec;
    Button menu;
    BDHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menufin);
        ec = new Ecouteur();
       score = findViewById(R.id.Score);
        Condition = findViewById(R.id.Condition);
        Bundle bundle = getIntent().getExtras();
        if (bundle.getBoolean("Condition"))
        Condition.setText("Victoire");
        else
            {
                Condition.setText("Defaite");
            }
        score.setText(String.valueOf(bundle.getInt("Score")));
        bd = BDHelper.getInstance(this);
        bd.ouvrirConnexionBD();
        Score s = new Score();
        s.score = bundle.getInt("Score");
        bd.ajouterScore(s);

        menu = findViewById(R.id.Menu);
        menu.setOnClickListener(ec);
    }

    class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v)
        {
            if (v.getId() == R.id.Menu)
            {
                Intent i = null;
                i = new Intent(Menudefin.this,menuprincipqle.class);
                finish();
                startActivity(i);
            }
        }
    }
}