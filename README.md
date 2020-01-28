# google_search_engine

The Google Search Engine stimulator is a small search engine, which can search and
print web pages that is related to the keyword that user enter. In this project I
implemented different data structures to manipulate all the URLs and give a desired
output for the user. All the URLs have a total score that shows how important is the URL.
Base on that total score I was able to sort the URLs from least important to the most
important URL using Quick sort. I also wanted to give ability to the user so
they can search, add or delete a URL from the list; therefore, I decided to use BST data
structure to let user manipulate the data. I implemented bucket sort for sorting all the
URL's related to the keyword, which is being searched the most, base on their first letter
of the URL. I used following classes to implement this project:

-FunnyCrawler.Java (was given)
-spiderLeg.Java (was given)
3. urlObject.java
4. BST.java
5. Node.java
6.BucketSort.java
7.SpiderTester.java
8.QuickSort.java

#SpiderTester class: This is main class that test all of the methods and classes
and provide a runnable code. The purpose of this class is that to put all of
the code from other classes together and create a search engine. There is
a menu that allow the user to choose the desired actions. When the user
make some decision the underlying functions manipulating data. A user
can search for one keyword or multiple keyword in a session and can get
the most popular keyword. All the data structures used in this programming
assignment were tested in this class.

#Quicksort class: The Purpose of this class is to take the list of my urls and sort them base
on their total score and print a descending sorted list. It has a helper function which is
partition and a function that is called initially.

#urlObject class: For this programming assignment we are asked to make
a Google Search Engine Simulator that prints 30 urls base on the keyword
and each url has to have Index, totalScore, Page Rank and URL. So to do
that I implemented a urlObject class to hold url with all the fileds such as
totalScore, pageRank, url and Index to make 30 of the same object with
different values. The three methods, setUrl(), setTotalScore(), and
setPageRank() will set the url name and corresponding totalScore and
pageRank for each urlObject that we need.

#BST class: A binary search tree or BST is a popular data structure which is
used to keep elements in order. The key for implementation this class is to
reduce the time complexity for the operations such as search, insert and
delete a node from the tree and still keep the bst properties. All the
operation for the BST can be perform in O(log(n))time.

#Node class: Each element in the binary search tree have left, right, parent, key. I
implement this class to have a node object so I can use it in my BST class to have access
to each part of the specific node. This class also has the fields obj type urlObject and
keyTotalScore and keyPageRank of type int to have access to the instances for the
comparison in the bst.

#BucketSort class : In this class I implement the bucket sort to put each url base on the first
letter of their url into a bucket in alphabetical order. I used a separate list of lists with
capacity of 27 to have a space for every character and that will act as buckets and
added every url based on the alphabet to it.
