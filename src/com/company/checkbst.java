package com.company;
import java.util.*;
//enter pos1 for leftsubtree,pos2 for left or right of node in particular subtree
//left-1111
//right-11111
public class checkbst {
    checkbst left,right;
    int key;
    static int temp=-1;
    static checkbst head;
    static String tree="left";
    public checkbst(int key)
    {
        this.key=key;
        left=right=null;
    }
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
          head=new checkbst(scanner.nextInt());
         for(int i=1;i<n;i++)
             insert(scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
         inordertraversal(head);
         System.out.println("correct");

    }
    private static void insert(int key,int index,int pos1,int pos2)
    {
        checkbst temp=new checkbst(key);
        checkbst last=head;
         if(pos1==1111)
        {
            while(index!=1)
            {
                last=last.left;
                System.out.println("rootleft "+last.key);
                index--;
            }
            if(pos2==1111)
            last.left=temp;
            else
            last.right=temp;
        }
        else
        {
            while(index!=1)
            {
                last=last.right;
                System.out.println("rootright "+last.key);
                index--;
            }
            if(pos2==1111)
                last.left=temp;
            else
            last.right=temp;
        }
    }

    private static void inordertraversal(checkbst root) {
        if(root==null)
            return;
        inordertraversal(root.left);
        //check for equal keys, have to be on right always
        if(temp>root.key||(temp==root.key&&tree.equals("left")))
        {
            System.out.println("incorrect");
            System.exit(0);
        }
        else
        {
            temp=root.key;
        }
        tree="right";
        inordertraversal(root.right);
        tree="left";
    }
}
