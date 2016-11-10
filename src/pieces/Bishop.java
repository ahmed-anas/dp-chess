package pieces;

import java.util.ArrayList;

import chess.Cell;


/**
 * This is the Bishop Class.
 * The Move Function defines the basic rules for movement of Bishop on a chess board
 * 
 *
 */
public class Bishop extends Piece{
	
	public Bishop(String id,String path,int color)
	{
		setId(id);
		setPath(path);
		setColor(color);
	}
	
	public ArrayList<Cell> move(Cell state[][],Coordinates newPosition)
	{
		//Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
		possiblemoves.clear();
		checkSE(state, newPosition);
		checkNW(state, newPosition);
		checkSW(state, newPosition);
		checkNE(state, newPosition);
		return possiblemoves;
	}

	private void checkNE(Cell[][] state, Coordinates position) {
		
		Coordinates checkPosition = position.clone();
		checkPosition.increaseX();
		checkPosition.increaseY();
		while(checkPosition.getX()<8&&checkPosition.getY()<8)
		{
			if(noPieceAtCell(state, checkPosition))
				possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
			else if(ourPieceAtCell(state, checkPosition))
				break;
			else
			{
				possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
				break;
			}
			checkPosition.increaseX();
			checkPosition.increaseY();
		}
	}

	private void checkSW(Cell[][] state, Coordinates position) {
		
		Coordinates checkPosition = position.clone();
		checkPosition.decreaseX();
		checkPosition.decreaseY();
		while(checkPosition.getX()>=0&&checkPosition.getY()>=0)
		{
			if(noPieceAtCell(state, checkPosition))
				possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
			else if(ourPieceAtCell(state, checkPosition))
				break;
			else
			{
				possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
				break;
			}
			checkPosition.decreaseX();
			checkPosition.decreaseY();
		}
	}

	private void checkNW(Cell[][] state, Coordinates position) {
		Coordinates checkPosition = position.clone();
		
		checkPosition.decreaseX();
		checkPosition.increaseY();
		while(checkPosition.getX()>=0&&checkPosition.getY()<8)
		{
			if(noPieceAtCell(state, checkPosition))
				possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
			else if(ourPieceAtCell(state, checkPosition))
				break;
			else
			{
				possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
				break;
			}
			checkPosition.decreaseX();
			checkPosition.increaseY();
		}
	}

	private void checkSE(Cell[][] state, Coordinates position) {
		
		Coordinates nextPosition = new Coordinates(position.getX() + 1, position.getY() - 1);
		while(nextPosition.getX()<8 && nextPosition.getY()>=0)
		{
			if(noPieceAtCell(state, nextPosition))
			{
				possiblemoves.add(state[nextPosition.getX()][nextPosition.getY()]);
			}
			else if(ourPieceAtCell(state, nextPosition))
				break;
			else
			{
				possiblemoves.add(state[nextPosition.getX()][nextPosition.getY()]);
				break;
			}
			nextPosition.increaseX();
			nextPosition.decreaseY();
			
		}
	}

	private boolean ourPieceAtCell(Cell[][] state, Coordinates position) {
		return state[position.getX()][position.getY()].getpiece().getcolor()==this.getcolor();
	}

	private boolean noPieceAtCell(Cell[][] state, Coordinates position) {
		return state[position.getX()][position.getY()].getpiece()==null;
	}
}