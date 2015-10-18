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
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;

import mobilecup.com.fiap.mobilecup.R;
import mobilecup.com.fiap.mobilecup.WS.MobileCupWS;


public class SituacaoOnibusActivity extends Activity {



    public static int QTD_LUGARES_ONIBUS = 50;
    private int qtdNoOnibus = 0;
    private TextView _txtQtdAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_situacao_onibus);

        _txtQtdAluno = (TextView)findViewById(R.id.txtQtdAlunos);

        Request r = new Request(this);
        r.execute(MobileCupWS.SERVICE_BUSCA_COM_SELETOR, MobileCupWS.BODY_REQUEST);
    }


    public void navigateNotice(View v){
        Intent i = new Intent(this, RssTabsActivity.class);
        startActivity(i);
    }

    public void update(View v){

        Request r = new Request(v.getContext());
        r.execute(MobileCupWS.SERVICE_BUSCA_COM_SELETOR, MobileCupWS.BODY_REQUEST);

    }


    private class Request extends AsyncTask<String, Void,String>{


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

            int qtdPessoas = 0;

            try {

                String json = MobileCupWS.post(params[0],params[1]);
                Log.d("JSON:", json);


                JSONObject jsonObject = new JSONObject(json);

                JSONArray docs = new JSONArray(jsonObject.get("docs").toString());

                for (int i = 0; i < docs.length(); i++) {

                    JSONObject auxDoc = docs.getJSONObject(i);

                    if(auxDoc.getInt("pres") == 1){
                        qtdPessoas ++;
                    }

                }

                int result =  QTD_LUGARES_ONIBUS - qtdPessoas;

                String vResult = String.valueOf(result);

                return vResult;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String v) {

            _txtQtdAluno.setText(v);
            dialog.dismiss();

        }
    }

}
