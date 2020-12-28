import java.io.*;
import java.util.*;
 
//no collaborators
public class main {     
 
	public static void main(String[] args) {
		main m = new main();
		
		/*testChaining();
		oaSucessfulInsertion();
		oaUnsucessfulInsertion();
		oaSucessfulDeletion();
		oaUnsucessfulDeletion();*/
	}
	
	public static void testChaining() {
		Chaining c = new Chaining(5, 1, 1);
		System.out.println(c.Table);
		int hash = c.chain(1);
		if(hash == 0) {
			System.out.println("hash function worked correctly");
		}else {
			System.out.println("hash function did not work");
		}
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		c.insertKeyArray(arr);
		ArrayList<Integer> indexZero = new ArrayList<Integer>();
		ArrayList<Integer> indexOne = new ArrayList<Integer>();
		ArrayList<Integer> indexTwo = new ArrayList<Integer>();
		for(int i = 1; i<4; i++) {
			indexZero.add(i);
			indexOne.add(i+3);
			indexTwo.add(i+7);
		}
		indexOne.add(7);
		
		if(c.Table.get(0).equals(indexZero) && c.Table.get(1).equals(indexOne) && c.Table.get(2).equals(indexTwo)) {
			System.out.println("Inserting the array of keys worked");
		}else {
			System.out.println("Inserting the array of keys did not work");
		}
		int collisions = c.insertKey(40);
		if(collisions == 3 && c.Table.get(2).contains(40)) {
			System.out.println("inserting a key worked");
		}else {
			System.out.println("inserting a key did not work");
		}
	}
	
	public static void oaSucessfulInsertion() {
		Open_Addressing oa = new Open_Addressing(5, 1, 1);
		oa.Table[2] = 8;
		int collisions = oa.insertKey(40);
		if(collisions == 1) {
			System.out.println("correct number of insertions");
		}else {
			System.out.println("incorrect number of insertions");
		}
	}
	
	public static void oaUnsucessfulInsertion() {
		Open_Addressing oa = new Open_Addressing(5, 1, 1);
		for(int i = 0; i<oa.Table.length; i++) {
			oa.Table[i] = i;
		}
		int collisions = oa.insertKey(40);
		if(collisions == oa.Table.length && !Arrays.asList(oa.Table).contains(40)) {
			System.out.println("sucessfully checked all slots, value was not inserted");
		}else {
			System.out.println("something went wrong, unsucessful insertion did not work");
		}
	}
	
	public static void oaSucessfulDeletion() {
		Open_Addressing oa = new Open_Addressing(5, 1, 1);
		oa.Table[2] = 8;
		oa.insertKey(40);
		int checks = oa.removeKey(40);
		if(checks == 2 && oa.Table[3]==-2) {
			System.out.println("sucessfully removed");
		}else {
			System.out.println("removal didn't work");
		}
	}
	
	public static void oaUnsucessfulDeletion() {
		Open_Addressing oa = new Open_Addressing(5, 1, 1);
		for(int i = 0; i<oa.Table.length; i++) {
			oa.Table[i] = i;
		}
		oa.Table[7] = -1;
		oa.Table[6] = -1;
		oa.Table[5] = -1;
		oa.Table[4] = -1;
		int checks = oa.removeKey(40);
		int[] correctTable = {0, 1, 2, 3, -1, -1, -1, -1};
		if(checks < oa.Table.length && Arrays.equals(correctTable, oa.Table)) {
			System.out.println("deletion worked correctly");
		}else {
			System.out.println("deletion didn't work right");
		}
	}
}
