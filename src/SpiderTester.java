import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SpiderTester {
/*
this is the test class that will run and control the flow of the program.
This class has an object of crawler, BST, quickSort and bucketsort. It also has a menu
for the user to choose their desired action.
 */




    public static void main(String[] args) {
        spiderLeg leg;
        int option = 0;     //to hold the user option
//        HashMap<String, Integer> searchedKeyWord = new HashMap<String, Integer>(); //this is to hold each keyword with number of their repitition
//        Quicksort quicksort; //creating a quick sort object
        RBT redBlackTree;   //creating a binary search tree object
        redBlackTree = new RBT();   //initializing the BST
        List<String> list = new ArrayList<>(); //creating a list to hold the initial list of urls
        Scanner scanner = new Scanner(System.in);
        List<urlObjects> arrUrl = new ArrayList<>();    //list of URL objects
        String key = "";
        Spider spider = new Spider();

        FunnyCrawler crawl = new FunnyCrawler();    //this the instantiation of the new crawler

        //This will be a forever while loop to get the option from the user
        //option 10 is to quit the program
        while (option != 10) {


            System.out.println("1) Search for a keyword");
            System.out.println("2) Print the urls:");
            System.out.println("3) Create a BST");
            System.out.println("4) Search for url base on it's totalScore:");
            System.out.println("5) Insert a url with it's totalScore:");
            System.out.println("6) Delete a url base on it's totalScore:");
            System.out.println("7) Print the sorted list:");
            System.out.println("8) Quit" + "\n\n");
            System.out.println("Please choose a number from list above:");
            option = scanner.nextInt();
            if (option == 1) {

                System.out.print("Please enter a keyword");
                String keyword = scanner.next();
                //adding keyword to the hash map to keep track of the user entry

                redBlackTree = new RBT();   //re initializing the tree for every new entry
                leg = new spiderLeg();          //spider leg to get the number of links in each link
                arrUrl = new ArrayList<>();     // re initializing the arr url to get the new set of urls

                list = crawl.getDataFromGoogle(keyword);    //sending the keyword to crawler and assigning it to list
                List<Integer> numOfLinks = new ArrayList<>();   //holding the number of links for each url

                int max = 0;
                for (int i = 0; i < arrUrl.size(); i++) {
                    int temp2 = arrUrl.get(i).totalScore;
                    if (temp2 > max) ;
                    temp2 = 30 - i;
                    arrUrl.get(i).pageRank = temp2;
                }

                //getting the number of links for each link
                for (String l : list) {
                    int temp = leg.getLinksOnPage(l);
                    numOfLinks.add(temp);
                    System.out.print(".");
//                    System.out.println(l);
                    if (temp > max)
                        max = temp;
                }



                //Getting the total score of each url and adding the urls to url list
                for (int i = 0; i < 30; i++) {

                    int score = totalScore(numOfLinks.get(i));
                    arrUrl.add(new urlObjects(score, list.get(i), i + 1, 0));
                }
            }
            if (option == 2) {
                System.out.println("\n" + "This is the list of urls before building RBT :" + "\n\n");
                for (int i = 0; i < arrUrl.size(); i++) {
                    System.out.println(i + 1 + ")" + "index: " + arrUrl.get(i).index + ";" + " Total score :" + arrUrl.get(i).totalScore + ";" + " url : " + arrUrl.get(i).url );
                }

            } else if (option == 3) {


                System.out.println("Red Black Tree is build" + "\n");
                Node node = null;

                for (int i = 0; i < arrUrl.size(); i++) {

                    node = new Node(arrUrl.get(i));
                    redBlackTree.treeInsert(node);

                }
                redBlackTree.index = arrUrl.size();



            } else if (option == 4) {
                System.out.println("Enter a totalScore to look for url :");
                int searchedValue = scanner.nextInt();
                Node searchedNode = redBlackTree.treeSearch(redBlackTree.getRoot(), searchedValue);
                System.out.println( " Total score :"+ searchedNode.obj.totalScore+ " has following property :" + "\n" + "index: " + searchedNode.obj.index + ";" +" Page Rank : "+ searchedNode.obj.pageRank + " Color : " + searchedNode.color + " url : " + searchedNode.obj.url   + "\n");
            } else if (option == 5) {
                System.out.println("Enter a url with it total score to insert in the tree");
                String userUrl = scanner.next();
                int userTotalScore = scanner.nextInt();

                urlObjects insertNode = new urlObjects(userTotalScore, userUrl, arrUrl.size(), 0);
                Node newNode = new Node(insertNode);
                redBlackTree.treeInsert(newNode);
                redBlackTree.index = arrUrl.size();
                redBlackTree.inorderTreeWalk(redBlackTree.getRoot());
            } else if (option == 6) {
                System.out.println("Enter a total score you would like to delete from tree");
                int deletedPageRank = scanner.nextInt();
                Node deletedNode = redBlackTree.treeSearch(redBlackTree.getRoot(), deletedPageRank);
                redBlackTree.treeDelete(deletedNode);
                redBlackTree.index = arrUrl.size();
                redBlackTree.inorderTreeWalk(redBlackTree.getRoot());

            } else if (option == 7) {
                redBlackTree.inorderTreeWalk(redBlackTree.getRoot());

            }
        }

    }

    //This function is calculating the total score based on the number of links and 3 other random numbers
    static int totalScore(int temp) {
        int freq = (int) (Math.random() * 10000);
        int siteAge = (int) (Math.random() * 10000);
        int moneyPaid = (int) (Math.random() * 10000);
        int Score = freq + siteAge + moneyPaid + temp;
        return Score;
    }


}

