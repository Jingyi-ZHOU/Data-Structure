package SPLT_A4;
// ref: http://users.cis.fiu.edu/~weiss/dsaajava/code/DataStructures/SplayTree.java

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

	public BST_Node getRoot() { // please keep this in here! I need your root node to test your tree!
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
	/*
	 * 在自顶向下(top-down)的实现中，需要将输入的树拆成三颗树，分别为左树L，中树M和右树R。其中M树维护当前还未被访问到的节点，
	 * L树中所有节点的值都小于M树中的任何节点值，R树中所有节点的值都大于M树中的任何节点值。L树中只需要知道当前的最大节点
	 * (leftTreeMax)，而R树中只需要知道当前的最小节点(rightTreeMin)。左右两棵树的根节点分别可以通过nullNode节点（
	 * 它是leftTreeMax和rightTreeMin的初始值，而且splay过程中变量nullNode本身未变化,只改变它的左右孩子节点）
	 * 的右和左孩子节点得到，因为leftTreeMax中加入一个新的节点或子树时都是将新的节点作为leftTreeMax的右孩子，而不是左孩子（注意这里的顺序）
	 * ，rightTreeMin跟leftTreeMax相反。自顶向下的zig-zig或zag-zag需要做旋转操作，zig-
	 * zig的旋转操作叫rotationWithLeftChild,旋转后目标节点的父节点和祖父节点加入R树，zag-
	 * zag的旋转操作叫rotationWithRightChild,旋转后目标节点的父节点和祖父节点加入L树。另外zig-zag或zag-
	 * zig可以分别简化为zig或zag操作，这样可以将zig-zag和zig合二为一，从而只需考虑一种情况，而不需要将两种情况单独考虑。
	 * zig操作将目标节点的父节点加入R树，zag操作将目标节点的父节点加入L树。
	 * 注意L和R树中每次加入新节点都需更新变量leftTreeMax或rightTreeMin。自顶向下splay操作的最后一步是重组(re-assemble)
	 * ：将M树的左孩子设置为L树的根节点，将M树的右孩子设置为R树的根节点，然后M树原来的左孩子成为leftTreeMax的右孩子，
	 * M树原来的右孩子成为rightTreeMin的左孩子。
	 * 
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