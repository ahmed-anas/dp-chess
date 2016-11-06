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
	int x, y;                             //is public because this is to be accessed by all the other class
	private boolean isSelected=false;
	private boolean ischeck=false;
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Cell(int x, int y, Piece p) {		
		setCoords(x, y);
		setLayout(new BorderLayout());
		setBackgroundColor();
		if(p!=null)
			setPiece(p);
	}

	private void setBackgroundColor() {
		if((x+y)%2==0)
			setBackground(new Color(113,198,113));
		else
			setBackground(Color.white);
	}
	
	public Cell(Cell cell) throws CloneNotSupportedException
	{
		setCoords(cell.x, cell.y);
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
	
	public void removePiece()
	{
		piece=null;
		this.remove(content);
	}
	
	
	public Piece getpiece()
	{
		return this.piece;
	}
	
	public void select()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		this.isSelected=true;
	}
	
	public boolean isselected()   //Function to return if the cell is under selection
	{
		return this.isSelected;
	}
	
	public void deselect()
	{
		this.setBorder(null);
		this.isSelected=false;
	}
	
	public void setpossibledestination()     //Function to highlight a cell to indicate that it is a possible valid move
	{
		this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		this.ispossibledestination=true;
	}
	
	public void removepossibledestination()      //Remove the cell from the list of possible moves
	{
		this.setBorder(null);
		this.ispossibledestination=false;
	}
	
	public boolean ispossibledestination()    //Function to check if the cell is a possible destination 
	{
		return this.ispossibledestination;
	}
	
	public void setcheck()     //Function to highlight the current cell as checked (For King)
	{
		this.setBackground(Color.RED);
		this.ischeck=true;
	}
	
	public void removecheck()
	{
		this.setBorder(null);
		setBackgroundColor();
		this.ischeck=false;
	}
	
	public boolean ischeck()    //Function to check if the current cell is in check
	{
		return ischeck;
	}
}