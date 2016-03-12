import java.util.ArrayList;
import java.util.Scanner;

import com.nailwal.rajesh.data.TreeNode;

public class ArraysAndSimpleQuries {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int queryCount = scanner.nextInt();
        ArrayList<TreeNode<Integer>> list = new ArrayList<TreeNode<Integer>>(size);
        
        for(int i = 0; i < size; ++i){
            TreeNode<Integer> node = new TreeNode<Integer>();
            node.data.setData(scanner.nextInt());
            list.add(i, node);
        }
        
        // create binary tree from array
        for(int i = 0; i < size/2; ++i){
        	TreeNode<Integer> node = list.get(i);
            node.left = list.get(i * 2 + 1);
            if((i * 2 + 2) < list.size()){
                node.right = list.get(i * 2 + 2);   
            }
        }
        TreeNode<Integer> root = list.get(0);
        assignSize(root);
        
        for(int i = 0; i < queryCount; ++i){
            int op = scanner.nextInt();
            TreeNode<Integer>[] array = list.toArray(new TreeNode[list.size()]);
            TreeNode<Integer>[] nodes = mark(array, scanner.nextInt(), scanner.nextInt());
            for(int j = nodes.length - 1; j >= 0; --j){
            	TreeNode<Integer>[] subTrees = split(nodes[j]);
                if(subTrees[0] == null){
                    
                }
            }
        }
    }
    
    private static TreeNode[] mark(TreeNode<Integer>[] array, int start, int end){
    	TreeNode[] markedNodes = new TreeNode[end - start + 1];
    	
        int i = 0;
        while(start <= end){
            markedNodes[i] = array[start];
            ++start; ++i;
        }
        return markedNodes;
    }
    
    private static TreeNode[] split(TreeNode<Integer> node){
    	TreeNode[] subRoots = new TreeNode[2];
        subRoots[0] = node.left;
        subRoots[1] = node.right;
        return subRoots;
    }
    
    private static TreeNode<Integer> merge(){
        return null;
    }
    
    private static void rearrangeArray(TreeNode<Integer> node, TreeNode<Integer>[] array, int index){
        if(index < array.length){
            array[index] = node;
            rearrangeArray(node.left, array, 2 * index + 1);
            rearrangeArray(node.right, array, 2 * index + 2);
        }
    }
           
    private static int assignSize(TreeNode<Integer> node){
        int size = 0;
        
        if(node != null){
            int leftSize = assignSize(node.left);
            int rightSize = assignSize(node.right);
            
            size = leftSize + rightSize + 1;
            node.size = size;
        }
        
        return size;
    }

}
