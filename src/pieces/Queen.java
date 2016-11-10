package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Queen Class inherited from the abstract Piece class
 *
 */
public class Queen extends Piece{
	
	public Queen(String id,String path,int color)
	{
		setId(id);
		setPath(path);
		setColor(color);
	}
	
	public ArrayList<Cell> move(Cell state[][],Coordinates position)
	{
		possiblemoves.clear();
		
		//Checking possible moves in vertical direction
		checkW(state, position);
		checkE(state, position);
		
		
		//Checking possible moves in horizontal Direction
		checkS(state, position);
		checkN(state, position);
		
		//Checking for possible moves in diagonal direction
		checkSE(state, position);
		checkNW(state, position);
		checkSW(state, position);
		checkNE(state, position);
		return possiblemoves;
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

	private boolean noPieceAtCell(Cell[][] state, Coordinates position) {
		return state[position.getX()][position.getY()].getpiece()==null;
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

	private boolean ourPieceAtCell(Cell[][] state, Coordinates position) {
		return state[position.getX()][position.getY()].getpiece().getcolor()==this.getcolor();
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

	private void checkN(Cell[][] state, Coordinates position) {
		
		Coordinates checkPosition = position.clone();
		checkPosition.increaseY();
		
		while(checkPosition.isValid())
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
			checkPosition.increaseY();
		}
	}

	private void checkS(Cell[][] state, Coordinates position) {
		Coordinates checkPosition = position.clone();
		checkPosition.decreaseY();
		while(checkPosition.isValid())
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
			checkPosition.decreaseY();
		}
	}

	private void checkE(Cell[][] state, Coordinates position) {
		Coordinates checkPosition = position.clone();
		checkPosition.increaseX();
		
		while(checkPosition.isValid())
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
		}
	}

	private void checkW(Cell[][] state, Coordinates position) {
		Coordinates checkPosition = position.clone();
		checkPosition.decreaseX();
		while(checkPosition.isValid())
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
			checkPosition.decreaseX();;
		}
	}
}