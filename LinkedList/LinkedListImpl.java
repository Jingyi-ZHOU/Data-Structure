package LinkedList;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel; // this will be the entry point to your linked list (the head)
	int size;

	public LinkedListImpl() {// this constructor is needed for testing purposes. Please don't modify!
		sentinel = new Node(0); // Note that the root's data is not a true part of your data set!
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	public LinkedListImpl(double i) {
		sentinel = new Node(0);
		sentinel.next = new Node(i);
		sentinel.next.prev = sentinel;
		size = 1;
	}

	// implement all methods in interface, and include the getRoot method we made
	// for testing purposes. Feel free to implement private helper methods!

	public Node getRoot() { // leave this method as is, used by the grader to grab your linkedList easily.
		return sentinel;
	}

	@Override
	public boolean insert(double elt, int index) {
		if (index > size)
			return false;
		Node in = new Node(elt);
		Node last;
		if (index == 0) {
			last = sentinel;
		} else {
			last = find(index - 1);
		}
		in.next = last.next;
		in.prev = last;
		if (in.next != null)
			in.next.prev = in;
		in.prev.next = in;
		size++;
		return true;
	}

	@Override
	public boolean remove(int index) {
		if (index < 0 || index > size - 1)
			return false;
		Node rem = find(index);
		rem.prev.next = rem.next;
		rem.next.prev = rem.prev;
		size--;
		return true;
	}

	@Override
	public double get(int index) {
		if (index < 0 || index > size - 1)
			return Double.NaN;
		return find(index).data;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	private Node find(int index) {
		int count = 0;
		Node ret = sentinel;
		while (count < index) {
			ret = ret.next;
			count++;
		}
		return ret.next;
	}
}
