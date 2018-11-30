/*
    This class is implementing the red black tree with its
    functions.
 */
public class RBT {
    private Node root;  //to holding the root of the BST
    public int index = 0;

    //constructor
    public RBT() {
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
        z.color = state.RED;
        rbInsertFixUp(z);
    }

    //this function is a helper function that help to fix the redblack tree
    public void rbInsertFixUp(Node z){
        while (z.parent != null && z.parent.color == state.RED){
            if (z.parent.parent.left != null && z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (y != null && y.color == state.RED) {
                    z.parent.color = state.BLACK;
                    y.color = state.BLACK;
                    z.parent.parent.color = state.RED;
                    z = z.parent.parent;
                } else{
                    if (z == z.parent.right) {
                    z = z.parent;
                    leftRotate(z);
                }
                z.parent.color = state.BLACK;
                z.parent.parent.color = state.RED;
                rightRotate(z.parent.parent);
            }
            }
            else{
                Node y = z.parent.parent.left;
                if (y != null && y.color == state.RED){
                    z.parent.color = state.BLACK;
                    y.color = state.BLACK;
                    z.parent.parent.color = state.RED;
                    z = z.parent.parent;
                }
                else {
                    if(z == z.parent.left){
                    z = z.parent;
                    rightRotate(z);
                    }
                z.parent.color = state.BLACK;
                z.parent.parent.color = state.RED;
                leftRotate(z.parent.parent);}
            }
        }
        this.root.color = state.BLACK;
    }
    //rotating the node
    public void leftRotate(Node x){
        Node y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            this.root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.left = x;
        x.parent = y;
    }
    //rotating the node
    public void rightRotate(Node x){
        Node y = x.left;
        x.left = y.right;
        if (y.right != null)
            y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            this.root = y;
        else if (x == x.parent.right)
            x.parent.right = y;
        else
            x.parent.left = y;
        y.right = x;
        x.parent = y;
    }


    //This function will do the in order walk of the tree and print the elements
    public void inorderTreeWalk(Node x){
        if (x != null){
            inorderTreeWalk(x.left);
            if (index > 0)
                x.obj.pageRank = index--;
            System.out.println( "index: " + x.obj.index + ";" + " Total score :" + x.obj.totalScore + ";" + " PageRank: " + x.obj.pageRank + ";"+" Color: " + x.color + " url : " + x.obj.url );
            inorderTreeWalk(x.right);
        }
    }

    //This function will delete a node from tree
    public void treeDelete(Node z){
        Node y = z;
        Node x;
        state yOriginalColor = y.color;
        // z has no children
        if (z.left == null) {
            x = z.right;
            rbTransplant(z, z.right);
        }
        // z has a left child but no right child
        else if (z.right== null) {
            x = z.left;
            rbTransplant(z, z.left);
        }
        // z has two children
        else {
            y = treeMinimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            // finding z's successor
            if (y.parent == z && x != null){
                x.parent = y;
            }
            else{
                rbTransplant(y,y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            //(c)if yis z’s right child
            rbTransplant(z, y);
            y.left= z.left;     //replace y’s left child by z’s left child
            y.left.parent = y ;
            y.color = z.color;
        }
        if (yOriginalColor == state.BLACK && x != null)
            treeDeleteFixup(x);
    }

    //this is a helper function for fixing the tree after delete
    public void treeDeleteFixup(Node x){
        while (x != this.root && x.color == state.BLACK) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.color == state.RED) {
                    w.color = state.BLACK;
                    x.parent.color = state.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                } else {
                    if (w.left.color == state.BLACK && w.right.color == state.BLACK) {
                        w.color = state.RED;
                        x = x.parent;
                    } else
                    {if (w.right.color == state.BLACK) {
                        w.left.color = state.BLACK;
                        w.color = state.RED;
                        rightRotate(w);
                        w = x.parent.right;

                    }
                    w.color = x.parent.color;
                    x.parent.color = state.BLACK;
                    w.right.color = state.BLACK;
                    leftRotate(x.parent);
                    x = this.root;}
                }
            } else {
                Node w = x.parent.left;
                if (w.color == state.RED) {
                    w.color = state.BLACK;
                    x.parent.color = state.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                else{
                if (w.left.color == state.BLACK && w.right.color == state.BLACK) {
                    w.color = state.RED;
                    x = x.parent;
                } else {
                    if (w.left.color == state.BLACK) {
                        w.right.color = state.BLACK;
                        w.color = state.RED;
                        leftRotate(w);
                        w = x.parent.left;

                    }
                    w.color = x.parent.color;
                    x.parent.color = state.BLACK;
                    w.left.color = state.BLACK;
                    rightRotate(x.parent);
                    x = this.root;
                }
            }
        }
        }
        x.color = state.BLACK;
    }

    //transplant is called from delete node function
    public void rbTransplant (Node u , Node v){
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
        if (v != null)
            v.parent = u.parent;
    }
}


