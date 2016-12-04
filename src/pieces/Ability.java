package pieces;

import chess.Cell;

public abstract class Ability extends Piece{
	protected Piece piece;
	public Ability(Piece piece){
		this.piece = piece;
	}
	protected boolean noPieceAtCell(Cell[][] state, Coordinates position) {
		return state[position.getX()][position.getY()].getpiece()==null;
	}
	
	protected boolean ourPieceAtCell(Cell[][] state, Coordinates position) {
		return state[position.getX()][position.getY()].getpiece().getcolor()==this.getcolor();
	}
	
	@Override
	public PieceType getRootPiece(){
		return this.piece.getRootPiece();
	}
	public int getPieceType(){
		return this.piece.getPieceType();
	}
	//Id Setter
	public void setId(String id)
	{
		this.piece.setId(id);
	}
	
	//Path Setter
	public void setPath(String path)
	{
		this.piece.setPath(path);
	}
	
	//Color Setter
	public void setColor(int c)
	{
		this.piece.setColor(c);
	}
	
	//Path getter
	public String getPath()
	{
		return this.piece.getPath();
	}
	
	//Id getter
	public String getId()
	{
		return this.piece.getId();
	}
	
	//Color Getter
	public int getcolor()
	{
		return this.piece.getcolor();
	}
	
	
	public void setx(int x)
	{
		this.piece.setx(x);
	}
	public void sety(int y)
	{
		this.piece.sety(y);
	}
	public int getx()
	{
		return this.piece.getx();
	}
	public int gety()
	{
		return this.piece.gety();
	}

}
