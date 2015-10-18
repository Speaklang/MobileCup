package mobilecup.com.fiap.mobilecup.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import mobilecup.com.fiap.mobilecup.R;
import mobilecup.com.fiap.mobilecup.WS.MobileCupWS;
import mobilecup.com.fiap.mobilecup.global.UserGlobal;


public class RegisterActivity extends Activity {

    private EditText _email;
    private EditText _senha;
    private EditText _nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        _email = (EditText) findViewById(R.id.edtEmail);
        _senha = (EditText) findViewById(R.id.edtSenha);
        _nome = (EditText) findViewById(R.id.edtNome);

    }




    public void cadastrar(View v){
        if( _email.getText().toString().isEmpty() ||
                _senha.getText().toString().isEmpty()){

            Toast.makeText(getApplicationContext(),"Por favor preencha todos os campos.",
                    Toast.LENGTH_SHORT).show();
            return;
        }


        List<NameValuePair> params = new LinkedList<NameValuePair>();

        params.add(new BasicNameValuePair("acao", "cadastro"));
        params.add(new BasicNameValuePair("email", _email.getText().toString()));
        params.add(new BasicNameValuePair("nome", _nome.getText().toString()));
        params.add(new BasicNameValuePair("senha", _senha.getText().toString()));

        String url = MobileCupWS.SERVICE_MOBILE_CUP + URLEncodedUtils.format(params, "utf-8");
        Request t = new Request(v.getContext());
        t.execute(url);

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
            dialog.setTitle("Cadastrando");
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

                //Log.i("JSON", s);
                JSONObject usuario = new JSONObject(v);
                String mensagem = usuario.getString("msg");

                if(mensagem.equalsIgnoreCase("Cadastrado com sucesso.")) {

                    //Toast.makeText(getApplicationContext(),mensagem,Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent i = new Intent(RegisterActivity.this,  LoginActivity.class);
                            startActivity(i);
                        }
                    }, 3000);

                }else{
                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
