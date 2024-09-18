/*
	This program takes an integer input and converts that integer to binary
	This program may also take a binary input and convert back to an integer
*/

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

public class BinaryConverter {
	public static int convertToBinary(int input) {
		Stack<Integer> stack = new Stack<>();
		String hold = "";
		int output;
		
		/*
			Any number % 2 is either 0 or 1
			For every division, push the remainder to the stack
			Stacks are last-in-first-out, so pop() until empty will result in the binary number
		*/
		
		while (input > 0) {
			stack.push(input % 2);
			input = input / 2;
		}
		
		// Convert stack to Integer array
		Integer[] arr = new Integer[stack.size()];
		arr = stack.toArray(arr);
		
		// Reverse array, eg. [01001] -> [10010]
		for(int n = 0; n < arr.length / 2; n++) {
			int temp = arr[n];
			arr[n] = arr[arr.length - n - 1];
			arr[arr.length - n - 1] = temp;
		}
		
		// Join all elements as string
		for(int i = 0; i < arr.length; i++) {
			hold = hold + arr[i];
		}
		
		// Parse string to int
		output = Integer.parseInt(hold);
		
		return output;
	}
	
	public static int convertToInteger(int input) {
		int output = 0;
		
		// Convert integer input to ArrayList
		ArrayList<Integer> list = new ArrayList<>();
		while(input > 0) {
			list.add(input % 10);
			input /= 10;
		}
		
		// Convert list to array
		Integer[] arr = new Integer[list.size()];
		arr = list.toArray(arr);
		
		/*
			Conversions from binary to decimal read right to left going by 2 to the power of the element's index
			Then, add all elements together for the final product
		*/
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > 0) {
				arr[i] = (int) Math.pow(2, i);
			}
		}
		
		for(int n = 0; n < arr.length; n++) {
			output = output + arr[n];
		}
		
		return output;
	}
	
	public static void main(String[] args) {
		String option;
		int input;
		
		Scanner binOrInt = new Scanner(System.in);
		System.out.println("Binary or integer input?");
		option = binOrInt.nextLine().toLowerCase();
		
		if(option.startsWith("int")) {
			// Convert integer to binary
			
			Scanner in = new Scanner(System.in);
			System.out.println("Enter integer input: ");
			
			if(in.hasNextInt()) {
				input = in.nextInt();
				
				System.out.println(input + " in binary is " + convertToBinary(input));
				
				// Close scanners
				binOrInt.close();
				in.close();
			}
			
		} else if(option.startsWith("bin")) {
			// Convert binary to integer
			
			Scanner in = new Scanner(System.in);
			System.out.println("Enter binary input:");
			
			if(in.hasNextInt()) {
				input = in.nextInt();
				
				System.out.println(input + " in decimal is " + convertToInteger(input));
				
				// Close scanners
				binOrInt.close();
				in.close();
			}
			
		} else {
			System.out.println("Invalid selection");
		}
	}
}