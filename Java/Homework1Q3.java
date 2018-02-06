//Jomar Pueyo
//Looping

import java.util.Scanner;
public class Homework1Q3 {
	
	public static void OddOnlyForLoop(int numb) {
		if(numb<1) {
			System.out.println("Error: Less than 1");
			return;
		}
		System.out.println("ForLoop:");
		for(int i=1;i<=numb;i=i+2) {
			System.out.println(i);
		}
		return;
	}
	
	public static void OddOnlyWhileLoop(int numb) {
		int i=1;
		if(numb<1) {
			System.out.println("Error: Less than 1");
			return;
		}
		System.out.println("WhileLoop:");
		while(i<=numb) {
			System.out.println(i);
			i=i+2;
		}
		
		return;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Select a positive integer to print odd elements less than: ");
		Scanner input = new Scanner(System.in);
		int odd = input.nextInt();
		System.out.println("");
		
		OddOnlyForLoop(odd);
		OddOnlyWhileLoop(odd);
		
		input.close();
	}
}