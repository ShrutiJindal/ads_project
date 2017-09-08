import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HuffmanTree {

	static HashMap<Integer, String> cTable = new HashMap<Integer, String>();
	

	public static ArrayList<TreeNode> makeHuffmanTreeFromMinBHeap(ArrayList<TreeNode> minHeap) {
		while (minHeap.size() > 1) {
			TreeNode node1 = MinBinaryHeap.removeMin(minHeap);
			TreeNode node2 = MinBinaryHeap.removeMin(minHeap);
			TreeNode node = new TreeNode(node1.keyFreq + node2.keyFreq, -1);
			node.lChild = node1;
			node1.p = node;
			node.rChild = node2;
			node2.p = node;
			MinBinaryHeap.insertNode(minHeap, node);
		}
		return minHeap;
	}

	public static void assignEncoding(TreeNode rootNode) {
		if (rootNode.p != null && rootNode == rootNode.p.lChild) {
			rootNode.path.append(rootNode.p.path);
			rootNode.path.append("0");
		}
		if (rootNode.p != null && rootNode == rootNode.p.rChild) {
			rootNode.path.append(rootNode.p.path);
			rootNode.path.append("1");
		}
		if (rootNode.lChild != null)
			assignEncoding(rootNode.lChild);
		if (rootNode.rChild != null)       ////check
			assignEncoding(rootNode.rChild);
	}

	public static HashMap<Integer, String> createCodeTable(TreeNode rootNode) {
		if (rootNode.lChild != null)
			createCodeTable(rootNode.lChild);
		if (rootNode.key != -1) {
			cTable.put(rootNode.key, rootNode.path.toString());
		}
		if (rootNode.rChild != null)
			createCodeTable(rootNode.rChild);

		return cTable;
	}

	public static void putCodeTableValues(HashMap<Integer, String> hmap) {
		StringBuilder s1 = new StringBuilder("");
		Set temp = hmap.entrySet();
		Iterator iter = temp.iterator();
		while (iter.hasNext()) {
			Map.Entry mp = (Map.Entry) iter.next();
			s1.append((int) mp.getKey() + " " + hmap.get((int) mp.getKey()) + "\n");
		}

		String fName = "code_table.txt";
		try {
			FileWriter fw = new FileWriter(fName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(s1.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
