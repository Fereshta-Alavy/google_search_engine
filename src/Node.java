/*
this is an implementation of BST nodes.
 */
public class Node {
    urlObjects obj;     //to hold the url object
    int keyTotalScore;  //to hold the total score of the url obj
    int keyPageRank;    //to hold the page rank of the url obj
    Node left;          //pointer to left child
    Node right;         //pointer to right child
    Node parent;        //pointer to parent
    //constructor
    Node(urlObjects url){
        this.obj = url;
        this.keyTotalScore= url.totalScore;
        this.keyPageRank= url.pageRank;
        left = null;
        right = null;
        parent = null;
    }


}
