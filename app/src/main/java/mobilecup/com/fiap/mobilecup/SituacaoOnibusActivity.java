package mobilecup.com.fiap.mobilecup;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SituacaoOnibusActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao_onibus);
    }


    public void navigateNotice(View v){

    }

    private static String get(String url) throws Exception {

        InputStream content = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        HttpResponse response = httpclient.execute(get);
        content = response.getEntity().getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        StringBuilder str = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null)
            str.append(line + "\n");
        return str.toString();

    }

    private class Request extends AsyncTask<String, Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            return null;
        }
    }

}
