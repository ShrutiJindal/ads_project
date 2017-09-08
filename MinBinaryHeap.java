
import java.util.ArrayList;

public class MinBinaryHeap {
	
	public static int Parent(int index) {
		return (index - 1) / 2;
	}

	public static int left(int index) {
		return 2 * index + 1;
	}

	public static int right(int index) {
		return 2 * index + 2;
	}

	public static ArrayList<TreeNode> createHeap(ArrayList<TreeNode> nodeSet) {
		int i = nodeSet.size() / 2 - 1;
		while(i >= 0 ) {
			heapifyMin(nodeSet, i);
			i--;
		}
		return nodeSet;
	}

	public static void heapifyMin(ArrayList<TreeNode> nodeSet, int index) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int low;
		if (left <= nodeSet.size() - 1 && nodeSet.get(left).compareTo(nodeSet.get(index)) == -1) {
			low = left;
		} else {
			low = index;
		}
		if (right <= nodeSet.size() - 1 && nodeSet.get(right).compareTo(nodeSet.get(low)) == -1) {
			low = right;
		}
		if (low != index) {
			TreeNode newNode = nodeSet.get(index);
			nodeSet.set(index, nodeSet.get(low));
			nodeSet.set(low, newNode);
			heapifyMin(nodeSet, low);
		}
	}

	public static void insertNode(ArrayList<TreeNode> nodeSet, TreeNode newNode) {
		nodeSet.add(nodeSet.size(), newNode);
		increaseKeyMinHeap(nodeSet, newNode);
	}

	public static void increaseKeyMinHeap(ArrayList<TreeNode> nodeSet, TreeNode newNode) {
		int index = nodeSet.size() - 1;
		while (index > 0 && nodeSet.get(Parent(index)).compareTo(nodeSet.get(index)) == 1) {
			TreeNode tempNode = nodeSet.get(index);
			nodeSet.set(index, nodeSet.get(Parent(index)));
			nodeSet.set(Parent(index), tempNode);
			index = Parent(index);
		}
	}
	
	public static TreeNode removeMin(ArrayList<TreeNode> nodeSet) {
		if (nodeSet.size() < 0)
			return null;

		TreeNode minNode = nodeSet.get(0);
		nodeSet.set(0, nodeSet.get(nodeSet.size() - 1));
		nodeSet.remove(nodeSet.size() - 1);
		heapifyMin(nodeSet, 0);
		return minNode;
	}

}