
public class TreeNode implements Comparable<TreeNode> {
	TreeNode lChild;
	TreeNode rChild;
	TreeNode p;
	int keyFreq;
	int key;
	StringBuilder path;

	public TreeNode(int freq, int k) {
		this.lChild = null;
		this.rChild = null;
		this.p = null;
		this.keyFreq = freq;
		this.key = k;
		this.path = new StringBuilder();
	}

	public int compareTo(TreeNode treeNode) {
		if (this.keyFreq < treeNode.keyFreq)
			return -1;
		else if (this.keyFreq > treeNode.keyFreq)
			return 1;
		else
			return 0;
	}

	public String toString() {
		return Integer.toString(keyFreq) + " " + Integer.toString(key);
	}
}