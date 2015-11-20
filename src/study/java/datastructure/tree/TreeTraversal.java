package study.java.datastructure.tree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

public class TreeTraversal {
	public static BinTreeBean myTree = null;
	static {
		myTree = new BinTreeBean();
		myTree.setData("A");
		BinTreeBean nodeB = new BinTreeBean();
		nodeB.setData("B");
		BinTreeBean nodeC = new BinTreeBean();
		nodeC.setData("C");
		BinTreeBean nodeD = new BinTreeBean();
		nodeD.setData("D");
		BinTreeBean nodeE = new BinTreeBean();
		nodeE.setData("E");
		BinTreeBean nodeF = new BinTreeBean();
		nodeF.setData("F");
		BinTreeBean nodeG = new BinTreeBean();
		nodeG.setData("G");
		myTree.setLeftChild(nodeB);
		myTree.setRightChild(nodeC);
		nodeB.setLeftChild(nodeD);
		nodeB.setRightChild(nodeE);
		nodeE.setLeftChild(nodeG);
		nodeC.setRightChild(nodeF);
	}
	
	/**
	 * 递归先序遍历
	 * @param tree
	 */
	public static void  preOrder(BinTreeBean tree) {
		if(tree != null) {
			System.out.print(tree.getData() + " ");
			preOrder(tree.getLeftChild());
			preOrder(tree.getRightChild());
		}
		
	}
	
	/**
	 * 递归中序遍历
	 * @param tree
	 */
	public static void inOrder(BinTreeBean tree) {
		if(tree != null) {
			inOrder(tree.getLeftChild());
			System.out.print(tree.getData() + " ");
			inOrder(tree.getRightChild());
		}
	}
	/**
	 * 非递归中序遍历
	 * @param tree
	 */
	public static void inOrderNonRecur(BinTreeBean tree) {
		Stack<BinTreeBean> myStack = new Stack<BinTreeBean>();
		myStack.push(tree);
		while(!myStack.isEmpty()) {
			while(myStack.peek() !=null)  {
				myStack.push(myStack.peek().getLeftChild());
				
			}
			myStack.pop();
			if(!myStack.isEmpty()) {
				BinTreeBean p = myStack.pop();
				System.out.print(p.getData() + " ");
				myStack.push(p.getRightChild());
			}
		}
		
	}
	
	/**
	 * 递归后序遍历
	 * @param tree
	 */
	public static void postOrder(BinTreeBean tree) {
		if(tree != null) {
			postOrder(tree.getLeftChild());
			postOrder(tree.getRightChild());
			System.out.print(tree.getData() + " ");
		}
	}
	
	/**
	 *  层序遍历
	 * @param tree
	 */
	public static void levelOrder(BinTreeBean tree) {
		if(tree != null) {
			Queue<BinTreeBean> queue = new LinkedBlockingDeque<BinTreeBean>();
			queue.add(tree);
			while(!queue.isEmpty()) {
				BinTreeBean  p = queue.poll();
				System.out.print(p.getData() + " ");
				if(p.getLeftChild() != null) {
					queue.add(p.getLeftChild());
				}
				if(p.getRightChild() != null) {
					queue.add(p.getRightChild());
				}
			}
		}
		
	}
	public static void main(String[] args) {
		System.out.print("递归先序遍历二叉树:");
		preOrder(myTree);
		System.out.println();
		System.out.print("递归中序遍历二叉树:");
		inOrder(myTree);
		System.out.println();
		System.out.print("非递归中序遍历二叉树:");
		inOrderNonRecur(myTree);
		System.out.println();
		System.out.print("递归后序遍历二叉树:");
		postOrder(myTree);
		System.out.println();
		System.out.print("层序遍历二叉树:");
		levelOrder(myTree);
	}
}
