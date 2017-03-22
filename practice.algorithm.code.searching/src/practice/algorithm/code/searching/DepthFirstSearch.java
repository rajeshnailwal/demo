package practice.algorithm.code.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import practice.algorithm.code.searching.data.Data;
import practice.algorithm.code.searching.data.TreeNode;
import practice.algorithm.code.searching.data.Tree;

public class DepthFirstSearch<E extends Comparable<E>> {
	
	public static void main(String...strings){
		List<Data<Integer>> data = new ArrayList<Data<Integer>>(){
			{
				/**
				 * 							 56
				 *  			 245  		     		  43
				 * 		    1          23           90          12   
				 *      32    123    6    108    59    98    22    809
				 * 678
				 */
				add(new Data<Integer>(56));
				add(new Data<Integer>(245));
				add(new Data<Integer>(43));
				add(new Data<Integer>(1));
				add(new Data<Integer>(23));
				add(new Data<Integer>(90));
				add(new Data<Integer>(12));
				add(new Data<Integer>(32));
				add(new Data<Integer>(123));
				add(new Data<Integer>(6));
				add(new Data<Integer>(108));
				add(new Data<Integer>(59));
				add(new Data<Integer>(98));
				add(new Data<Integer>(22));
				add(new Data<Integer>(809));
				add(new Data<Integer>(678));
			}
		};
		
		TreeNode<Integer> root = new Tree<Integer>().getCompleteBinaryTree(data.toArray(new Data[data.size()]));
		
		TreeNode<Integer> found = new DepthFirstSearch<Integer>().search(root, new Data<Integer>(98));
	}
	
	public TreeNode<E> search(TreeNode<E> root, Data<E> searchData){
		Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
		
		TreeNode<E> node = root, result = null;
		stack.push(root);
		
		while((node = stack.pop()) != null){
			//do whatever you want do with node at this step
			System.out.println(node.data.getData());
			if(node.data.getData().equals(searchData.getData())){
				result = node;
				break;
			}
			
			//after doing operation on node go for children of the node
			if(node.right != null) stack.push(node.right);
			if(node.left != null) stack.push(node.left);			
		}
		
		return result;
	}

}
