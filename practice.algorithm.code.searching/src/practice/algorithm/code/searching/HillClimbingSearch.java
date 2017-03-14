package practice.algorithm.code.searching;

import java.util.Arrays;

/**
 * Hill climbing search
 * 
 * This is a mathematical optimization technique. 
 * It is an iterative algorithm that starts with an arbitrary solution to a problem, 
 * then attempts to find a better solution by incrementally changing a single element 
 * of the solution. If the change produces a better solution, an incremental change 
 * is made to the new solution, repeating until no further improvements can be found.
 * 
 * Hill climbing is good for finding a local optimum (a solution that cannot be improved
 * by considering a neighbouring configuration) but it is not necessarily guaranteed to 
 * find the best possible solution (the global optimum) out of all possible solutions 
 * (the search space). In convex problems, hill-climbing is optimal.
 *
 */

/**
 * This is a demo of concept only. As this concept can't be bound with a certain algorithm, 
 * i.e. it doesn't have well defined steps but it is applied as technique which is changed
 * as per the requirement of problem.
 *  
 * In the problem below we are trying to find a point which is nearest to 4 points. 
 * 
 * @author Rajesh
 *
 */
public class HillClimbingSearch {
	
	
	public static void main(String...strings){
		final double step = 0.1d;
		
		Point[] points = new Point[]{new Point(1, 5), new Point(6, 4), new Point(5, 2), new Point(2, 1)};
		Point point = new Point(1, 1);
		
		Point[] tempPoints = new Point[4];
		
		while(true){
			tempPoints[0] = point.getNewPoint(-step, 0);
			tempPoints[1] = point.getNewPoint(0, -step);
			tempPoints[2] = point.getNewPoint(+step, 0);
			tempPoints[3] = point.getNewPoint(0, +step);
			
			//Get the point among the points derived above i.e. 'tempPoints' which has least distance
			//from the 'points'
			Point pt = Arrays.stream(tempPoints).min((p1, p2) -> {
				int val = 0;
				
				if (sumOfDistances(p1, points) < sumOfDistances(p2, points)) val = -1;
				else if (sumOfDistances(p1, points) > sumOfDistances(p2, points)) val = 1;
				
				return val;
			}).get();
			
			//if distance of 'pt' is smaller than 'point'
			if(sumOfDistances(pt, points) < sumOfDistances(point, points)){
				point = pt;
			} else {
				break;
			}
			
		}
		
		System.out.println("Point "+point);
	}
	
	public static double sumOfDistances(Point point, Point[] points){
		return Arrays.stream(points).mapToDouble(pt -> {
			return point.getDistance(pt);
		}).sum();
	}	
	
	private static class Point {
		private double x;
		private double y;
		
		public Point(double x, double y){
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}
		
		public Point getNewPoint(double deltaX, double deltaY){
			return new Point(this.x + deltaX, this.y + deltaY);
		}
		
		public double getDistance(Point point) {
			return Math.sqrt((Math.pow((point.getX() - this.getX()), 2)) + (Math.pow((point.getY() - this.getY()), 2)));
		}
		
		public String toString(){
			return "[X="+this.getX()+", Y="+this.getY()+"]";
		}
		
	}
	
}
