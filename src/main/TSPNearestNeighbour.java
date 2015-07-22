package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class TSPNearestNeighbour{
	private int numberOfNodes;
	private Stack<Integer> stack;
	
	List<int []> permuteNoRepeat = new ArrayList<int[]>();

	public TSPNearestNeighbour(int x)
	{
		stack = new Stack<Integer>();
		createAllSubsetsWithSameLength(x);

/*		for(int[] a : permuteNoRepeat){
			System.out.println(Arrays.toString(a));
		}*/
		
	}

	public <T> ArrayList<T> tsp(ArrayList<T> list, double[][] adjacencyMatrix , int begin){
		ArrayList<T> ans = new ArrayList<T>();
		numberOfNodes = adjacencyMatrix[0].length - 1;
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

	public ArrayList<TransportRequest> tspFull(ArrayList<TransportRequest> list, double[][] distances) {
		ArrayList<TransportRequest> ans = new ArrayList<TransportRequest>();
		
		int [] best = null;
		int min = Integer.MAX_VALUE;
		for(int[] a: permuteNoRepeat){
			int sum = 0;
			for (int i = 0; i < a.length-1; i++) {
				sum += distances[a[i]][a[i+1]];
			}

			if(sum < min){
				best = a;
				min = sum;
			}
		}
		
		System.out.println(Arrays.toString(best));
		System.out.println(min);

		for(int x : best){
			ans.add(list.get(x));
		}
		return ans;
	}
	
	
	public void createAllSubsetsWithSameLength(int x){
		int[] list = new int[x];
		for (int i = 0; i < x; i++) {
			list[i] = i;
		}
	    do {
	    	int[] permute = new int[x];
	        for (int i = 0; i < list.length; i++) {
				permute[i] = list[i];
				permuteNoRepeat.add(permute);
			}
	    } while(permuteLexically(list));
		
	}
	

	
	public static boolean permuteLexically(int[] data) {
	    int k = data.length - 2;
	    while (data[k] >= data[k + 1]) {
	        k--;
	        if (k < 0) {
	            return false;
	        }
	    }
	    int l = data.length - 1;
	    while (data[k] >= data[l]) {
	        l--;
	    }
	    swap(data, k, l);
	    int length = data.length - (k + 1);
	    for (int i = 0; i < length / 2; i++) {
	        swap(data, k + 1 + i, data.length - i - 1);
	    }
	    return true;
	}
	
	
	
    private static void swap(int[] data, int k, int l) {
		// TODO Auto-generated method stub
		int t = data[k];
		data[k] = data[l];
		data[l] = t;
	}

	// The method that prints all possible strings of length k.  It is
    //  mainly a wrapper over recursive function printAllKLengthRec()
    static void printAllKLength(int set[], int k) {
        int n = set.length;        
        printAllKLengthRec(set, "", n, k);
    }
 
    // The main recursive method to print all possible strings of length k
    static void printAllKLengthRec(int set[], String prefix, int n, int k) {
         
        // Base case: k is 0, print prefix
        if (k == 0) {
            System.out.println(prefix);
            return;
        }
 
        // One by one add all characters from set and recursively 
        // call for k equals to k-1
        for (int i = 0; i < n; ++i) {
             
            // Next character of input added
            String newPrefix = prefix + set[i]; 
             
            // k is decreased, because we have added a new character
            printAllKLengthRec(set, newPrefix, n, k - 1); 
        }
    }

}