package main;
import java.util.ArrayList;


public class Utils {
	public static void printMatrix(double[][] matrix){
		System.out.println("\n\n");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println("");
		}
	}

	public static <T> void swap(T[] arr, int i, int j) {
		T temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}

	public static <T> String printArrayList(ArrayList<T> arraylist) {
		String s = "";
		for(T t : arraylist){
			s += t.toString();
		}
		return s;
	}
}
