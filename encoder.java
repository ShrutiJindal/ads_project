import java.io.*;
import java.util.*;

public class encoder {

	public static void heapEncoder(ArrayList<Integer> input, HashMap<Integer, String> codeTable) {
		StringBuilder encodedForm = new StringBuilder("");
		Iterator iter = input.iterator();
        
		while (iter.hasNext()) {
			int t = (int) iter.next();
			encodedForm.append(codeTable.get(t));
		}

		byte[] byteArray = new byte[encodedForm.length() / 8];
		for (int i = 0; i < (encodedForm.length() / 8); i++) {
			byteArray[i] = (byte) Short.parseShort(encodedForm.substring(8 * i, 8 * (i + 1)), 2);
		}

		String file = "encoded.bin";
		try {
			OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			out.write(byteArray);
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
			HashMap<Integer, Integer> freqTable = new HashMap<Integer, Integer>();
			ArrayList<Integer> inputTable = new ArrayList<Integer>();

			String file = args[0];
			String inputLine = null;
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while ((inputLine = br.readLine()) != null) {
					inputTable.add(Integer.parseInt(inputLine));
					if (freqTable.containsKey(Integer.parseInt(inputLine))) {
						int valCount = freqTable.get(Integer.parseInt(inputLine));
						freqTable.put(Integer.parseInt(inputLine), ++valCount);
					} else {
						freqTable.put(Integer.parseInt(inputLine), 1);
					}
				}

				br.close();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();

			}
			
			Set keyset = freqTable.entrySet();
			ArrayList<TreeNode> freqCount = new ArrayList<TreeNode>();
			Iterator iter = keyset.iterator();
			while (iter.hasNext()) {
				Map.Entry mp = (Map.Entry) iter.next();
				TreeNode node = new TreeNode((int) mp.getValue(), (int) mp.getKey());
				freqCount.add(node);
			}
			
			  long start = System.currentTimeMillis(); 
			  ArrayList<TreeNode> huff = new ArrayList<TreeNode>(); 
			  ArrayList<TreeNode> binaryHeapNodeArray = MinBinaryHeap.createHeap(freqCount); 
			  huff = HuffmanTree.makeHuffmanTreeFromMinBHeap(binaryHeapNodeArray);
			  HuffmanTree.assignEncoding(huff.get(0));
			  
			  HashMap<Integer, String> codeTable = new HashMap<Integer, String>();
			  codeTable = HuffmanTree.createCodeTable(huff.get(0));
			  
			  HuffmanTree.putCodeTableValues(codeTable);
	
			  
			  encoder.heapEncoder(inputTable, codeTable);
			  

	}

}
