package pl.stackapp;

import com.google.gson.annotations.SerializedName;


public class ListRow {
    @SerializedName("title")
    public String subject ;

    @SerializedName("answer_count")
    public String answers;
    @SerializedName("link")
    public String subjectURL;

    @SerializedName("owner")
    public Owner owner;

   class Owner{
        @SerializedName("profile_image")
        public String avatarURL;
        @SerializedName("display_name")
        public String author ;

    };


}

