package pl.stackapp;

import android.widget.ImageView;
import android.widget.TextView;


public class ListRowView {
    public TextView subject ;
    public TextView author ;
    public TextView answers;
    public ImageView avatarImage;
    public void setImage(String avatarURL) {
        DownloadImageTask downloadImageTask = new DownloadImageTask(avatarImage);
        downloadImageTask.execute(avatarURL);
    }

}
