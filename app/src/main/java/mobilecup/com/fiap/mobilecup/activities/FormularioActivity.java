package mobilecup.com.fiap.mobilecup.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.Toast;

import com.parse.ParseFacebookUtils;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import mobilecup.com.fiap.mobilecup.R;
import mobilecup.com.fiap.mobilecup.WS.MobileCupWS;
import mobilecup.com.fiap.mobilecup.global.UserGlobal;


public class FormularioActivity extends Activity {


    private CheckBox _chkEsporte;
    private CheckBox _chkDinheiro;
    private CheckBox _chkPolitica;
    private CheckBox _chkMundo;
    private CheckBox _chkPessoas;
    private CheckBox _chkEstiloVida;
    private List<Integer> idInteresse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_formulario);

        _chkEsporte = (CheckBox) findViewById(R.id.chkEsportes);
        _chkDinheiro = (CheckBox) findViewById(R.id.chkDinheiro);
        _chkPolitica = (CheckBox) findViewById(R.id.chkPolitica);
        _chkMundo = (CheckBox) findViewById(R.id.chkMundo);
        _chkPessoas = (CheckBox) findViewById(R.id.chkPessoas);
        _chkEstiloVida = (CheckBox) findViewById(R.id.txtEstiloVida);

    }

    public void enviar(View v) {
        try{

            idInteresse = new ArrayList<>();


            if(_chkEsporte.isChecked()){
                idInteresse.add(1);
            }
            if(_chkDinheiro.isChecked()){
                idInteresse.add(2);
            }

            if(_chkPolitica.isChecked()){
                idInteresse.add(3);
            }

            if(_chkMundo.isChecked()){
                idInteresse.add(4);
            }

            if(_chkPessoas.isChecked()){
                idInteresse.add(5);
            }

            if(_chkEstiloVida.isChecked()){
                idInteresse.add(6);
            }

            final UserGlobal user = (UserGlobal) getApplicationContext();

            List<NameValuePair> params = new LinkedList<NameValuePair>();
            params.add(new BasicNameValuePair("acao", "cadastro"));
            params.add(new BasicNameValuePair("email", user.getUsuario()));

            String ids = "";
            for(int id : idInteresse){
                ids += id + ";";
            }

            params.add(new BasicNameValuePair("interesses", ids));

            Request r = new Request(v.getContext());

            String url =  MobileCupWS.SERVICE_USUARIO_INTERESSE
                    + URLEncodedUtils.format(params, "utf-8");

            r.execute(url);


        }catch (Exception e){
            Log.d("Erro:",e.getMessage());
        }

    }

    public void responderDepois(View v) {
        Intent i = new Intent(this, SituacaoOnibusActivity.class);
        startActivity(i);
        this.finish();
    }

    private class Request extends AsyncTask<String, Void, String> {

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
                JSONObject result = new JSONObject(v);
                String mensagem = result.getString("msg");

                if(mensagem.equalsIgnoreCase("OK")){

                    Intent i = new Intent(FormularioActivity.this,SituacaoOnibusActivity.class);
                    startActivity(i);
                    FormularioActivity.this.finish();

                }else{

                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
