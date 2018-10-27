package com.company;
import java.io.*;
import java.util.ArrayList;

// A binary tree node
class Node2 {

    int data;
    Node2 left, right;

    Node2(int d) {
        data = d;
        left = right = null;
    }
}

class mergebst
{

    // Root of BST
    Node2 root;

    // Constructor
    mergebst() {
        root = null;
    }

//    static void setChild(Node2 node, boolean toLeft, Node2 child) {
//        // Assign child node to the indicated direction: left or right
//        if (toLeft) {
//            node.left = child;
//        } else {
//            node.right = child;
//        }
//    }


    // Inorder traversal of the tree
    void inorder()
    {
        inorderUtil(this.root);
    }

    // Utility function for inorder traversal of the tree
    void inorderUtil(Node2 node)
    {
        if(node==null)
            return;
        inorderUtil(node.left);
        System.out.print(node.data + " ");
        inorderUtil(node.right);
    }


    // A Utility Method that stores inorder traversal of a tree
    public ArrayList<Integer> storeInorderUtil(Node2 node, ArrayList<Integer> list)
    {
        if(node == null)
            return list;

        //recur on the left child
        storeInorderUtil(node.left, list);

        // Adds data to the list
        list.add(node.data);

        //recur on the right child
        storeInorderUtil(node.right, list);

        return list;
    }

    // Method that stores inorder traversal of a tree
    ArrayList<Integer> storeInorder(Node2 node)
    {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = storeInorderUtil(node,list1);
        return list2;
    }

    // Method that merges two ArrayLists into one.
    ArrayList<Integer> merge(ArrayList<Integer>list1, ArrayList<Integer>list2, int m, int n)
    {
        // list3 will contain the merge of list1 and list2
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        int i=0;
        int j=0;

        //Traversing through both ArrayLists
        while( i<m && j<n)
        {
            // Smaller one goes into list3
            if(list1.get(i)<list2.get(j))
            {
                list3.add(list1.get(i));
                i++;
            }
            else
            {
                list3.add(list2.get(j));
                j++;
            }
        }

        // Adds the remaining elements of list1 into list3
        while(i<m)
        {
            list3.add(list1.get(i));
            i++;
        }

        // Adds the remaining elements of list2 into list3
        while(j<n)
        {
            list3.add(list2.get(j));
            j++;
        }
        return list3;
    }

    // Method that converts an ArrayList to a BST
    Node2 ALtoBST(ArrayList<Integer>list, int start, int end)
    {
        // Base case
        if(start > end)
            return null;

        // Get the middle element and make it root
        int mid = (start+end)/2;
        Node2 node = new Node2(list.get(mid));

        /* Recursively construct the left subtree and make it
        left child of root */
        node.left = ALtoBST(list, start, mid-1);

        /* Recursively construct the right subtree and make it
        right child of root */
        node.right = ALtoBST(list, mid+1, end);

        return node;
    }

    // Method that merges two trees into a single one.
    Node2 mergeTrees(Node2 node1, Node2 node2)
    {
        //Stores Inorder of tree1 to list1
        ArrayList<Integer>list1 = storeInorder(node1);

        //Stores Inorder of tree2 to list2
        ArrayList<Integer>list2 = storeInorder(node2);

        // Merges both list1 and list2 into list3
        ArrayList<Integer>list3 = merge(list1, list2, list1.size(), list2.size());

        //Eventually converts the merged list into resultant BST
        Node2 node = ALtoBST(list3, 0, list3.size()-1);
        return node;
    }


//    static Node2 splitTree(Node2 root, int k) {
//        Node2 root2 = null;
//        Node2 parent1 = null;
//        Node2 parent2 = null;
//        // Determine at which side of the root we will travel
//        boolean toLeft = root != null && k < root.data;
//
//        while (root != null) {
//            while (root != null && (k < root.data) == toLeft) {
//                parent1 = root;
//                root = toLeft ? root.left : root.right;
//            }
//            setChild(parent1, toLeft, null); // Cut out the edge. root is now detached
//            toLeft = !toLeft; // toggle direction
//            if (root2 == null) {
//                root2 = root; // This is the root of the other tree.
//            } else if (parent2 != null) {
//                setChild(parent2, toLeft, root); // re-attach the detached subtree
//            }
//            parent2 = parent1;
//            parent1 = null;
//        }
//        return root2;
//    }

    // Driver function
    public static void main (String[] args)
    {

        /* Creating following tree as First balanced BST
                100
                / \
                50 300
                / \
               20 70
        */

        mergebst tree1 = new mergebst();
        tree1.root = new Node2(100);
        tree1.root.left = new Node2(50);
        tree1.root.right = new Node2(300);
        tree1.root.left.left = new Node2(20);
        tree1.root.left.right = new Node2(70);

        /* Creating following tree as second balanced BST
                80
                / \
              40 120
        */

        mergebst tree2 = new mergebst();
        tree2.root = new Node2(80);
        tree2.root.left = new Node2(40);
        tree2.root.right = new Node2(120);


        mergebst tree = new mergebst();
        tree.root = tree.mergeTrees(tree1.root, tree2.root);
        System.out.println("The Inorder traversal of the merged BST is: ");
        tree.inorder();
        //tree.root=splitTree(tree1.root,50);
    }
}