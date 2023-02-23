public class Board implements Cloneable{
	private int height;
	private int width;
	private boolean[][] board;
	public void setHeight(int height){
		this.height = height;
	}
	public void setArray(boolean[][] array){
		this.board = array;
	}
	public void setWidth(int width){
                this.width = width;
        }
	public int getHeight(){
		return this.height;
	}
	public int getWidth(){
                return this.width;
        }
	public boolean[][] getArray(){
		return this.board;
	}
	public void setBoardSize(int height, int width){
		this.board = new boolean[height][width];
	}
	public void printBoard(){
		for (int i = 0; i < this.getHeight(); i++){
			for(int j = 0; j < this.getWidth(); j++){
				if (this.board[i][j]){
					System.out.print("O");
				} else {
					System.out.print(".");
				}
			}
			System.out.println("");
		}
	}
	public Board getBoard(){
		return this;
	}
	public boolean getIndex(int x, int y){
		return this.board[x][y];
	}
	public void setIndex(int x, int y, boolean value){
		this.board[x][y] = value;
	}
	public void swapIndex(int y, int x){
		if (this.board[y][x]){
			this.board[y][x] = false;
		} else {
			this.board[y][x] = true;
		}
	}
}
