package pl.stackapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;


public class JSONParser extends AsyncTask<String, String, JSONObject> {
    private static String  URL1 = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle=";
    private static String  URL2 = "&site=stackoverflow";

    private static String  TAG_ITEMS = "items";
    private static String  TAG_OWNER = "owner";
    private static String  TAG_DISPLAY_NAME = "display_name";
    private static String  TAG_PROFILE_IMAGE = "profile_image";
    private static String  TAG_ANSWER_COUNT = "answer_count";
    private static String  TAG_LINK = "link";
    private static String  TAG_TITLE = "title";

    private static ListView listView;

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONParser(ListView listView) {
        this.listView = listView;
    }

    @Override
    protected JSONObject doInBackground(String... args) {
           String searchText = args[0];
           String stringUrl = URL1 + searchText + URL2;

       // InputStream source = retrieveStream(url);
        JSONObject json = getJSONFromUrl(stringUrl);
        return json;

    }
    @Override
    protected void onPostExecute(JSONObject json) {

        try {

            ArrayList<ListRow> listRowList = new ArrayList() ;
            JSONArray items  = json.getJSONArray(TAG_ITEMS);
            for (int i=0;i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                String link = item.getString(TAG_LINK);
                String answer_count = item.getString(TAG_ANSWER_COUNT);
                String title = item.getString(TAG_TITLE);
                JSONObject owner = item.getJSONObject(TAG_OWNER);
                String display_name = owner.getString(TAG_DISPLAY_NAME);
                String profile_image = owner.getString(TAG_PROFILE_IMAGE);

                ListRow listRow = new ListRow(title, display_name,answer_count, profile_image, link);
                listRowList.add(listRow);
                ListRowAdapter adapter ;

                /*adapter = new ListRowAdapter(listView.getContext(), R.layout.list_row, listRowList);
                listView.setAdapter(adapter);*/

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONObject getJSONFromUrl(String url) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(is)));;
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;
    }
}
