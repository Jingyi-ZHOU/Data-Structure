package SPLT;

public class SPLT implements SPLT_Interface {
	private BST_Node root;
	private int size;
	private static BST_Node header = new BST_Node(null);// for splay
	private static BST_Node nullNode = new BST_Node(null);

	public SPLT() {
		this.size = 0;
		root = null;
		nullNode.left = nullNode.right = nullNode;
	}

	public BST_Node getRoot() { // for test
		return root;
	}

	@Override
	public boolean insert(String s) {
		if (empty()) {
			root = new BST_Node(s);
			size += 1;
			return true;
		}

		if (contains(s)) {
			return false;
		} else {
			boolean b = root.insertNode(s);
			root = splay(s, root);
			size += 1;
			return b;
		}
	}

	@Override
	public boolean remove(String s) {
		boolean b = false;
		if (!contains(s)) {
			return false;
		} else {
			if (root.data.equals(s) && size == 1) {
				root = null;
				b = true;
				size -= 1;
			} else {
				b = root.removeNode(s);
				size -= 1;
			}
		}
		return b;
	}

	@Override
	public String findMin() {
		if (empty()) {
			return null;
		} else {
			String s = root.findMin().data;
			root = splay(s, root);
			return s;
		}
	}

	@Override
	public String findMax() {
		if (empty()) {
			return null;
		} else {
			String s = root.findMax().data;
			root = splay(s, root);
			return s;
		}
	}

	@Override
	public boolean empty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		if (empty()) {
			return false;
		}
		root = splay(s, root);
		return root.containsNode(s);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

	/*
	 * When to splay 1. insert: contain -> splay it to root; not contain -> insert
	 * and to root 2. contain: contain -> splay to root; not contain -> the previous
	 * one to root 3. findMin(Max) 4. remove: contain -> findMax on L -> make R the
	 * right child of L
	 */
	private BST_Node splay(String s, BST_Node r) {
		if (r == null)
			return null;
		BST_Node leftMax, rightMin;
		header.left = header.right = nullNode;
		leftMax = rightMin = header;
		while (true) {
			if (r.data.compareTo(s) > 0) {
				// s is in the left
				
				if (r.left != null && r.left.data.compareTo(s) > 0) {
					// left left
					r = rotateLeft(r);
				}
				if (r.left == null)
					break;
				rightMin.left = r;
				rightMin = r;
				r = r.left;

			} else if (r.data.compareTo(s) < 0) {
				// s is in the right
				if (r.right != null && r.right.data.compareTo(s) < 0) {
					// right right
					r = rotateRight(r);
				}
				if (r.right == null) {
					break;
					
				}
				// 将节点添加到leftMax.right
				leftMax.right = r;
				leftMax = r; // leftMax = leftMax.right
				r = r.right;

			} else {
				// s is in the root
				break;
			}

		}
		// header固定，leftMax&rightMin是curr
		leftMax.right = r.left;
		rightMin.left = r.right;
		r.left = header.right;
		r.right = header.left;
		return r;

	}

	private BST_Node rotateLeft(BST_Node p) {
		BST_Node x = p.left;
		p.left = x.right;
		x.right = p;
		return x;

	}

	private BST_Node rotateRight(BST_Node p) {
		BST_Node x = p.right;
		p.right = x.left;
		x.left = p;
		return x;

	}

}
