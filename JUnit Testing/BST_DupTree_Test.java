import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BST_DupTree_Test {

	static DupTree dtr;
	static List<Integer> al = new ArrayList<Integer>();
	static Random r = new Random();

	@BeforeAll
	public static void setup() {
	 	// code to be filled in by you
		int a = r.nextInt(25);
		dtr = new DupTree(a);
		al.add(a);
		for (int i = 1; i <= 24; i++)
		{
			a = r.nextInt(25);
			dtr.insert(a);
			al.add(a);
		}
		Collections.sort(al);
		System.out.println("DupTree created in Setup:");
		for (int x : dtr) 
			System.out.print(x + " ");
		System.out.println();
		System.out.println("Sorted ArrayList created in Setup:");
		for (int x : al) 
			System.out.print(x + " ");
		System.out.println();
		System.out.println("------------------------------------------------------");
	}

	@AfterEach
	void check_invariant() {
		// code to be filled in by you
		assertTrue(ordered(dtr));
		System.out.println("DupTree invariant maintained");
		System.out.println("------------------------------------------------------");
	}
	
	@Test
	void test_insert() {
		// code to be filled in by you 
		System.out.println("Testing DupTree insert ...");
		System.out.println("Creating ArrayList iterator and Comparing elements pair-wise ...");
		Iterator<Integer> iter1 = dtr.iterator();
		Iterator<Integer> iter2 = al.iterator();
		while(iter1.hasNext() && iter2.hasNext())
		{
			assertTrue(iter1.next() == iter2.next());
		}
		System.out.println("... DupTree insert test passed");
	}
	
	@Test
	void test_delete() {
		// code to be filled in by you
		System.out.print("Testing DupTree Delete: ");
		int v = r.nextInt(25);
		dtr.insert(v);
		int oldCount = get_count(dtr, v);
		System.out.println("inserted value = " + v + " with count = " + oldCount);
		dtr.delete(v);
		int newCount = get_count(dtr, v);
		System.out.println("After DupTree delete: value = " + v + " count = " + newCount);
		assertEquals(oldCount, newCount + 1);
		System.out.println("DupTree delete test passed");
	}		

	public int get_count(DupTree tr, int v) {
		// code to be filled in by you 
		Tree t = tr.find(v);
		int count = 0;
		if (t != null)
			count = t.get_count();
		return count;
	}

	public boolean ordered(Tree tr) {
		// code to be filled in by you
		return (tr.left == null || tr.value > tr.left.max().value && ordered(tr.left)) &&
				(tr.right == null || tr.value < tr.right.min().value && ordered(tr.right));
	}
}