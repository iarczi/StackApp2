package pl.stackapp;

/**
 * Created by aRczi on 2014-09-30.
 */
public class ListRow {
    public String subject ;
    public String author ;
    public String answers;
    public String avatarURL;
    public String subjectURL;

    public ListRow(String subject, String author, String answers, String avatarURL, String subjectURL) {
        this.subject = subject;
        this.author = author;
        this.answers = answers;
        this.subjectURL = subjectURL;
        this.avatarURL =avatarURL;

    }
}
