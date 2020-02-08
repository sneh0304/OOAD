// Assignment 1 Part 1: Starter Code

class Tree_Test {

	public static void main(String[] args) {
		AbsTree tr = new Tree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
		
		tr.delete(20);
		tr.delete(20);
		tr.delete(20);
		tr.delete(150);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);
	}
}

class DupTree_Test {

	public static void main(String[] args) {
		AbsTree tr = new DupTree(100);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(20);
		tr.insert(75);
		tr.insert(20);
		tr.insert(90);
		tr.insert(50);
		tr.insert(125);
		tr.insert(150);
		tr.insert(75);
		tr.insert(90);
		
		tr.delete(20);
		tr.delete(20);
		tr.delete(20);
		tr.delete(150);
		tr.delete(100);
		tr.delete(150);
		tr.delete(125);
		tr.delete(125);
		tr.delete(50);
		tr.delete(50);
		tr.delete(50);
		tr.delete(75);
		tr.delete(90);
		tr.delete(75);
		tr.delete(90);
	}
}

abstract class AbsTree {
	public AbsTree(int n) {
		value = n;
		left = null;
		right = null;
		//parent = this;
	}

	public void insert(int n) {
		if (value == n)
			count_duplicates();
		else if (value < n)
			if (right == null) {
				right = add_node(n);
				right.parent = this;//find(value);
			} else
				right.insert(n);
		else if (left == null) {
			left = add_node(n);
			left.parent = this;//find(value);
		} else
			left.insert(n);
	}

	public void delete(int n) {  
		AbsTree t = find(n);

		if (t == null) { // n is not in the tree
			System.out.println("Unable to delete " + n + " -- not in the tree!");
			return;
		}

		int c = t.get_count();
		if (c > 1) {
			t.set_count(c-1);
			return;
		}

		if (t.left == null && t.right == null) { // n is a leaf value
			if (t == this && t.parent == null)
				System.out.println("Unable to delete " + n + " -- tree will become empty!");
			else
				case1(t);
			return;
		}
		if (t.left == null || t.right == null) { // t has one subtree only
			if (t != this) { // check whether t is the root of the tree
				case2(t);
				return;
			} else {
				if (t.right == null)
					case3L(t);
				else
					case3R(t);
				return;
			}
		}
		// t has two subtrees; go with smallest in right subtree of t
		case3R(t);
	}

	protected void case1(AbsTree t) { // remove the leaf
		if(t.parent.left == t)
			t.parent.left = null;
		else
			t.parent.right = null;
	}

	protected void case2(AbsTree t) { // remove internal node
		if(t.left == null)
		{
			if(t.parent.left == t)
				t.parent.left = t.right;
			else
				t.parent.right = t.right;
			
			t.right.parent = t.parent;
		}
		else
		{
			if(t.parent.left == t)
				t.parent.left = t.left;
			else
				t.parent.right = t.left;
			
			t.left.parent = t.parent;
		}
	}

	protected void case3L(AbsTree t) { // replace t.value and t.count
		AbsTree temp = t.left.max();
		t.value = temp.value;
		t.left.delete(t.value);
	}

	protected void case3R(AbsTree t) { // replace t.value
		AbsTree temp = t.right.min();
		t.value = temp.value;
		t.right.delete(t.value);
	}

	private AbsTree find(int n) {
		if (this.value == n)
			return this;
		else if (this.value < n && this.right != null)
			return this.right.find(n);
		else if (this.value > n && this.left != null)
			return this.left.find(n);
		else
			return null;
	}

	public AbsTree min() {
		if (this.left != null)
			return this.left.min();
		else
			return this;
	}

	public AbsTree max() {
		if (this.right != null)
			return this.right.max();
		else
			return this;
	}

	protected int value;
	protected AbsTree left;
	protected AbsTree right;
	protected AbsTree parent;

	protected abstract AbsTree add_node(int n);
	protected abstract void count_duplicates();
	protected abstract int get_count();
	protected abstract void set_count(int v);
}

class Tree extends AbsTree {
	public Tree(int n) {
		super(n);
	}

	protected AbsTree add_node(int n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
		;
	}

	protected int get_count() {
		return 1;
	}

	protected void set_count(int v) {
		//no need to write anything
	}
}

class DupTree extends AbsTree {
	public DupTree(int n) {
		super(n);
		count = 1;
	};

	protected AbsTree add_node(int n) {
		return new DupTree(n);
	}

	protected void count_duplicates() {
		count++;
	}

	protected int get_count() {
		return count;
	}

	protected void set_count(int v) {
		count = v;
	}

	protected int count;
}
