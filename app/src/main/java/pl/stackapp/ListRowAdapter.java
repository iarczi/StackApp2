package pl.stackapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ListRowAdapter extends ArrayAdapter<ListRow> {

    Context context;
    int layoutResourceId;
    List<ListRow> data = null;

    public ListRowAdapter(Context context, int layoutResourceId, List<ListRow> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ListRowView rowView = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            rowView = new ListRowView();
            rowView.avatarImage = (ImageView)row.findViewById(R.id.avatarImage);
            rowView.author = (TextView)row.findViewById(R.id.authorText);
            rowView.answers = (TextView)row.findViewById(R.id.answersText);
            rowView.subject = (TextView)row.findViewById(R.id.subjectText);

            row.setTag(rowView);
        }
        else
        {
            rowView = (ListRowView)row.getTag();
        }

        ListRow object = data.get(position);

        rowView.setImage(object.avatarURL);
        rowView.author.setText(object.author);
        rowView.answers.setText(object.answers);
        rowView.subject.setText(object.subject);
        return row;
    }

    public ListRow getItem(int position){

        return data.get(position);
    }

}


