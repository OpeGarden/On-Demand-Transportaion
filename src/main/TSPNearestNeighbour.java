package main;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class TSPNearestNeighbour{
	private int numberOfNodes;
	private Stack<Integer> stack;

	public TSPNearestNeighbour()
	{
		stack = new Stack<Integer>();
	}

	public <T> ArrayList<T> tsp(ArrayList<T> list, double[][] adjacencyMatrix , int begin){
		ArrayList<T> ans = new ArrayList<T>();
		numberOfNodes = adjacencyMatrix[1].length - 1;
		int[] visited = new int[numberOfNodes + 1];
		
		visited[begin] = 1;
		stack.push(begin);
		int element, dst = begin, i;
		double min = Double.MAX_VALUE;
		boolean minFlag = false;
		ans.add(list.get(begin));

		while (!stack.isEmpty()){
			element = stack.peek();
			i = 0;
			min = Double.MAX_VALUE;
			while (i <= numberOfNodes){
				if (visited[i] == 0){
					if (min > adjacencyMatrix[element][i]){
						min = adjacencyMatrix[element][i];
						dst = i;
						minFlag = true;
					}
				}
				i++;
			}
			if (minFlag){
				visited[dst] = 1;
				stack.push(dst);
				ans.add(list.get(dst));
				minFlag = false;
				continue;
			}
			stack.pop();
		}
		return ans;
	}
}