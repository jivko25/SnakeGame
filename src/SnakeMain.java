import java.awt.Container;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SnakeMain {
	public static void main(String [] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Borders px = new Borders('#',2,2);
		String turn;
		while(!px.isGameOver() && px.GameOver == false) {
		turn = sc.nextLine();
		px.GameControlers(turn);
		}
		System.out.printf("Game Over\nYour points are: %d",(px.snakeLenght-1)*100);
		}
	}
	
	class Borders{
		char [][] px = new char[10][10];
		int snakeX;
		int snakeY;
		int snakeLenght = 1;
		ArrayList<Integer> snakeRiseX = new ArrayList<Integer>();
		ArrayList<Integer> snakeRiseY = new ArrayList<Integer>();
		boolean GameOver = false;
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
			RandomTarget();
			Print();
		}
		
		public void RandomTarget() {
			Random rd = new Random();
			int rdX = rd.nextInt(7)+2;
			int rdY = rd.nextInt(7)+2;
			while(this.px[rdX][rdY] == 'o') {
				rdX = rd.nextInt(7)+2;
				rdY = rd.nextInt(7)+2;
			}
			this.px[rdX][rdY] = '@';
		}
		public void TurnRight() throws InterruptedException {
			if(px[this.snakeX+1][this.snakeY] == '@') {
				RandomTarget();
				this.snakeLenght++;
			}
			if(px[this.snakeX+1][this.snakeY] == 'o') {
				this.GameOver = true;
			}
			this.px[this.snakeX][this.snakeY] = ' ';
			this.snakeX = this.snakeX + 1;
			this.px[this.snakeX][this.snakeY] = 'o';
			this.snakeRiseX.add(0,this.snakeX);
			this.snakeRiseY.add(0,this.snakeY);
			RiseSnake();
			Print();
		}
		
		public void TurnLeft() throws InterruptedException {
			if(px[this.snakeX-1][this.snakeY] == '@') {
				RandomTarget();
				this.snakeLenght++;
			}
			if(px[this.snakeX-1][this.snakeY] == 'o') {
				this.GameOver = true;
			}
			this.px[this.snakeX][this.snakeY] = ' ';
			this.snakeX = this.snakeX - 1;
			this.px[this.snakeX][this.snakeY] = 'o';
			this.snakeRiseX.add(0,this.snakeX);
			this.snakeRiseY.add(0,this.snakeY);
			RiseSnake();
			Print();
		}
		
		public void TurnUp() throws InterruptedException {
			if(px[this.snakeX][this.snakeY-1] == '@') {
				RandomTarget();
				this.snakeLenght++;
			}
			if(px[this.snakeX][this.snakeY-1] == 'o') {
				this.GameOver = true;
			}
			this.px[this.snakeX][this.snakeY] = ' ';
			this.snakeY = this.snakeY - 1;
			this.px[this.snakeX][this.snakeY] = 'o';
			this.snakeRiseX.add(0,this.snakeX);
			this.snakeRiseY.add(0,this.snakeY);
			RiseSnake();
			Print();
		}
		
		public void TurnDown() throws InterruptedException {
			if(px[this.snakeX][this.snakeY+1] == '@') {
				RandomTarget();
				this.snakeLenght++;
			}
			if(px[this.snakeX][this.snakeY + 1] == 'o') {
				this.GameOver = true;
			}
			this.px[this.snakeX][this.snakeY] = ' ';
			this.snakeY = this.snakeY + 1;
			this.px[this.snakeX][this.snakeY] = 'o';
			this.snakeRiseX.add(0,this.snakeX);
			this.snakeRiseY.add(0,this.snakeY);
			RiseSnake();
			Print();
		}
		
		public void RiseSnake() {
			for(int i = this.snakeRiseX.size()-1;i>this.snakeLenght;i--) {
				this.px[this.snakeRiseX.get(i)][this.snakeRiseY.get(i)] = ' ';
				snakeRiseX.remove(i);
				snakeRiseY.remove(i);
			}
			for(int i = 0;i<this.snakeLenght;i++) {
				this.px[this.snakeRiseX.get(i)][this.snakeRiseY.get(i)] = 'o';
			}
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
			if(px[this.snakeX][this.snakeY] == '@') {
				RandomTarget();
			}
		}
		
	}
