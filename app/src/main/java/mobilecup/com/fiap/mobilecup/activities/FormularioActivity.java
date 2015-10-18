package mobilecup.com.fiap.mobilecup.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import mobilecup.com.fiap.mobilecup.R;


public class FormularioActivity extends ActionBarActivity {

    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        String cursos[] = {"Analise e desenvolvimento de sistemas","Engenharia","Letras","Sistemas para internet","Redes de computadores"};

        sp = (Spinner) findViewById(R.id.spFormacaoAcademica);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, cursos);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp.setAdapter(spinnerArrayAdapter);
    }

    public void enviar(View v){
        Intent i = new Intent(this,SituacaoOnibusActivity.class);
        startActivity(i);
        this.finish();
    }

    public void responderDepois(View v){
        Intent i = new Intent(this,SituacaoOnibusActivity.class);
        startActivity(i);
        this.finish();
    }

}
