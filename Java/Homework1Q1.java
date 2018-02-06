//Jomar Pueyo
//Java Functions

import java.util.Scanner;
public class Homework1Q1 {
	public static int Sum(int number1,int number2){
		return number1+number2;
	}
	public static int Product(int number1,int number2) {
		return number1*number2;
	}
	public static int Difference(int number1,int number2) {
		return number1-number2;
	}
	public static int Quotient(int number1,int number2) {
		return number1/number2;
	}

public static void main(String[] args) {

	System.out.print("Input first integer: ");
	Scanner input = new Scanner(System.in);
	int a = input.nextInt();
	System.out.print("Input second integer: ");
	int b = input.nextInt();
	
	int add = Sum(a,b);
	int mult = Product(a,b);
	int sub = Difference(a,b);
	int divide = Quotient(a,b);
	
	System.out.println("Sum: "+add);
	System.out.println("Product: "+mult);
	System.out.println("Difference: "+sub);
	System.out.println("Quotient: "+divide+"\n");
	
	input.close();
	}
}