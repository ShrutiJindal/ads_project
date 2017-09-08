#
# A simple makefile for compiling three java classes
#

# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

# typing 'make' will invoke the first target entry in the makefile 
# (the default one in this case)
#
default: TreeNode.class MinBinaryHeap.class HuffmanTree.class encoder.class GetBitArray.class decoder.class

# this target entry builds the Average class
# the Average.class file is dependent on the Average.java file
# and the rule associated with this entry gives the command to create it
#
TreeNode.class: TreeNode.java
	$(JCC) $(JFLAGS) TreeNode.java	
	
MinBinaryHeap.class: MinBinaryHeap.java
	$(JCC) $(JFLAGS) MinBinaryHeap.java	
	
HuffmanTree.class: HuffmanTree.java
	$(JCC) $(JFLAGS) HuffmanTree.java

encoder.class: encoder.java
	$(JCC) $(JFLAGS) encoder.java
GetBitArray.class: GetBitArray.java
	$(JCC) $(JFLAGS) GetBitArray.java
decoder.class: decoder.java
	$(JCC) $(JFLAGS) decoder.java
		
# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean: 
	$(RM) *.class

