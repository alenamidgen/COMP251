import java.io.*;
import java.util.*;

//no collaborators
public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
        	//sets variables with the attributes
        	int A = this.A;
        	int m = this.m;
        	int r = this.r;
        	int w = this.w;
        	//finds h(k) using the same equation as used in Chaining.java
        	int hk = ((A * key) % power2(w))>>(w-r);
        	//uses hk to find the result and returns it
        	int result = (hk +i) % power2(r);
        return result;
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //declares some variables to be used
        	boolean full = true;
            int index = -1;
            int i = 0;
            //this loop iterates until the probe function finds an empty slot, or until it checks all the slots
            while(full) {
            	index = probe(key, i);
            	// if the index holds the value -1 (empty) or -2 (an element was removed) the loop will stop
            	if (this.Table[index] == -1 || this.Table[index]==-2) {
            		full = false;
            		break;
            	} else {
            		i++;
            	//if the method has checked m slots and they are all full, the method returns i since it will not be able to insert the key
            	} if(i== this.m-1) {
            		return i;
            	}
            	
            }
        	//the key is put in the Table at the index and i is returned
            this.Table[index]= key; 
        	return i;
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            int index;
            int i = 0;
            while(true) {
            	//returns if it has checked every slot
            	if(i==this.m) {
            		return i;
            	}
            	//find the index using the probe function and current i value
            	index = probe(key, i);
            	//if the index holds the key, sets it equal to -1 (removing it) and returns the number of attempts
            	if(this.Table[index] == key) {
            		this.Table[index] = -2;
            		return i;
            	}if (this.Table[index]==-1) {
            		//if the element at the probe index is -1, that means the element isn't in the table since it hasn't found it yet and -1 means empty
            		return i+1;
            	}
            	
            	//increment i after each attempt to find the key
            	i++;
            }
        }
        
}
import java.io.*;
import java.util.*;
 
//no collaborators
public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;
 
     protected Open_Addressing(int w, int seed, int A) {
 
         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
        	//sets variables with the attributes
        	int A = this.A;
        	int m = this.m;
        	int r = this.r;
        	int w = this.w;
        	//finds h(k) using the same equation as used in Chaining.java
        	int hk = ((A * key) % power2(w))>>(w-r);
        	//uses hk to find the result and returns it
        	int result = (hk +i) % power2(r);
        return result;
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //declares some variables to be used
        	boolean full = true;
            int index = -1;
            int i = 0;
            //this loop iterates until the probe function finds an empty slot, or until it checks all the slots
            while(full) {
            	index = probe(key, i);
            	// if the index holds the value -1 (empty) or -2 (an element was removed) the loop will stop
            	if (this.Table[index] == -1 || this.Table[index]==-2) {
            		full = false;
            		break;
            	} else {
            		i++;
            	//if the method has checked m slots and they are all full, the method returns i since it will not be able to insert the key
            	} if(i== this.m-1) {
            		return i;
            	}
            	
            }
        	//the key is put in the Table at the index and i is returned
            this.Table[index]= key; 
        	return i;
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            int index;
            int i = 0;
            while(true) {
            	//returns if it has checked every slot
            	if(i==this.m) {
            		return i;
            	}
            	//find the index using the probe function and current i value
            	index = probe(key, i);
            	//if the index holds the key, sets it equal to -1 (removing it) and returns the number of attempts
            	if(this.Table[index] == key) {
            		this.Table[index] = -2;
            		return i;
            	}if (this.Table[index]==-1) {
            		//if the element at the probe index is -1, that means the element isn't in the table since it hasn't found it yet and -1 means empty
            		return i+1;
            	}
            	
            	//increment i after each attempt to find the key
            	i++;
            }
        }     
}
