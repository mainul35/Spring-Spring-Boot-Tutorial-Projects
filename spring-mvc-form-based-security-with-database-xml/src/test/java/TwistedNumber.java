import java.util.Scanner;

public class TwistedNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int start = 0;
		int end = 0;
		for (int x = 0; x <= (num / 5); x++) {
			if (x % 2 == 0) {
				
				for (; start < num;) {
					++start;
					System.out.print(start + " ");
					if(start%5==0){
						break;
					}
				}
				end = (start+=5)<num?(start):(num);
				
			}else if (x % 2 == 1) {
				for (; start-5<end; end--) {
					System.out.print(end + " ");
				}
			}
			System.out.println();
		}
	}
}
