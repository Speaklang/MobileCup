package mobilecup.com.fiap.mobilecup.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import mobilecup.com.fiap.mobilecup.R;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
