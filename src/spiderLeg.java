import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.lang.String;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;

/*
this class was copied from the source that was given in the sheet.
This is the 3rd option in our list.
 */

public class spiderLeg {
    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    public void crawl(String url){
        try{
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument= ((org.jsoup.Connection) connection).get();
            this.htmlDocument= htmlDocument;
            System.out.println("Received web page at " + url);
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links" );
            for (Element link : linksOnPage){
                this.links.add(link.absUrl("href"));
            }


        }
        catch (IOException ioe){
            System.out.println("Error in out HTTP request " + ioe);
        }
    }


    public boolean searchForWord(String searchWord){
        System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }

    public int getLinksOnPage(String url){
        try{
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument= ((org.jsoup.Connection) connection).get();
            Elements linksOnPage = htmlDocument.select("a[href]");
            return linksOnPage.size();
        }
        catch (IOException ioe){
            //System.out.println("Error in out HTTP request " + ioe);
        }
        return -1;
    }
    public List<String> getLinks(){
        return this.links;
    }
}



