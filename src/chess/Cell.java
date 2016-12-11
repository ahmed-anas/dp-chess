package chess;

import java.awt.*;
import javax.swing.*;

import pieces.*;

/**
 * This is the Cell Class. It is the token class of our GUI.
 * There are total of 64 cells that together makes up the Chess Board
 *
 */
public class Cell extends JPanel implements Cloneable, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private boolean ispossibledestination;
	private JLabel content;
	private Piece piece=null;
	private int x,y;
	private boolean isSelected=false;
	private boolean ischeck=false;
	
	public Coordinates getCoordinates(){
		return new Coordinates(this.getCellX(), this.getCellY());
	}
	public int getCellX()
	{
		return x;
	}

	
	public int getCellY()
	{
		return y;
	}

	private void setCoords(int x, int y) {
		this.x=x;
		this.y=y;
	}

	Cell(int x, int y, Piece p) {		
		setCoords(x, y);
		setLayout(new BorderLayout());
		setBackgroundColor();
		if(p!=null)
			setPiece(p);
	}

	private void setBackgroundColor() {
		if((getCellX()+getCellY())%2==0)
			setBackground(new Color(113,198,113));
		else
			setBackground(Color.white);
	}
	
	Cell(Cell cell) throws CloneNotSupportedException
	{
		setCoords(cell.getCellX(), cell.getCellY());
		setLayout(new BorderLayout());
		setBackgroundColor();
		if(cell.getpiece()!=null)
			setPiece(cell.getpiece().getcopy());
	}
	
	public void setPiece(Piece p)
	{
		piece=p;
		ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));
		content=new JLabel(img);
		this.add(content);
	}
	
	void removePiece()
	{
		piece=null;
		this.remove(content);
	}
	
	
	public Piece getpiece()
	{
		return this.piece;
	}
	
	void select()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		isSelected=true;
	}
	
	
	void deselect()
	{
		this.setBorder(null);
		isSelected=false;
	}
	
	void setpossibledestination()     //Function to highlight a cell to indicate that it is a possible valid move
	{
		this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		this.ispossibledestination=true;
	}
	
	void removepossibledestination()      //Remove the cell from the list of possible moves
	{
		this.setBorder(null);
		this.ispossibledestination=false;
	}
	
	boolean ispossibledestination()    //Function to check if the cell is a possible destination 
	{
		return this.ispossibledestination;
	}
	
	void setcheck()     //Function to highlight the current cell as checked (For King)
	{
		this.setBackground(Color.RED);
		this.ischeck=true;
	}
	
	void removecheck()
	{
		this.setBorder(null);
		setBackgroundColor();
		this.ischeck=false;
	}
	
	boolean ischeck()    //Function to check if the current cell is in check
	{
		return ischeck;
	}

	
}