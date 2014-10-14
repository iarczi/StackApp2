package pl.stackapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aRczi on 2014-10-07.
 */
public class JSONParser2 {
    private static String  URL1 = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle=";
    private static String  URL2 = "&site=stackoverflow";
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static ListView listView;
    private static SwipeRefreshLayout swipe;
    private ListRow[] listRows;

    JSONParser2 (ListView listView, SwipeRefreshLayout swipe){
        this.listView= listView;
        this.swipe = swipe;
    }
    public void jsonParse( String question ){
        String stringUrl = URL1 + question + URL2;
        swipe.setRefreshing(true);
        client.get(stringUrl, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, JSONObject object) {
                Gson gson = new Gson();
                try {
                    String string = object.getJSONArray("items").toString();
                    listRows = gson.fromJson(string , ListRow[].class);
                    ListRowAdapter adapter ;

                    adapter = new ListRowAdapter(listView.getContext(), R.layout.list_row, listRows);
                    listView.setAdapter(adapter);
                    swipe.setRefreshing(false);
                }
                catch (JSONException e){
                    Toast.makeText(listView.getContext(), "msg msg", Toast.LENGTH_SHORT).show();
                }

            }


        });



       // int length = listRow.length;

    }

}
