package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Pawn Class inherited from the piece
 *
 */
public class Pawn extends Piece{
	
	public Pawn(String id,String path,int color)
	{
		setId(id);
		setPath(path);
		setColor(color);
	}
	
	//Move Function Overridden
	public ArrayList<Cell> move(Cell state[][], Coordinates position)
	{
		
		possiblemoves.clear();
		if(white())
		{
			if(position.getX()==0)
				return possiblemoves;
			handleFirstWhiteMove(state, position);
			addKillMovesWhite(state, position);
		}
		else
		{
			if(position.getX()==8)
				return possiblemoves;
			handleFirstBlackMove(state, position);
			addKillMovesBlack(state, position);
		}
		return possiblemoves;
	}

	private void handleFirstWhiteMove(Cell[][] state, Coordinates position) {
		
		Coordinates checkPosition = position.clone();
		checkPosition.decreaseX();
		
		if(noPieceAtCell(state, checkPosition))
		{
			possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
			if(position.getX()==6)
			{
				if(state[4][position.getY()].getpiece()==null)
					possiblemoves.add(state[4][position.getY()]);
			}
		}
	}

	private void handleFirstBlackMove(Cell[][] state, Coordinates position) {
		Coordinates checkPosition = position.clone();
		checkPosition.increaseX();
		if(noPieceAtCell(state, checkPosition))
		{
			possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
			if(position.getX()==1)
			{
				if(state[3][position.getY()].getpiece()==null)
					possiblemoves.add(state[3][position.getY()]);
			}
		}
	}

	private void addKillMovesBlack(Cell[][] state, Coordinates position) {
		
		Coordinates killPositionFirst = position.clone();
		killPositionFirst.increaseX();
		killPositionFirst.decreaseY();
		
		Coordinates killPositionSeconds = position.clone();
		killPositionSeconds.increaseX();
		killPositionSeconds.increaseY();
		
		if((killPositionFirst.getY()>0)&&(state[killPositionFirst.getX()][killPositionFirst.getY()].getpiece()!=null)&&(state[killPositionFirst.getX()][killPositionFirst.getY()].getpiece().getcolor()!=this.getcolor()))
			possiblemoves.add(state[killPositionFirst.getX()][killPositionFirst.getY()]);
		
		if((killPositionSeconds.getY()<7)&&(state[killPositionSeconds.getX()][killPositionSeconds.getY()].getpiece()!=null)&&(state[killPositionSeconds.getX()][killPositionSeconds.getY()].getpiece().getcolor()!=this.getcolor()))
			possiblemoves.add(state[killPositionSeconds.getX()][killPositionSeconds.getY()]);
	}

	private void addKillMovesWhite(Cell[][] state, Coordinates position) {
		Coordinates killPositionFirst = position.clone();
		killPositionFirst.decreaseX();
		killPositionFirst.decreaseY();
		
		Coordinates killPositionSeconds = position.clone();
		killPositionSeconds.decreaseX();
		killPositionSeconds.increaseY();
		
		if((killPositionFirst.getY()>0)&&(state[killPositionFirst.getX()][killPositionFirst.getY()].getpiece()!=null)&&(state[killPositionFirst.getX()][killPositionFirst.getY()].getpiece().getcolor()!=this.getcolor()))
			possiblemoves.add(state[killPositionFirst.getX()][killPositionFirst.getY()]);
		if((killPositionSeconds.getY()<7)&&(state[killPositionSeconds.getX()][killPositionSeconds.getY()].getpiece()!=null)&&(state[killPositionSeconds.getX()][killPositionSeconds.getY()].getpiece().getcolor()!=this.getcolor()))
			possiblemoves.add(state[killPositionSeconds.getX()][killPositionSeconds.getY()]);
	}

	private boolean noPieceAtCell(Cell[][] state, Coordinates position) {
		return state[position.getX()][position.getY()].getpiece()==null;
	}

	private boolean white() {
		return getcolor()==0;
	}
}
