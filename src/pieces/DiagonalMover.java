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
	
	private void coordinatesIncrementer(Coordinates checkPosition, boolean doIncreaseX, boolean doIncreaseY){
		if(doIncreaseX){
			checkPosition.increaseX();
		}
		else
		{
			checkPosition.decreaseX();
		}
		
		if(doIncreaseY){
			checkPosition.increaseY();
		}
		else
		{
			checkPosition.decreaseY();
		}
	}

	private void checkNE(Cell[][] state, Coordinates position) {
		Coordinates checkPosition = position.clone();
		boolean doIncreaseX = true;
		boolean doIncreaseY = true;
		
		positionsChecker(state, checkPosition, doIncreaseX, doIncreaseY);
	}

	private void checkNW(Cell[][] state, Coordinates position) {
		Coordinates checkPosition = position.clone();
		boolean doIncreaseX = false;
		boolean doIncreaseY = true;
		
		positionsChecker(state, checkPosition, doIncreaseX, doIncreaseY);
	}

	private void checkSW(Cell[][] state, Coordinates position) {
		Coordinates checkPosition = position.clone();
		boolean doIncreaseX = false;
		boolean doIncreaseY = false;
		
		positionsChecker(state, checkPosition, doIncreaseX, doIncreaseY);
	}

	private void checkSE(Cell[][] state, Coordinates position) {
		Coordinates checkPosition = position.clone();
		boolean doIncreaseX = true;
		boolean doIncreaseY = false;
		
		positionsChecker(state, checkPosition, doIncreaseX, doIncreaseY);
	}


	private void positionsChecker(Cell[][] state, Coordinates checkPosition,
			boolean doIncreaseX, boolean doIncreaseY) {
		coordinatesIncrementer(checkPosition, doIncreaseX, doIncreaseY);
		while(checkPosition.isValid())
		{
			if(noPieceAtCell(state, checkPosition))
			{
				possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
			}
			else if(ourPieceAtCell(state, checkPosition))
				break;
			else
			{
				possiblemoves.add(state[checkPosition.getX()][checkPosition.getY()]);
				break;
			}
			coordinatesIncrementer(checkPosition, doIncreaseX, doIncreaseY);
		}
	}
}
