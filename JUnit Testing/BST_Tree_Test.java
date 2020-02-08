import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BST_Tree_Test {

	static Tree tr;
	static TreeSet<Integer> ts = new TreeSet<Integer>();
	static Random r = new Random();

	@BeforeAll
	public static void setup() {
		// code to be filled in by you
		int a = r.nextInt(25);
		tr = new Tree(a);
		ts.add(a);
		for (int i = 1; i <= 24; i++)
		{
			a = r.nextInt(25);
			tr.insert(a);
			ts.add(a);
		}
		System.out.println("Tree created in Setup:");
		for (int x : tr) 
			System.out.print(x + " ");
		System.out.println();
		System.out.println("TreeSet created in Setup:");
		for (int x : ts) 
			System.out.print(x + " ");
		System.out.println();
		System.out.println("------------------------------------------------------");
	}		 

	@AfterEach
	void check_invariant() {
		// code to be filled in by you 
		assertTrue(ordered(tr));
		System.out.println("Tree invariant maintained");
	}
		
	@Test
	void test_insert() {
		// code to be filled in by you	 
		System.out.println("Testing Tree insert ...");
		System.out.println("Creating TreeSet iterator and Comparing elements pair-wise ...");
		Iterator<Integer> iter1 = tr.iterator();
		Iterator<Integer> iter2 = ts.iterator();
		while(iter1.hasNext() && iter2.hasNext())
		{
			assertTrue(iter1.next() == iter2.next());
		}
		System.out.println("... Tree insert test passed");
	}
		
	public boolean ordered(Tree tr) {
		// code to be filled in by you	
		return (tr.left == null || tr.value > tr.left.max().value && ordered(tr.left)) &&
				(tr.right == null || tr.value < tr.right.min().value && ordered(tr.right));
	}

}