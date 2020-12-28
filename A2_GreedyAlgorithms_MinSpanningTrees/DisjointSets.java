import java.io.*;
import java.util.*;
//By Alena Midgen, no collaborators
 
/****************************
*
* COMP251 template file
*
* Assignment 2, Question 1
*
*****************************/
 
 
public class DisjointSets {
 
    private int[] par;
    private int[] rank;
    
    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }
    
    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }
    
    /* find resentative of element i */
    public int find(int i) {
    	//does recursive call until i is its own parent, at this point it's the root
    	while(i != par[i]) {
    		//calls method recursively on the parent of itself
    		i = find(par[i]);
    	}
        /* Fill this method (The statement return 0 is here only to compile) */
        return i;	//returns i when it's the root
        
    }
 
    /* merge sets containing elements i and j */
    public int union(int i, int j) {
    	//making some variables to clarify the code
    	int iRoot = find(i);
    	int jRoot = find(j);
    	//the new rank will be the sum of the ranks of the reps of i and j's sets
    	int newRank = rank[iRoot] + rank[jRoot];
    	//if they're in the same tree, the rank will remain the same
    	if(iRoot == jRoot) {
    		newRank = rank[iRoot];
    	}
    	int newRoot=-1;
    	//if the rank of i's set is less or equal to j's, the new root is j's root
    	if(rank[iRoot] <= rank[jRoot]) {
    		newRoot = jRoot;
    		//parent of i's root is the new root
    		par[iRoot]= newRoot;
    		//rank of j updated to the new rank
    		rank[jRoot] = newRank;
    	} else {
    		//otherwise the new root is i's root
    		newRoot = iRoot;
    		//parent of j's root is now the new root
    		par[jRoot]= newRoot;
    		//rank of new root is now the new rank
    		rank[iRoot] = newRank;
    	}
    	
        /* Fill this method (The statement return 0 is here only to compile) */
        return newRoot;	//returns the new root
        
    }
    
    public static void main(String[] args) {
        
        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);
        
    }
 
}
