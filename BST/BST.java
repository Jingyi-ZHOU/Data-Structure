package BST;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST() {
		size = 0;
		root = null;
	}

	@Override
	// used for testing
	public BST_Node getRoot() {
		return root;
	}

	@Override
	public boolean insert(String s) {
		if (root == null) {
			root = new BST_Node(s);
			size++;
			return true;
		} else {
			boolean res = insert(root, s);
			if (res)
				size++;
			return res;
		}
	}

	private boolean insert(BST_Node root, String s) {
		if (s.compareTo(root.data) == 0) {
			return false;
		} else if (s.compareTo(root.data) > 0) {
			if (root.right == null) {
				root.right = new BST_Node(s);
				return true;
			}
			return insert(root.right, s);
		} else {
			if (root.left == null) {
				root.left = new BST_Node(s);
				return true;
			}
			return insert(root.left, s);
		}
	}

	@Override
	public boolean remove(String s) {
		if(root == null || !contains(s)) return false;
		root = remove(root,s);
		size --;
		return true;
	}

	private BST_Node remove(BST_Node root, String s) {
		if(root == null) return root;
		if (s.compareTo(root.data) == 0) {
			if (root.left == null && root.right == null) {
				return null;
			} else if (root.right == null) {
				return root.left;
			} else if (root.left == null){
				return root.right;
			}else {
				root.data = findMin(root.right).data;
				root.right = remove(root.right,root.data);
			}
		} else if (s.compareTo(root.data) > 0) {
			root.right = remove(root.right, s);
		} else {
			root.left = remove(root.left, s);
		}
		return root;
	}


	@Override
	public String findMin() {
		if (root == null)
			return null;
		return findMin(root).data;
	}

	private BST_Node findMin(BST_Node root) {
		if (root == null)
			return null;
		if (root.left == null)
			return root;
		return findMin(root.left);
	}

	@Override
	public String findMax() {
		if (root == null)
			return null;
		return findMax(root).data;
	}

	private BST_Node findMax(BST_Node root) {
		if (root == null)
			return null;
		if (root.right == null)
			return root;
		return findMax(root.right);
	}

	@Override
	public boolean empty() {
		return size == 0;
	}

	@Override
	public boolean contains(String s) {
		if (root == null)
			return false;
		return contains(root, s);
	}

	private boolean contains(BST_Node root, String s) {
//		if (root == null)
//			return false;
		if (s.compareTo(root.data) == 0)
			return true;
		if (s.compareTo(root.data) > 0) {
			if (root.right == null) {
				return false;
			} else {
				return contains(root.right, s);
			}
		}
		if (s.compareTo(root.data) < 0) {
			if (root.left == null) {
				return false;
			} else {
				return contains(root.left, s);
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if (size == 0)
			return -1;
		return height(root);
	}

	public int height(BST_Node root) {
		if (root == null)
			return -1;
		return 1 + Math.max(height(root.left), height(root.right));
	}

}
