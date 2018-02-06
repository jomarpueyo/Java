//Jomar Pueyo
//Output Checkerboard

public class Homework1Q4 {

	public static void CheckerBoard() {
		for(int i=0;i<=7;i++) {					//row counter
			if((i%2)==1) {						//if odd row
				for(int j=0;j<=13;j++){ 
					if((j%2)==1) 				//odd column
						System.out.print(" ");
					else						//even column
						System.out.print("#");
				}
			}
			else {								//if even row
				for(int k=0;k<=13;k++){ 
					if((k%2)==1)				//odd column
						System.out.print("#");
					else						//even column
						System.out.print(" ");
				}
			}
			System.out.println();
		}
	return;
	}
	
	public static void main(String[] args) {
		CheckerBoard();
	}
}