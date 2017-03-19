package practice.algorithm.code.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import practice.algorithm.code.searching.data.Data;
import practice.algorithm.code.searching.data.Node;
import practice.algorithm.code.searching.data.Tree;

public class BinarySearch<E extends Comparable<E>> {
	
	public static void main(String...strings){
		List<Data<Integer>> list = new ArrayList<Data<Integer>>(){
			{
				Random sequenceGenerator = new Random(18945672);
				
				for(int i = 0; i < 25; i++){
					add(new Data<Integer>(sequenceGenerator.nextInt(1000)));
				}
				
				add(new Data<Integer>(567));
			}
		};
		
		System.out.println("Data List : "+list);
		
		Node<Integer> root = new Tree<Integer>().getBinraySearchTree(list);
		Node<Integer> node = new BinarySearch<Integer>().search(root, new Data<Integer>(567));
		System.out.println("Node found "+node);
	}
	
	public Node<E> search(Node<E> root, Data<E> searchData){
		
		Node<E> node = null, nd = root;
		
		while(nd != null){
			if(nd.data.getData().compareTo(searchData.getData()) == 0){
				node = nd;
				break;
			} else if(nd.data.getData().compareTo(searchData.getData()) < 0){
				nd = nd.right;
			} else if(nd.data.getData().compareTo(searchData.getData()) > 0){
				nd = nd.left;
			}
		}
		
		return node;
	}

}
