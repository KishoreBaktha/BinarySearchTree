package com.company;
import java.util.*;
class Node3
{
    int data;
    Node3 left, right;

    public Node3(int item)
    {
        data = item;
        left = right = null;
    }
}

/* Class to find size of Binary Tree */
class kthsmallest
{
    Node3 root;

    /* Given a binary tree. Print its nodes in level order
       using array for implementing queue */
    int size()
    {
        return size(root);
    }

    /* computes number of nodes in tree */
    static int size(Node3 node)
    {
        if (node == null)
            return 0;
        else
            return(size(node.left) + 1 + size(node.right));
    }

    public static void main(String args[])
    {
        /* creating a binary tree and entering the nodes */
        kthsmallest tree = new kthsmallest();
        tree.root = null;
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        for(int i=0;i<n;i++)
            tree.root=insert(tree.root,scanner.nextInt());
        System.out.println("The size of binary tree is : "
                + tree.size());
        System.out.println(orderstatistic2(tree.root,7).data);
    }
    //kth smallest element
    public static Node3 orderstatistic(Node3 root,int k)
    {
        int s=size(root.left);
        if(k==s+1)
            return root;
        else if(k<s+1)
            return orderstatistic(root.left,k);
        else
            return orderstatistic(root.right,k-s-1);
    }
    //kth largest element
    public static Node3 orderstatistic2(Node3 root,int k)
    {
        int s=size(root.right);
        if(k==s+1)
            return root;
        else if(k<s+1)
            return orderstatistic2(root.right,k);
        else
            return orderstatistic2(root.left,k-s-1);
    }
    public static  Node3 insert(Node3 root,int key)
    {
        if(root==null)
            root=new Node3(key);
        else if(key<root.data)
        {
            Node3 temp;
            temp=insert(root.left,key);
            root.left=temp;
        }
        else
        {
            Node3 temp;
            temp=insert(root.right,key);
            root.right=temp;
        }
        return  root;
    }
}
