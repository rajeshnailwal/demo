package practice.algorithm.code.datastructures;

import java.util.Random;

import practice.algorithm.code.datastructures.data.Data;
import practice.algorithm.code.datastructures.data.RedBlackTreeNode;
import practice.algorithm.code.datastructures.data.RedBlackTreeNode.COLOR;

public class RedBlackTree {
	
	private RedBlackTreeNode root;
	private static long seed = 678546;
	
	public static void main(String...strings){
		Random rand = new Random(seed);
		RedBlackTree tree = new RedBlackTree();
		for(int i = 0; i < 40; ++i){
			int data = (int)(1000 * rand.nextDouble());
			tree.insert(new Data(data));
		}
		
		//tree.preOrderTraversal(tree.getRoot());
		
		System.out.println("==============Deletion starting=================");
		
		tree.delete(new Data(53));
		/*tree.delete(new Data(780));
		tree.delete(new Data(721));*/
		
		tree.preOrderTraversal(tree.getRoot());
	}
	
	public RedBlackTreeNode getRoot(){
		return root;
	}
	
	public RedBlackTreeNode insert(Data data){
		
		RedBlackTreeNode node = root;
		RedBlackTreeNode newNode = new RedBlackTreeNode(data);
		RedBlackTreeNode parent = null;
				
		if(root == null){
			root = newNode;
		} else {
			
			while(node != null){
				if(data.getData().compareTo(node.data.getData()) < 0){
					parent = node;
					node = node.left;
				} else if (data.getData().compareTo(node.data.getData()) > 0) {
					parent = node;
					node = node.right;
				} else {
					return node;
				}
			}
			
			if(data.getData().compareTo(parent.data.getData()) < 0){
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
			newNode.parent = parent;
		}
		
		node = newNode;
		parent = node.parent;
		
		//when parent color is red then balancing is required
		while(parent != null && parent.color == COLOR.RED && node.color == COLOR.RED){
			
			//grandpa will be black if it is not black then tree was already not a red black tree 
			//as grandpa(red) and parent(red) would be violating the condition of red black tree
			RedBlackTreeNode grandpa = parent.parent;
			RedBlackTreeNode greatgrandpa = grandpa != null ? grandpa.parent : null;;
			RedBlackTreeNode uncle = null;
			
			if(grandpa != null){
				
				if(grandpa.left == parent) {//when node is added in left sub tree uncle will be on right sub tree
					uncle = grandpa.right;					
					
					//case 1 - when uncle is red
					if(uncle != null && uncle.color == COLOR.RED){//when uncle is red
						parent.color = COLOR.BLACK;
						uncle.color = COLOR.BLACK;
						grandpa.color = COLOR.RED;
						
						//assign node for next iteration
						node = grandpa;
						parent = node.parent;
					}
					

					else {//when uncle is null or black
						
						//case 2 - when node is left-left
						if(parent.left == node){
							//rotate right at grandpa
							
							if(greatgrandpa != null){
								if(greatgrandpa.left == grandpa) greatgrandpa.left = parent;
								else greatgrandpa.right = parent;
								parent.parent = greatgrandpa;
							}
							
							grandpa.parent = parent;
							
							grandpa.left = parent.right;
							if(parent.right != null) parent.right.parent = grandpa;
							
							parent.right = grandpa;
							
							parent.color = COLOR.BLACK;
							grandpa.color = COLOR.RED;
							
							node = parent;
							parent = parent.parent;
							
						} 
						
						//case 3 - when node is left-right
						else {
							//rotate left at parent
							
							node.parent = grandpa;
							grandpa.left = node;
							
							parent.right = node.left;
							if(node.left != null) node.left.parent = parent;
							
							node.left = parent;
							parent.parent = node;
							
							//rotate right at grandpa
							RedBlackTreeNode temp = node;
							node = parent;
							parent = temp;
							
							
							if(greatgrandpa != null){
								if(greatgrandpa.left == grandpa) greatgrandpa.left = parent;
								else greatgrandpa.right = parent;
								parent.parent = greatgrandpa;
							}
							
							grandpa.parent = parent;
							
							grandpa.left = parent.right;
							if(parent.right != null) parent.right.parent = grandpa;
							
							parent.right = grandpa;
							parent.parent = greatgrandpa;
							
							parent.color = COLOR.BLACK;
							grandpa.color = COLOR.RED;
							
							node = parent;
							parent = parent.parent;							
						}
					}
					
				} else {//when node is added in right sub tree uncle will be on left sub tree
					uncle = grandpa.left;					
					
					//case 1 (mirror) - when uncle is red
					if(uncle != null && uncle.color == COLOR.RED){//when uncle is red
						parent.color = COLOR.BLACK;
						uncle.color = COLOR.BLACK;
						grandpa.color = COLOR.RED;
						
						//assign node for next iteration
						node = grandpa;
						parent = node.parent;
					}
					

					else {//when uncle is null or black
						
						//case 2 (mirror)- when node is right-right
						if(parent.right == node){
							//rotate left at grandpa
							
							if(greatgrandpa != null){
								if(greatgrandpa.left == grandpa) greatgrandpa.left = parent;
								else greatgrandpa.right = parent;
								parent.parent = greatgrandpa;
							}
							
							grandpa.parent = parent;
							
							grandpa.right = parent.left;
							if(parent.left != null) parent.left.parent = grandpa;
							
							parent.left = grandpa;
							parent.parent = greatgrandpa;
							
							parent.color = COLOR.BLACK;
							grandpa.color = COLOR.RED;
							
							node = parent;
							parent = parent.parent;
						} 
						
						//case 3 (mirror)- when node is right-left
						else {

							//rotate right at parent
							
							node.parent = grandpa;
							grandpa.right = node;
							
							parent.left = node.right;
							if(node.right != null) node.right.parent = parent;
							
							node.right = parent;
							parent.parent = node;
							
							//rotate left at grandpa
							RedBlackTreeNode temp = node;
							node = parent;
							parent = temp;
							
							
							if(greatgrandpa != null){
								if(greatgrandpa.left == grandpa) greatgrandpa.left = parent;
								else greatgrandpa.right = parent;
								parent.parent = greatgrandpa;
							}
							
							grandpa.parent = parent;
							
							grandpa.right = parent.left;
							if(parent.left != null) parent.left.parent = grandpa;
							
							parent.left = grandpa;
							parent.parent = greatgrandpa;
							
							parent.color = COLOR.BLACK;
							grandpa.color = COLOR.RED;
							
							node = parent;
							parent = parent.parent;							
						}
					}
					
				}
				
				
			} else {
				parent.color = COLOR.BLACK;
				root = parent;
				break;
			}
						
		}
		
		if(parent == null) {
			node.color = COLOR.BLACK;
			root = node;
		}
		
		return root;
	}
	
	public RedBlackTreeNode delete(Data data){
		RedBlackTreeNode node = root;
		RedBlackTreeNode parent = null;
		RedBlackTreeNode doubleBlackNode = null;
		
		while(node != null){
			if(data.getData().compareTo(node.data.getData()) < 0){
				parent = node;
				node = node.left;
			} else if (data.getData().compareTo(node.data.getData()) > 0) {
				parent = node;
				node = node.right;
			} else {
				
				RedBlackTreeNode successor = null;
				if(node.left != null && node.right != null){
					//get inorder successor of node
					successor = getInorderSuccessor(node.right);
					
					//copy successor data into node
					node.data = successor.data;
					
					node = successor;
				}
				
				RedBlackTreeNode actualNodeToDelete = node;
				
				parent = node.parent;
				
				// Case 1
				//			B                                          B                                B   					B
				//         /                     OR                     \              OR              /            OR			 \
				//    ->  R						                         R  <-                   ->   R		    				  R  <-
				//       /		            				            / 			                   \		    			   \
				//      X(NULL, BLACK, SUBTREE)  (NULL, BLACK, SUBTREE)X  		  (NULL, BLACK, SUBTREE)X     (NULL, BLACK, SUBTREE)X
				if(node.color == COLOR.RED){
					//'node' can't have a right child as it was actually the inorder successor
					//it can have only a left child if any
					
					RedBlackTreeNode child = node.left != null ? node.left : node.right;
					
					if (parent.left == node) parent.left = child;
					else parent.right = child;
					
					if(child != null) child.parent = parent;
				} else {
					node.color = COLOR.DOUBLE_BLACK;
					while(node != null && node.color == COLOR.DOUBLE_BLACK){
						
						// Case 2
						//
						//         DB(DOUBLE BLACK)
						//        / \
						//    NULL   X
						//
						if(parent == null){
							node.color = COLOR.BLACK;
						} else {
							RedBlackTreeNode sibling = null;
							RedBlackTreeNode grandpa = parent.parent;
							
							if(parent.left == node) sibling = parent.right;
							else sibling = parent.left;
							
							if(sibling.color == COLOR.RED){
								
								if(parent.left == sibling) {
									
									// Case 3
									//			
									//          B
									//         / \
									//        R   DB
									//       / \    
									//      B   B  
									//right rotate on parent
									sibling.parent = grandpa;
									if(grandpa != null) {
										if(grandpa.left == parent) grandpa.left = sibling;
										else grandpa.right = sibling;
									} else {
										sibling.parent = null;
										root = sibling;
									}
									
									parent.left = sibling.right;
									if(sibling.right != null) sibling.right.parent = parent;
									
									parent.parent = sibling;
									sibling.right = parent;
									
									//re-color
									sibling.color = COLOR.BLACK;
									node.color = COLOR.BLACK;
								} else {
									// Case 3 - Mirror case
									//			
									//          B
									//         / \
									//        DB  R
									//           / \    
									//          B   B  
									//left rotate on parent
									sibling.parent = grandpa;
									if(grandpa != null) {
										if(grandpa.left == parent) grandpa.left = sibling;
										else grandpa.right = sibling;
									} else {
										sibling.parent = null;
										root = sibling;
									}
									
									parent.right = sibling.left;
									if(sibling.left != null) sibling.left.parent = parent;
									
									parent.parent = sibling;
									sibling.left = parent;
									
									//re-color
									sibling.color = COLOR.BLACK;
									node.color = COLOR.BLACK;
								}
							} else {
								
								if(parent.left == sibling){
									if( sibling.left == null || sibling.left.color == COLOR.BLACK){
										
										if(sibling.right == null || sibling.right.color == COLOR.BLACK){
											
											//Case 4
											//            B
											//           / \
											//          B   DB
											//         / \    
											// (NULL, B) (B, NULL)
											if(parent.color == COLOR.BLACK){
												//re-color
												node.parent.color = COLOR.DOUBLE_BLACK;
												node.color = COLOR.BLACK;
											} else {
												//Case 5
												//            R
												//           / \
												//          B   DB
												//         / \    
												// (NULL, B) (B, NULL)
												
												//re-color
												parent.color = COLOR.BLACK;
												sibling.color = COLOR.RED;
												node.color = COLOR.BLACK;
											}
											
										} else {
											
											//Case 6
											//            X
											//           / \
											//          B   DB 
											//         / \
											//        B   R
											
											//rotate left on sibling
											RedBlackTreeNode sibling_right = sibling.right;
											
											parent.left = sibling_right;
											if(sibling_right != null) sibling_right.parent = parent;
											
											sibling.right = sibling_right.left;
											if(sibling_right.left != null) sibling_right.left.parent = sibling;
											
											sibling_right.left = sibling;
											sibling.parent = sibling_right;
											
											//re-color
											sibling_right.color = COLOR.BLACK;
											sibling.color = parent.color;
											
											sibling = sibling_right;
											
											//rotate right on parent
											sibling.parent = grandpa;
											if(grandpa != null) {
												if(grandpa.left == parent) grandpa.left = sibling;
												else grandpa.right = sibling;
												sibling.parent = grandpa;
											} else {
												sibling.parent = null;
												root = sibling;
											}
											
											parent.left = sibling.right;
											if(sibling.right != null) sibling.right.parent = parent;
											
											parent.parent = sibling;
											sibling.right = parent;
											
											//re-color
											node.color = COLOR.BLACK;
										}
										
									} else {//sibling's left is red
										
										//Case 7
										//
										//          X(R, B)
										//         / \
										//        B   DB   
										//       / \
										//      R   Y(R, B, NULL)
										//rotate right on parent
										sibling.parent = grandpa;
										if(grandpa != null) {
											if(grandpa.right == parent) grandpa.right = sibling;
											else grandpa.left = sibling;
										} else {
											sibling.parent = null;
											root = sibling;
										}
										
										parent.left = sibling.right;
										if(sibling.right != null) sibling.right.parent = parent;
										
										sibling.right = parent;
										parent.parent = sibling;
										
										//re-color
										sibling.color = parent.color;
										parent.color = COLOR.BLACK;
										sibling.left.color = COLOR.BLACK;
										node.color = COLOR.BLACK;
									}
								} else {//mirror cases
									if( sibling.right == null || sibling.right.color == COLOR.BLACK){
										if(sibling.left == null || sibling.left.color == COLOR.BLACK){
											
											//Case 4 - Mirror case
											//
											//            B
											//           / \
											//          DB  B
											//             / \    
											//     (NULL, B) (B, NULL)
											if(parent.color == COLOR.BLACK){
												
												//re-color
												node.parent.color = COLOR.DOUBLE_BLACK;
												node.color = COLOR.BLACK;
											} else {
												//Case 5 - Mirror case
												//            R
												//           / \
												//          DB  B
												//             / \    
												//     (NULL, B) (B, NULL)
												
												//re-color
												parent.color = COLOR.BLACK;
												sibling.color = COLOR.RED;
												node.color = COLOR.BLACK;
											}
											
										} else {
											
											//Case 6 - Mirror case
											//            X
											//           / \
											//          DB  B 
											//             / \
											//            R   B
											//rotate right on sibling
											RedBlackTreeNode sibling_left = sibling.left;
											
											parent.right = sibling_left;
											sibling_left.parent = parent;
											
											sibling.left = sibling_left.right;
											if(sibling_left.right != null) sibling_left.right.parent = sibling;
											
											sibling_left.right = sibling;
											sibling.parent = sibling_left;
											
											sibling_left.color = COLOR.BLACK;
											sibling.color = parent.color;
											
											sibling = sibling_left;
											
											//rotate left on parent
											sibling.parent = grandpa;
											if(grandpa != null) {
												if(grandpa.left == parent) grandpa.left = sibling;
												else grandpa.right = sibling;
												sibling.parent = grandpa;
											} else {
												sibling.parent = null;
												root = sibling;
											}
											
											parent.right = sibling.left;
											if(sibling.left != null) sibling.left.parent = parent;
											
											parent.parent = sibling;
											sibling.left = parent;
											
											node.color = COLOR.BLACK;
										}
										
									} else {//sibling's right is red
										
										//Case 7 - Mirror case
										//
										//                X(R, B)
										//               / \
										//              DB  B   
										//                 / \
										//    (B, R, NULL)X   R
										//rotate left on parent
										sibling.parent = grandpa;
										if(grandpa != null) {
											if(grandpa.left == parent) grandpa.left = sibling;
											else grandpa.right = sibling;
										} else {
											sibling.parent = null;
											root = sibling;
										}
										
										parent.right = sibling.left;
										if(sibling.left != null) sibling.left.parent = parent;
										
										sibling.left = parent;
										parent.parent = sibling;
										
										//re-color
										sibling.color = parent.color;
										sibling.right.color = COLOR.BLACK;
										parent.color = COLOR.BLACK;
										node.color = COLOR.BLACK;
									}
								}
								
							}
							
						}
					}
				}
				
				if(actualNodeToDelete.parent != null){
					if(actualNodeToDelete.parent.left == actualNodeToDelete) actualNodeToDelete.parent.left = null;
					else actualNodeToDelete.parent.right = null;
				}
				actualNodeToDelete.parent = actualNodeToDelete.left = actualNodeToDelete.right = null;
				
				break;
				
			}
		}
		
		return root;
	}
	
	private RedBlackTreeNode getInorderSuccessor(RedBlackTreeNode currentRoot){
		RedBlackTreeNode node = currentRoot;
		
		while(node != null && node.left != null){
			node = node.left;
		}
		
		return node;
	}
	
	public void preOrderTraversal(RedBlackTreeNode node){
		if(node != null) {
			preorder(node, 0);
		}
	}
	
	private void preorder(RedBlackTreeNode node, int level) {
		if(node != null){
			System.out.println("{"+node + ", Level "+level+", Node "+node.data+" has COLOR "+node.color+"}");
			preorder(node.left, level + 1);
			preorder(node.right, level + 1);
		}
	}
	
	public void postOrderTraversal(RedBlackTreeNode node){
		if(node != null) {
			postorder(node, 0);
		}
	}
	
	private void postorder(RedBlackTreeNode node, int level) {
		if(node != null){
			postorder(node.left, level + 1);
			postorder(node.right, level + 1);
			System.out.println("{"+node + ", Level "+level+"}");
		}
	}
	
	public void inOrderTraversal(RedBlackTreeNode node){
		if(node != null) {
			inorder(node, 0);
		}
	}
	
	private void inorder(RedBlackTreeNode node, int level) {
		if(node != null){
			inorder(node.left, level + 1);
			System.out.println("{"+node + ", Level "+level+"}");
			inorder(node.right, level + 1);
		}
	}

}
