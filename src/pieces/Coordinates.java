package pieces;

public class Coordinates implements Cloneable{

	private int x;
	private int y;
	public Coordinates(){
		
	}
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean isEqualTo(Coordinates c){
		return this.x == c.x && this.y == c.y;
	}
	
	@Override
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
	
	void increaseX(){
		this.x++;
	}
	void decreaseX(){
		this.x--;
	}
	void increaseY(){
		this.y++;
	}
	void decreaseY(){
		this.y--;
	}
	
}
