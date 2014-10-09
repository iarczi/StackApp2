package pl.stackapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aRczi on 2014-09-30.
 */
public class ListRow {
    @SerializedName("title")
    public String subject ;

    @SerializedName("answer_count")
    public String answers;
    @SerializedName("link")
    public String subjectURL;

    @SerializedName("owner")
    public Owner owner;



    public ListRow(){};
    public ListRow(String subject, String author, String answers, String avatarURL, String subjectURL) {
        /*this.subject = subject;
        this.author = author;
        this.answers = answers;
        this.subjectURL = subjectURL;
        this.avatarURL =avatarURL;*/

    }

}
class Owner{
    @SerializedName("profile_image")
    public String avatarURL;
    @SerializedName("display_name")
    public String author ;

};
