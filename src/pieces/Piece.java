package pieces;

import java.util.ArrayList;
import chess.Cell;

public abstract class Piece implements Cloneable{

	//Member Variables
	
	protected ArrayList<Cell> possiblemoves = new ArrayList<Cell>();  //Protected (access from child classes)
	public abstract ArrayList<Cell> move(Cell pos[][],Coordinates position);  //Abstract Function. Must be overridden
	
	public static final int KING_TYPE = 1;
	static final int SIMPLE_TYPE = 2;
	
	public abstract int getPieceType();
	
	public abstract void setId(String id);
	public abstract void setPath(String path);
	public abstract void setColor(int c);
	public abstract String getPath();
	public abstract String getId();
	public abstract int getcolor();
	
	public void setx(int x)
	{
		throw new UnsupportedOperationException();
	}
	public void sety(int y)
	{
		throw new UnsupportedOperationException();
	}
	public int getx()
	{
		throw new UnsupportedOperationException();
	}
	public int gety()
	{
		throw new UnsupportedOperationException();
	}
	
	public boolean isindanger(Cell state[][]){
		throw new UnsupportedOperationException();
	}
	
	public abstract PieceType getRootPiece();
	
	//Function to return the a "shallow" copy of the object. The copy has exact same variable value but different reference
	public Piece getcopy() throws CloneNotSupportedException
	{
		return (Piece) this.clone();
	}
	
	public abstract boolean  hasInstance(Class c);
}