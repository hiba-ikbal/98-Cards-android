package cherkaoui.ikbal.hiba.a98cards20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuprincipqle extends AppCompatActivity {
Button jouer;
Ecouteur ec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipqle);
        jouer = findViewById(R.id.btnJouer);
        ec = new Ecouteur();
        jouer.setOnClickListener(ec);
    }
    class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
        if (v.getId() == R.id.btnJouer)
        {

            Intent i = null;
            i = new Intent(menuprincipqle.this,MainActivity.class);
            finish();
            startActivity(i);

        }
        }
    }
}