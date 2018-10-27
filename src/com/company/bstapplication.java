package com.company;
import java.util.*;
public class bstapplication {
    int key;
    bstapplication left, right,parent;
    static List<Integer> range=new ArrayList<Integer>();
    public bstapplication(int key)
    {
        this.key=key;
        left=right=null;
    }
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        bstapplication Root=null;
        for(int i=0;i<n;i++)
            Root=insert(Root,scanner.nextInt());
       // bstapplication find=find(5,Root);
       // System.out.println(find.key);
     //   bstapplication next=Next(find);
     //   System.out.println(next.key);
       RangeSearch(5,45,Root);
       for(int i=0;i<range.size();i++)
            System.out.println(range.get(i));
        //delete(find);
       //  find=find(5,Root);
         //System.out.println(find.key);
    }
    public static  bstapplication insert(bstapplication root,int key)
    {
        if(root==null)
            root=new bstapplication(key);
        else if(key<root.key)
        {
            bstapplication temp;
            temp=insert(root.left,key);
            root.left=temp;
            temp.parent=root;
        }
        else
        {
            bstapplication temp;
            temp=insert(root.right,key);
            root.right=temp;
            temp.parent=root;
        }
        return  root;
    }
    public static bstapplication find(int key,bstapplication Root)
    {
        if(Root.key==key)
            return Root;
        else if(key<Root.key)
        {
            if(Root.left!=null)
            {
                return find(key,Root.left);
            }
            //place where it can be inserted
            //return Root;
            return new bstapplication(-1);
        }
        else
        {
            if(Root.right!=null)
            {
                return find(key,Root.right);
            }
            //place where it can be inserted
           // return Root;
            return new bstapplication(-1);
        }
    }
    public static bstapplication Next(bstapplication N)
    {
        if(N.right!=null)
            return leftdescendent(N.right);
        else
            return rightancestor(N);
    }
    public static bstapplication leftdescendent(bstapplication N)
    {
        if(N.left==null)
            return N;
        else
            return leftdescendent(N.left);
    }
    public static bstapplication rightancestor(bstapplication N)
    {
        if(N.key<N.parent.key)
            return N.parent;
        else
            return rightancestor(N.parent);
    }
    public static List RangeSearch(int x,int y,bstapplication root)
    {
        bstapplication N=find(x,root);
        while(N.key<=y)
        {
            if(N.key>=x)
              range.add(N.key);
            N=Next(N);
        }
        return range;
    }
    public static void delete(bstapplication N)
    {
        if(N.right==null)
        {
            if(N.parent.key>N.key)
                N.parent.left=N.left;
            else
                N.parent.right=N.left;
            //simple promote
        }
        else
        {
            bstapplication x=Next(N);
            if(N.parent.key>N.key)
                N.parent.left=x;
            else
                N.parent.right=x;
            if(x.parent.key>x.key)
                x.parent.left=x.right;
            else
                x.parent.right=x.right;
            //no right child for x
        }
    }
}
