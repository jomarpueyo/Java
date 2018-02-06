//Jomar Pueyo
//Hotel Check-in price
import java.util.Scanner;

public class Homework1Q2 {

	public static float Total(int night, int kingRoom, int doubleRoom) {
		float discount = 1.00f;
		int room = kingRoom + doubleRoom;
		
		if (night>3)
			discount = 0.90f;
		if (room>3)
			discount = 0.90f;
		if ((room>3)&&(night>3))
			discount =0.85f;

		return (float) (discount*night*(kingRoom*100.00+doubleRoom*80.00));
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("How many nights to stay in Hotel ABC?");
		int nights = input.nextInt();
		System.out.println("How many King Sized Rooms?");
		int kingRoom = input.nextInt();
		System.out.println("How many Double Full-Sized Rooms?");
		int doubleRoom = input.nextInt();
		
		System.out.println("Your total is: "+Total(nights,kingRoom,doubleRoom));
		
		input.close();
	}
}