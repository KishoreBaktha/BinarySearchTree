package com.company;
import java.util.*;
//bst
public class Main {
    int key;
    Main left, right;
     public Main(int key)
     {
         this.key=key;
         left=right=null;
     }
    public static void main(String[] args) {
        // write your code here
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        Main Root=null;
        for(int i=0;i<n;i++)
          Root=insert(Root,scanner.nextInt());
        System.out.println("inorder");
        inordertraversal(Root);
        System.out.println("preorder");
        preordertraversal(Root);
        System.out.println("postorder");
        postordertraversal(Root);

    }

    private static void preordertraversal(Main root) {
         if(root==null)
             return;
         System.out.print(root.key+" ");
         preordertraversal(root.left);
         preordertraversal(root.right);

    }

    private static void postordertraversal(Main root) {
         if(root==null)
             return;
         postordertraversal(root.left);
         postordertraversal(root.right);
         System.out.print(root.key+" ");
    }

    private static void inordertraversal(Main root) {
         if(root==null)
             return;
         inordertraversal(root.left);
         System.out.print(root.key+" ");
         inordertraversal(root.right);
    }

    public static  Main insert(Main root,int key)
    {
        if(root==null)
            root=new Main(key);
        else if(key<root.key)
        {
            Main temp;
            temp=insert(root.left,key);
            root.left=temp;
        }
        else
        {
            Main temp;
            temp=insert(root.right,key);
            root.right=temp;
        }
        return  root;
    }
}
