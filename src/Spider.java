
import java.util.*;
/*
this class was copied from the source that was given in the sheet.
This is the 3rd option in our list.
 */
public class Spider {
    private static final int MAX_PAGES_TO_SEARCH = 30;
    private Set<String> pageVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();
    private int counter = 0;
    private static final int MAX_PAGES_WITH_KEYWORD = 10;
    private List<String> listOfUrls = new ArrayList<String>();

    public List<String> search(String url , String searchWord ) {
        while (this.pageVisited.size() < MAX_PAGES_TO_SEARCH) {
            String currentUrl;
            spiderLeg leg = new spiderLeg();
            if (this.pagesToVisit.isEmpty()) {
                currentUrl = url;
                this.pageVisited.add(url);
            } else {
                currentUrl = this.nextUrl();
            }
            leg.crawl(currentUrl);
            boolean success = leg.searchForWord(searchWord);
            if (success) {
                System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                listOfUrls.add(currentUrl);
                counter++;
                if (counter == MAX_PAGES_WITH_KEYWORD) {
                    counter = 0;
                    return listOfUrls;
                }
            }
            this.pagesToVisit.addAll(leg.getLinks());
        }
        System.out.println(String.format("**Done** Visited %s web page(s)", this.pageVisited.size()));
        return  listOfUrls;
    }
    private String nextUrl(){
        String nextUrl;
        do {
            nextUrl = this.pagesToVisit.remove(0);
        }
        while(this.pagesToVisit.contains(nextUrl));
        this.pagesToVisit.add(nextUrl);
        return nextUrl;
    }
}


