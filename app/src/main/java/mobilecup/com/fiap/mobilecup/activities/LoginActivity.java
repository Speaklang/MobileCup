package mobilecup.com.fiap.mobilecup.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import mobilecup.com.fiap.mobilecup.R;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void login(View v){
        Intent i = new Intent(this,FormularioActivity.class);
        startActivity(i);
        this.finish();

    }

    public void register(View v){
        //Intent i = new Intent(this,FormularioActivity.class);
        //startActivity(i);
        //this.finish();

    }

}
