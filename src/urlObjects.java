import java.util.List;

/*
this is the data structure we are using to hold
each url with its correspondent fields.
 */
public class urlObjects {

    public int totalScore ; //to hold total score of each url
    public int pageRank;    //to hold page rank of each url
    public String url;      //to hold the actual url
    public int index;       //to hold the original index of the url when it was created
    //Constructor
    urlObjects(int totalScore, String url, int index, int pageRank) {
        this.totalScore = totalScore;
        this.url = url;
        this.index = index;
        this.pageRank= pageRank;
    }



}