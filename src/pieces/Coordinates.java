package pieces;

public class Coordinates {

	private int x;
	private int y;
	public Coordinates(){
		
	}
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinates clone(){
		return new Coordinates(this.getX(), this.getY());
	}
	
	public boolean isValid(){
		return this.getX() >=0 && this.getX() < 8 && this.getY() >= 0 && this.getY() < 8;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void increaseX(){
		this.x++;
	}
	public void decreaseX(){
		this.x--;
	}
	public void increaseY(){
		this.y++;
	}
	public void decreaseY(){
		this.y--;
	}
	
}
