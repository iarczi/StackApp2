package pl.stackapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class ResultListActivity extends Activity {

    private String searchText = "";
    private ListView list ;

    private SwipeRefreshLayout swipeLayout;
    //private JSONParser jsonParser;
    private JSONParser2 jsonParser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        Intent intent = getIntent();
        list = (ListView) findViewById(R.id.listView1);

        searchText = intent.getStringExtra("searchText");

        /*jsonParser= new JSONParser(list);
        jsonParser.execute(searchText);*/

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        jsonParser = new JSONParser2(list,swipeLayout);

        jsonParser.jsonParse( searchText);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jsonParser.jsonParse( searchText);
                //jsonParser.execute(searchText);
            }
        });

        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListRow listRow = (ListRow) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), RowDetailsActivity.class);
                intent.putExtra("URL", listRow.subjectURL);
                startActivity(intent);
            }
        });

    }




}
