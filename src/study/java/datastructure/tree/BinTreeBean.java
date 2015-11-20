package study.java.datastructure.tree;

public class BinTreeBean {
	// 数据域
	private String data;
	
	// 左树
	private BinTreeBean leftChild;
	
	// 右树
	private BinTreeBean rightChild;
	
	// 左标志
	private byte leftTag;
	
	// 右标志
	private byte rightTag;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BinTreeBean getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinTreeBean leftChild) {
		this.leftChild = leftChild;
	}

	public BinTreeBean getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinTreeBean rightChild) {
		this.rightChild = rightChild;
	}

	public byte getLeftTag() {
		return leftTag;
	}

	public void setLeftTag(byte leftTag) {
		this.leftTag = leftTag;
	}

	public byte getRightTag() {
		return rightTag;
	}

	public void setRightTag(byte rightTag) {
		this.rightTag = rightTag;
	}
}
