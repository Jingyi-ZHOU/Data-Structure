/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

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

//Below is AKUN's code
//public class LinkedListImpl implements LIST_Interface {
//	Node sentinel; // this will be the entry point to your linked list (the head)
//	int numElts = 0;
//
//	public LinkedListImpl() {// this constructor is needed for testing purposes. Please don't modify!
//		sentinel = new Node(0); // Note that the root's data is not a true part of your data set!
//	}
//
//	// implement all methods in interface, and include the getRoot method we made
//	// for testing purposes. Feel free to implement private helper methods!
//
//	public Node getRoot() { // leave this method as is, used by the grader to grab your linkedList easily.
//		return sentinel;
//	}
//
//	@Override
//	public boolean insert(double elt, int index) {
//		Node newNode = new Node(elt);
//		boolean insertStatus = false;
//		if (index < 0 || index > size()) {
//			return insertStatus;
//		}
//		if (size() == 0 && index == 0) {
//			sentinel.next = newNode;
//			newNode.next = sentinel;
//			newNode.prev = sentinel;
//			sentinel.prev = newNode;
//
//			insertStatus = true;
//			numElts++;
//		} else if (index==size()) {
//			Node preNode=getNode(index-1);
//			preNode.next=newNode;
//			newNode.prev=preNode;
//			newNode.next=sentinel;
//			sentinel.prev=newNode;
//			insertStatus = true;
//			numElts++;
//		}else{
//			Node currNode = getNode(index);
//			Node preNode = currNode.prev;
//
//			newNode.prev = preNode;
//			newNode.next = currNode;
//
//			currNode.prev = newNode;
//			preNode.next = newNode;
//
//			insertStatus = true;
//			numElts++;
//		}
//
//		return insertStatus;
//	}
//
//	@Override
//	public boolean remove(int index) {
//		boolean removeStatus = false;
//		if (index > size() || index < 0) {
//			return removeStatus;
//		}
//
//		Node preNode = getNode(index).prev;
//		Node nextNode = getNode(index).next;
//		preNode.next = nextNode;
//		nextNode.prev = preNode;
//		removeStatus=true;
//		numElts--;
//
//		
//		return removeStatus;
//	}
//
//	@Override
//	public double get(int index) {
//		double nodeValue = Double.NaN;
//		if (index < 0 || index > size() - 1) {
//			return nodeValue;
//		} else {
//			if (index==0) {
//				nodeValue= sentinel.next.getData();
//			} else 	if (index < size() / 2) {
//				for (int i = 0; i < index; i++) {
//					nodeValue = sentinel.next.getData();
//				}
//			} else {
//				for (int i = size(); i > index; i--) {
//					nodeValue = sentinel.prev.getData();
//				}
//			}
//
//		}
//
//		return nodeValue;
//	}
//
//	@Override
//	public int size() {
//		// TODO Auto-generated method stub
//		return numElts;
//	}
//
//	@Override
//	public boolean isEmpty() {
//		// TODO Auto-generated method stub
//		return size() == 0;
//	}
//
//	@Override
//	public void clear() {
//		sentinel = new Node(0);
//		numElts = 0;
//
//	}
//
//	private Node getNode(int index) {
//		Node node = null;
//		if (index == 0) {
//			node=sentinel.next;
//		} else if (index < size() / 2) {
//			node=sentinel.next;
//			for (int i = 0; i < index-1; i++) {
//				node = node.next;
//			}
//		} else {
//			node=sentinel.prev;
//			for (int i = size()-1; i > index; i--) {
//				node = node.prev;
//			}
//		}
//
//		return node;
//
//	}
//
//}