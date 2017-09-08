class GetBitArray {
	int i = 0;
	int k = 0;
	byte[] bitArray;
	final static char[] masks = { 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01 };

	public GetBitArray(byte[] byteArray) {
		this.bitArray = byteArray;
	}

	boolean getNextBit() {
		if (k == 8) {
			i++;
			k = 0;
		}
		return (bitArray[i] & masks[k++]) > 0;
	}

	boolean hasNext() {
		return !(k == 8 && i == (bitArray.length - 1));
	}
}