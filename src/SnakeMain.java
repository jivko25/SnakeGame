import java.awt.Container;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SnakeMain {
	public static void main(String [] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Borders px = new Borders('#',2,2);
		String turn = sc.nextLine();
		while(!px.isGameOver()) {
		turn = sc.nextLine();
		px.GameControlers(turn);
		}
		}
	}
	
	class Borders{
		char [][] px = new char[10][10];
		int snakeX;
		int snakeY;
		public Borders(char symbol, int snakeX,int snakeY) {
			this.snakeX = snakeX;
			this.snakeY = snakeY;
			for(int i = 0;i<px.length;i++) {
				for(int k = 0;k<px.length;k++) {
					if(i == 0 || i == px.length-1 || k == 0 || k == px.length-1) {
						this.px[k][i] = symbol;
					}
					else if(i == this.snakeY && k == this.snakeY){
						this.px[k][i] = 'o';
					}
					else {
						this.px[k][i] = ' ';
					}
				}
			}
			Print();
		}
		
		public void TurnRight() throws InterruptedException {
			this.px[this.snakeX][this.snakeY] = ' ';
			this.snakeX = this.snakeX + 1;
			this.px[this.snakeX][this.snakeY] = 'o';
			Print();
		}
		
		public void TurnLeft() throws InterruptedException {
			this.px[this.snakeX][this.snakeY] = ' ';
			this.snakeX = this.snakeX - 1;
			this.px[this.snakeX][this.snakeY] = 'o';
			Print();
		}
		
		public void TurnUp() throws InterruptedException {
			this.px[this.snakeX][this.snakeY] = ' ';
			this.snakeY = this.snakeY - 1;
			this.px[this.snakeX][this.snakeY] = 'o';
			Print();
		}
		
		public void TurnDown() throws InterruptedException {
			this.px[this.snakeX][this.snakeY] = ' ';
			this.snakeY = this.snakeY + 1;
			this.px[this.snakeX][this.snakeY] = 'o';
			Print();
		}
		
		public void Print() {
			for(int i = 0;i<px.length;i++) {
				for(int k = 0;k<px.length;k++) {
					System.out.print(this.px[k][i]);
				}
				System.out.println();
			}
		}
		public boolean isGameOver() {
			if(this.snakeX == 0 || this.snakeX == this.px.length-1 || this.snakeY == 0 || this.snakeY == this.px.length-1 ) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public void GameControlers(String turn) throws InterruptedException {
			boolean D = false;
			boolean A = false;
			boolean W = false;
			boolean S = false;
//			TimeUnit.SECONDS.sleep(1);
			if(W == true) {
				TurnUp();
			}
			else if(D == true) {
				TurnRight();
			}
			else if(A == true) {
				TurnLeft();
			}
			else if(S == true) {
				TurnDown();
			}
			switch (turn) {
			case "w":
				TurnUp();
				W = true;
				A = false;
				S = false;
				D = false;
				break;
			case "s":
				TurnDown();
				W = false;
				A = false;
				S = true;
				D = false;
				break;
			case "a":
				TurnLeft();
				W = false;
				A = true;
				S = false;
				D = false;
				break;
			case "d":
				TurnRight();
				W = false;
				A = false;
				S = false;
				D = true;
				break;	
			default:
				break;
			}
		}
		
	}
