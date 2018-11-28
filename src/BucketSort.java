import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/*
This class is the implementation of the Bucket sort. This is used to
sort the url objects in alphabetical order.
 */
public class BucketSort {

    //main function to perform the bucket sort
    void bucketSort(List<urlObjects> url) {
        int n = url.size(); //to hold the size of the list
        List<List<urlObjects>> B = new ArrayList<>();   //this is the bucket list of lists
        for (int i = 0; i < 27; i++) {
            List<urlObjects> list = new ArrayList<>(); //initializing the empty lists
            B.add(list);
        }

        //in this for loop the urls are added to the right bucket
        for (int i = 0; i < n; i++) {
            String tempUrl = url.get(i).url;    //getting the url
            String [] arrStr = tempUrl.split(Pattern.quote("//")); //parsing it to get the first word of the url
            int index = arrStr[1].charAt(0) - 'a';  //this is to hold the index of the url based on the first character
            B.get(index).add(url.get(i));           //this is adding it to the list of lists
        }

        //calling insertion sort on each bucket
        for (int i = 0; i < 27; i++) {
            insertionSort(B.get(i));
        }

        url.clear();

        //stiching the buckets in order back together in a list
        for (int i = 0 ; i < B.size(); i++){
            for (int j = B.get(i).size()-1; j >=0; j--){
                url.add(B.get(i).get(j));
            }
        }
    }

    //This function get the first word of the url for insertion sort
    public static String getName(String url){
        String [] tempArr = url.split(Pattern.quote("//"));
        return tempArr[1];
    }

    //This is the implementation of insertion sort
    public static void insertionSort(List<urlObjects> url) {
        //insertion sort
        for (int j = 1; j < url.size(); j++) {
            String temp = url.get(j).url;
            int i = j - 1;
            urlObjects node = url.get(j);
            String key = getName(temp);
            while (i >= 0 && getName(url.get(i).url).compareTo(key) > 0) {
                url.set(i+1, url.get(i));

                i = i - 1;
            }

            url.set(i+1, node);
        }
    }
}
