package mobilecup.com.fiap.mobilecup.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import mobilecup.com.fiap.mobilecup.R;
import mobilecup.com.fiap.mobilecup.WS.MobileCupWS;
import mobilecup.com.fiap.mobilecup.global.UserGlobal;


public class LoginActivity extends Activity {

    private EditText _email;
    private EditText _senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        _email = (EditText) findViewById(R.id.edtEmail);
        _senha = (EditText) findViewById(R.id.edtSenha);


    }


    public void login(View v){

        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("acao", "login"));
        params.add(new BasicNameValuePair("email", _email.getText().toString()));
        params.add(new BasicNameValuePair("senha", _senha.getText().toString()));

        String url = MobileCupWS.SERVICE_MOBILE_CUP  + URLEncodedUtils.format(params, "utf-8");

        Request r = new Request(v.getContext());

        r.execute(url);

    }

    public void register(View v){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    private class Request extends AsyncTask<String, Void,String> {


        private ProgressDialog dialog;
        private Context context;


        public Request(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            //dialog.setTitle("Cadastrando");
            dialog.setMessage("Aguarde...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                return MobileCupWS.get(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String v) {

            try {
                JSONObject usuario = new JSONObject(v);
                String mensagem = usuario.getString("msg");

                if(mensagem.equalsIgnoreCase("OK")){
                    final UserGlobal userGlobal = (UserGlobal) getApplicationContext();
                    userGlobal.setUsuario(_email.getText().toString());
                    userGlobal.setSenha(_senha.getText().toString());

                    Intent i = new Intent(LoginActivity.this,FormularioActivity.class);
                    startActivity(i);
                    LoginActivity.this.finish();

                }else{

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }



        }
    }

}
