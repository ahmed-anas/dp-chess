package pieces;

import java.util.ArrayList;

import chess.Cell;

abstract class PieceType extends Piece{
	private int color;
	private String id=null;
	private String path;
	
	
	

	@Override
	public ArrayList<Cell> move(Cell state[][], Coordinates position)
	{
		return new ArrayList<Cell>();
	}
	
	//Id Setter
	public void setId(String id)
	{
		this.id=id;
	}
	
	//Path Setter
	public void setPath(String path)
	{
		this.path=path;
	}
	
	//Color Setter
	public void setColor(int c)
	{
		this.color=c;
	}
	
	//Path getter
	public String getPath()
	{
		return path;
	}
	
	//Id getter
	public String getId()
	{
		return id;
	}
	
	//Color Getter
	public int getcolor()
	{
		return this.color;
	}
	

	@Override
	public PieceType getRootPiece() {
		return this;
	}
	
	public  boolean  hasInstance(Class c)
	{
		return c.isAssignableFrom(this.getClass());
	}

	
}
