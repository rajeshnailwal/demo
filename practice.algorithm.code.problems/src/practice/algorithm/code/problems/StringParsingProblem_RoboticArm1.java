package practice.algorithm.code.problems;

/**
 * 
 * @author rajeshnailwal
 * 
 * We have a robot that can pick up blocks from a stash, move them horizontally, and lower them in place. 
 * There are 10 positions available to lower blocks, numbered from 0 to 9. Each position can hold up to 15 blocks.
 *
 * The robot understands the commands 'P', 'M' and 'L':
 * 
 * P: Pickup from the stash and move to position 0
 * M: Move to the next position
 * L: Lower the block
 *	
 * The robot is safe to operate and very forgiving:
 *
 * - There are always blocks in the stash (Pickup will always get a block).
 * - If the robot already holds a block, Pickup will reset the robot to position 0.
 * - The robot will not go beyond position 9. Trying to Move it further does nothing.
 * - Lowering a block on a pile of 15 blocks does nothing (and the robot will keep any block it holds).
 * - Lowering without a block does nothing.
 * - The robot ignores any command that is not 'P', 'M' or 'L'.
 *
 * Implement a function that takes a String of commands for the robot. The function should output a String representing the number of blocks (in hexadecimal) at each position after running all the commands.
 *
 * Sample Input 1 : "PMLPMMMLPMLPMML"
 * Sample Output 1 : "0211000000"
 * Sample Input 2 : "PLPLPLPLPLPLPLPLPLPL"
 * Sample Output 2 : "A000000000"
 *
 */
public class StringParsingProblem_RoboticArm1 {
	
	public static void main(String...strings){
		String commandSet = "PLPLPLPLPLPLPLPLPLPL";
		int[]  positions = new int[10];
		int currentPosition = 0;
		boolean isBlockPicked = false;
		
		int count = 0;
		while(count < commandSet.length()){
			char command = commandSet.charAt(count);
			
			switch(command) {
				case 'P' :
					currentPosition = 0;
					isBlockPicked = true;
					break;
				case 'M' :
					if(currentPosition < 9) ++currentPosition;
					break;
				case 'L' :
					if(isBlockPicked && positions[currentPosition] < 15) {
						positions[currentPosition] += 1;
						isBlockPicked = false;
					}
					break;
				default :
					//do nothing
			}
			count++;
		}
		
		StringBuilder finalString = new StringBuilder("");
		for(int i : positions){
			finalString.append(Integer.toHexString(i).toUpperCase());
		}
		System.out.println(finalString);
	}
	
}