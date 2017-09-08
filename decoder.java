import java.io.*;

public class decoder {
	public static void decode(String codeTableFile,String enodedFile) {
		try {
			TreeNode rootNode = new TreeNode(-1, -1);
			String in = null;
			String fName = codeTableFile;
			FileReader fr = new FileReader(fName);
			BufferedReader br = new BufferedReader(fr);
			while ((in = br.readLine()) != null) {
				String[] line = in.split("\\s+");
				createTree(line[0], line[1], rootNode);
			}
			br.close();

			File encodedFile = new File(enodedFile);
			byte[] encBytes = new byte[(int) encodedFile.length()];
			int totalByteread = 0;
			InputStream input = new BufferedInputStream(new FileInputStream(encodedFile));
			while (totalByteread < encBytes.length) {
				int bytesReadLeft = encBytes.length - totalByteread;
				int bytesRead = input.read(encBytes, totalByteread, bytesReadLeft);
				if (bytesRead > 0) {
					totalByteread = totalByteread + bytesRead;
				}
			}
			input.close();
			

			StringBuilder sb = new StringBuilder("");
			FileWriter fw = new FileWriter("decoded.txt");
			BufferedWriter br_out = new BufferedWriter(fw);
			GetBitArray bitItr = new GetBitArray(encBytes);
			TreeNode treeTraversal = rootNode;

			while (bitItr.hasNext()) {
				if (bitItr.getNextBit()) {
					treeTraversal = treeTraversal.rChild;
				} else {
					treeTraversal = treeTraversal.lChild;
				}
				if (treeTraversal.key != -1) {
					sb.append(treeTraversal.key + "\n");
					treeTraversal = rootNode;
				}
			}
			br_out.write(sb.toString());
			br_out.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Error occured '" + e + "'");
		}
	}

	private static void createTree(String s1, String s2, TreeNode troot) {
		StringBuilder path = new StringBuilder(s2);
		int ind = 0;
		while (ind < path.length() - 1) {
			if (path.charAt(ind) == '0' && troot.lChild != null) {
				troot = troot.lChild;}
			else if (path.charAt(ind) == '0' && troot.lChild == null) {
				TreeNode tempNode = new TreeNode(-1, -1);
				troot.lChild = tempNode;
				troot = tempNode;
			}  
			else if (path.charAt(ind) == '1' && troot.rChild != null) {
				troot = troot.rChild;
			}
			else if (path.charAt(ind) == '1' && troot.rChild == null) {
				TreeNode temp = new TreeNode(-1, -1);
				troot.rChild = temp;
				troot = temp;
			} 
			ind++;
		}

		if (ind == path.length() - 1) {
			TreeNode tempNode = new TreeNode(-1, Integer.parseInt(s1));
			if (path.charAt(ind) == '0') {
				troot.lChild = tempNode;
			} else {
				troot.rChild = tempNode;
			}
		}

	}
	
	public static void main(String[] args)
	{
		String encodedFile = args[0];
		String codeTableFile = args[1];
		decode(codeTableFile,encodedFile);
	}
}
