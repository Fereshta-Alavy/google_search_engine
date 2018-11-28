/*
    This class is implementing the binary search tree with its
    functions.
 */
public class BST {
    private Node root;  //to holding the root of the BST
    public int index = 0;

    //constructor
    public BST() {
        root = null;
    }

    //This function return the root of our tree
    public Node getRoot() {
        return root;
    }

    //This function return the node that is being searched based on total score
    public Node treeSearch(Node x, int k) {
        if (x == null || k == x.obj.totalScore)
            return x;
        if (k < x.obj.totalScore)
            return treeSearch(x.left, k);
        else
            return treeSearch(x.right, k);


    }

    //This function will return the minimum of the tree
    public Node treeMinimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    //This function will insert a new node to the tree
    public void treeInsert(Node z) {
        Node y = null;
        Node x = this.root;
        while (x != null) {
            y = x;
            if (z.keyTotalScore < x.keyTotalScore)
                x = x.left;
            else
                x = x.right;
        }
        z.parent = y;
        if (y == null)
            this.root = z;   // tree T was empty
        else if (z.keyTotalScore < y.keyTotalScore)
            y.left = z;
        else
            y.right = z ;
    }

    //This function will do the in order walk of the tree and print the elements
    public void inorderTreeWalk(Node x){
        if (x != null){
            inorderTreeWalk(x.left);
            x.obj.pageRank = index--;
            System.out.println( "index: " + x.obj.index + ";" + " Total score :" + x.obj.totalScore + ";" + " PageRank: " + x.obj.pageRank + ";" + " url : " + x.obj.url );
            inorderTreeWalk(x.right);
        }
    }

    //This function will delete a node from tree
    public void treeDelete(Node z){
        Node y = null;
        // z has no children
        if (z.left == null)
            Transplant(z , z.right);
        // z has a left child but no right child
        else if (z.right== null)
            Transplant(z, z.left);
        // z has two children
        else {
            y = treeMinimum(z.right);
        // finding z's successor
            if (y.parent != z ) {           //y lies within y’s right subtree but is not the root of this subtree
                Transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            //(c)if yis z’s right child
            Transplant(z, y);
            y.left= z.left;     //replace y’s left child by z’s left child
            y.left.parent = y ;
            }


    }

    public void Transplant (Node u , Node v){
        // Handle u is root of T
        if (u.parent == null)
            this.root = v;
        // if u is a left child
        else if (u == u.parent.left)
            u.parent.left= v;
        //if u is a right child
        else
            u.parent.right = v;
        //update v.p if v is non-NIL
        if( v != null)
            v.parent = u.parent;
    }
}


