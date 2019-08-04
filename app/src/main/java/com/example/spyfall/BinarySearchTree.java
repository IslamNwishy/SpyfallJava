package com.example.spyfall;

import java.util.ArrayList;

public class BinarySearchTree {


    private class Node{
        String Key;
        Node left,right;

        Node(String item){
            Key=item;
            left=right=null;
        }
    }




    private Node root;
    private boolean IsNew;

    public void BinarySearchTree(){
        root=null;
    }

    public boolean Insert(String item){

       IsNew=true;
       Node temp = Insert2(root,item);

       if(!IsNew)
           return false;

       root=temp;
       return true;
    }

    private Node Insert2(Node root, String item){


        if(root==null){
            root = new Node(item);
            return root;
        }
        else if(item.compareTo(root.Key)==0){
            IsNew=false;
        }
        else if(item.compareTo(root.Key)<0)
            root.left=Insert2(root.left,item);

        else
            root.right=Insert2(root.right,item);

        return root;

    }

    public ArrayList<String> Traverse(){
        ArrayList<String> List =  new ArrayList<String>();
        Traverse2(root,List);
        return List;
    }

    private void Traverse2(Node root, ArrayList<String> List){
        if(root==null)
            return;
        List.add(root.Key);
        Traverse2(root.left,List);
        Traverse2(root.right,List);
    }


}
