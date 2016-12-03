package pieces;

import java.util.ArrayList;

import chess.Cell;

public class DiagonalMover extends Ability{
	
	public DiagonalMover(Piece piece){
		super(piece);
	}
	
	
	public ArrayList<Cell> move(Cell state[][],Coordinates position)
	{
		//Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
		possiblemoves.clear();
		checkSE(state, position);
		checkNW(state, position);
		checkSW(state, position);
		checkNE(state, position);
		
		possiblemoves.addAll(this.piece.move(state, position));
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
}
