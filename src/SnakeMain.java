import java.util.concurrent.TimeUnit;

public class SnakeMain {
	public static void main(String [] args) throws InterruptedException {
		Borders px = new Borders('#',2,2);
		px.Print();
		for(int i = 10;i>=px.snakeX;i--) {
		TimeUnit.SECONDS.sleep(1);
		px.newPos(1,1);
		px.Print();
		}
	}
	
	public static class Borders{
		char [][] px = new char[10][10];
		int snakeX;
		int snakeY;
		public Borders(char symbol, int snakeX,int snakeY) {
			this.snakeX = snakeX;
			this.snakeY = snakeY;
			for(int i = 0;i<px.length;i++) {
				for(int k = 0;k<px.length;k++) {
					if(i == 0 || i == px.length-1 || k == 0 || k == px.length-1) {
						this.px[i][k] = symbol;
					}
					else if(i == this.snakeY && k == this.snakeY){
						this.px[i][k] = 'o';
					}
					else {
						this.px[i][k] = ' ';
					}
				}
			}
		}
		
		public void newPos(int x,int y) throws InterruptedException {
			this.snakeX = this.snakeX + x;
			this.snakeY = this.snakeY + y;
			for(int i = 0;i<px.length;i++) {
				for(int k = 0;k<px.length;k++) {
					if(i == 0 || i == px.length-1 || k == 0 || k == px.length-1) {
						this.px[i][k] = '#';
					}
					else if(i == this.snakeY && k == this.snakeY){
						this.px[i][k] = 'o';
					}
					else {
						this.px[i][k] = ' ';
					}
				}
			}
		}
		
		public void Print() {
			for(int i = 0;i<px.length;i++) {
				for(int k = 0;k<px.length;k++) {
					System.out.print(this.px[i][k]);
				}
				System.out.println();
			}
		}
		public void Delete() {
			for(int i = 0;i<px.length;i++) {
				for(int k = 0;k<px.length;k++) {
					
						this.px[i][k] = '\b';
					
				}
			}
			Print();
		}
	}
}
