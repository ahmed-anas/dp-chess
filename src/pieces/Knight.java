package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Knight Class inherited from the Piece abstract class
 *  
 *
 */
class Knight extends Ability
{
	Knight(Piece piece){
		super(piece);
	}
	
	private boolean isValidMove(Cell[][] state, Coordinates position){
		return position.isValid() && (state[position.getX()][position.getY()].getpiece()==null||state[position.getX()][position.getY()].getpiece().getcolor()!=this.getcolor());
	}
	
	
	//Move Function overridden
	//There are at max 8 possible moves for a knight at any point of time.
	//Knight moves only 2(1/2) steps
	public ArrayList<Cell> move(Cell state[][], Coordinates position)
	{
		int x = position.getX();
		int y = position.getY();
		possiblemoves.clear();
		int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
		int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
		
		
		
		for(int i=0;i<8;i++)
		{
			if(isValidMove(state, new Coordinates(posx[i], posy[i])))
			{
				possiblemoves.add(state[posx[i]][posy[i]]);
			}
		}
		
		possiblemoves.addAll(this.piece.move(state, position));
		return possiblemoves;
	}
}
