package mobilecup.com.fiap.mobilecup.WS;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 17/10/2015.
 */
public class MobileCupWS {

    public final static String SERVICE_BUSCA_COM_SELETOR =
            "http://6e53a06b-7e51-41ab-828a-6e3df4e56186-bluemix.cloudant.com/" +
                    "fiap_arduino_db/_find";


    public final static String BODY_REQUEST = "{  \"selector\": {\"devCode\" : {\"$eq\": \"1\"},\"timestamp\": " +
            "{\"$gt\": 0 }},\"sort\": [{\"timestamp\": \"desc\"}  ],  \"limit\": 50 }";


    public static String get(String url) throws Exception {

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


    public static String post(String url,String body) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        //List<NameValuePair> nvList = new ArrayList<NameValuePair>();
        //BasicNameValuePair bnvp = new BasicNameValuePair("body", body);
        //nvList.add(bnvp);
        StringEntity se = new StringEntity(body.toString());
        post.setEntity(se);
        HttpResponse resp = client.execute(post);
        InputStream content  = resp.getEntity().getContent();


        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        StringBuilder str = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null)
            str.append(line + "\n");
        return str.toString();

    }

}
