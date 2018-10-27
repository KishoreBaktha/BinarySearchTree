package com.company;
import java.util.*;
class Node4
{
    Node4 left,right,parent;
    int key;
    public Node4(int key)
    {
        this.key=key;
        left=right=null;
    }
}
public class splay {
    static Node4 root,root2;
    // A utility function to right rotate subtree rooted with y
    static Node4 rightRotate(Node4 y) {
        Node4 x = y.left;
        Node4 T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
       // y.height = max(height(y.left), height(y.right)) + 1;
       // x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    static Node4 leftRotate(Node4 x) {
        Node4 y = x.right;
        Node4 T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
      //  x.height = max(height(x.left), height(x.right)) + 1;
    //    y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }
    public static Node4 splayoperation(Node4 root,int key)
    {
        //base case
       if(root==null||root.key==key)
           return  root;
        // Key lies in left subtree
       if(root.key>key)
       {
           //data not in tree
           if(root.left==null)
               return root;
           // Zig-Zig (Left Left)
           if (root.left.key > key)
           {
               // First recursively bring the key as root of left-left
               root.left.left = splayoperation(root.left.left, key);

               // Do first rotation for root, second rotation is done after else
               root = rightRotate(root);
           }
           else if (root.left.key < key) // Zig-Zag (Left Right)
           {
               // First recursively bring the key as root of left-right
               root.left.right = splayoperation(root.left.right, key);

               // Do first rotation for root->left
               if (root.left.right != null)
                   root.left = leftRotate(root.left);
           }

           // Do second rotation for root
           return (root.left == null)? root: rightRotate(root);
       }
       else // Key lies in right subtree
       {
           // Key is not in tree, we are done
           if (root.right == null) return root;

           // Zag-Zig (Right Left)
           if (root.right.key > key)
           {
               // Bring the key as root of right-left
               root.right.left = splayoperation(root.right.left, key);

               // Do first rotation for root->right
               if (root.right.left != null)
                   root.right = rightRotate(root.right);
           }
           else if (root.right.key < key)// Zag-Zag (Right Right)
           {
               // Bring the key as root of right-right and do first rotation
               root.right.right = splayoperation(root.right.right, key);
               root = leftRotate(root);
           }

           // Do second rotation for root
           return (root.right == null)? root: leftRotate(root);
       }
       }
    public static Node4 find(int key,Node4 Root) {
        if (Root.key == key)
            return Root;
        else if (key < Root.key) {
            if (Root.left != null) {
                return find(key, Root.left);
            }
            //place where it can be inserted
            return Root;
            // return new Node4(-1);
        } else {
            if (Root.right != null) {
                return find(key, Root.right);
            }
            //place where it can be inserted
            return Root;
            // return new bstapplication(-1);
        }
    }
    public static Node4 STFind(int key,Node4 root)
    {
        Node4 N=find(key,root);
        root=splayoperation(root,key);
        System.out.println("location-"+ N.key);
        return root;
    }
    public static  Node4 insert(Node4 root,int key)
    {
        if(root==null)
            root=new Node4(key);
        else if(key<root.key)
        {
            Node4 temp;
            temp=insert(root.left,key);
            root.left=temp;
            temp.parent=root;
        }
        else
        {
            Node4 temp;
            temp=insert(root.right,key);
            root.right=temp;
            temp.parent=root;
        }
        return  root;
    }
    public static  Node4 STInsert(int key,Node4 root)
    {
        root=insert(root,key);
        root=splayoperation(root,key);
        return root;
    }
    public static Node4 Next(Node4 N)
    {
        if(N.right!=null)
            return leftdescendent(N.right);
        else
            return rightancestor(N);
    }
    public static Node4 leftdescendent(Node4 N)
    {
        if(N.left==null)
            return N;
        else
            return leftdescendent(N.left);
    }
    public static Node4 rightancestor(Node4 N)
    {
        if(N.key<N.parent.key)
            return N.parent;
        else
            return rightancestor(N.parent);
    }
    //delete there must be atleast one element grewater and equal to key
    public static Node4 Stdelete(Node4 root,int key)
    {
        Node4 next=Next(find(key,root));
        System.out.println("next-"+next.key);
        root=splayoperation(root,next.key);
        root=splayoperation(root,key);//no left node for successor
        Node4 find=find(key,root);
        Node4 L=find.left;
        Node4 R=find.right;
        R.left=L;
        L.parent=R;
        R.parent=null;
        root=R;
        return root;
    }
    public static Node4 STMerge(Node4 R1,Node4 R2)
    {
        Node4 N=find(Integer.MAX_VALUE,R1);//max in left tree,all elements smaller than the right
        R1=splayoperation(R1,N.key);
        R1.right=R2;
        R2.parent=N;
        return R1;
    }
    public static void STSplit(Node4 root,int x)
    {
        Node4 N=find(x,root);
        root=splayoperation(root,N.key);
        root2=root.right;
        root=root.left;
    }
    public static void main(String[] args)
    {
          Scanner scanner=new Scanner(System.in);
          int n=scanner.nextInt();
          for(int i=0;i<n;i++)
           root=STInsert(scanner.nextInt(),root);
//        for(int i=0;i<5;i++)
//            root2=STInsert(scanner.nextInt(),root2);
        //Node4 root3=STMerge(root,root2);
       // STFind(7,root3);
          //root=STFind(3,root);
          //root=Stdelete(root,3);
          //System.out.println(root.right.key);
      //  System.out.println(root.key);
        STSplit(root,7);
        System.out.println(root.key);
        System.out.println(root2.key);
    }
}
