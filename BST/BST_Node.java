package BST;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data) {
		this.data = data;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is
	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

// --- end used for testing -------------------------------------------
//--- fill in these methods ------------------------------------------
	//
	// at the moment, they are stubs returning false
	// or some appropriate "fake" value
	//
	// you make them work properly
	// add the meat of correct implementation logic to them
	// you MAY change the signatures if you wish...
	// make the take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations
	/*
	 * public boolean containsNode(String s){ return false; } public boolean
	 * insertNode(String s){ return false; } public boolean removeNode(String s){
	 * return false; } public BST_Node findMin(){ return left; } public BST_Node
	 * findMax(){ return right; } public int getHeight(){ return 0; }
	 */
	// --- end fill in these methods --------------------------------------
	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------
	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}

	public int getHeight() {
		if(this==null) return -1;
		return height(this);
	}
	
	public int height(BST_Node root) {
		if(root==null) return -1;
		return 1+Math.max(height(root.left),height(root.right));
	}
	
	
	

}
