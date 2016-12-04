package pieces;

import java.util.ArrayList;
import java.util.Iterator;

import chess.Cell;


/**
 * This is the Piece Class. It is an abstract class from which all the actual pieces are inherited.
 * It defines all the function common to all the pieces
 * The move() function an abstract function that has to be overridden in all the inherited class
 * It implements Cloneable interface as a copy of the piece is required very often
 */
public abstract class Piece implements Cloneable{

	//Member Variables
	
	protected ArrayList<Cell> possiblemoves = new ArrayList<Cell>();  //Protected (access from child classes)
	public abstract ArrayList<Cell> move(Cell pos[][],Coordinates position);  //Abstract Function. Must be overridden
	
	public static final int KING_TYPE = 1;
	public static final int SIMPLE_TYPE = 1;
	
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
	
	public boolean canMoveHere(Cell state[][], Coordinates here){
		
		ArrayList<Cell> moves = this.move(state, here);
		Iterator<Cell> cellIte = moves.iterator();
		
		while(cellIte.hasNext()){
			Cell movableCell = cellIte.next();
			if(movableCell.getCoordinates().equals(here))
			{
				return true;
			}
		}
		return false;
	}
}