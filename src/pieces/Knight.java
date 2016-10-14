package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Knight Class inherited from the Piece abstract class
 *  
 *
 */
public class Knight extends Piece{
	
	//Constructor
	public Knight(String id,String path,int color)
	{
		setId(id);
		setPath(path);
		setColor(color);
		
	}
	
	private boolean isValidPosition(int x, int y)
	{
		return x>=0&&x<8&&y>=0&&y<8;
	}
	
	public boolean isValidMove(Cell[][] state, int x, int y){
		return isValidPosition(x,y) && (state[x][y].getpiece()==null||state[x][y].getpiece().getcolor()!=this.getcolor());
	}
	
	
	//Move Function overridden
	//There are at max 8 possible moves for a knight at any point of time.
	//Knight moves only 2(1/2) steps
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		possiblemoves.clear();
		int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
		int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
		for(int i=0;i<8;i++)
			if(isValidMove(state, posx[i], posy[i]))
			{
				possiblemoves.add(state[posx[i]][posy[i]]);
			}
		return possiblemoves;
	}
}