package pieces;

import java.util.ArrayList;

import chess.Cell;

class StraightMover extends Ability{
	
	StraightMover(Piece piece){
		super(piece);
	}
	@Override
	public ArrayList<Cell> move(Cell state[][], Coordinates position)
	{
		possiblemoves.clear();
		checkW(state, position);
		checkE(state, position);
		checkS(state, position);
		checkN(state, position);
		
		possiblemoves.addAll(piece.move(state, position));
		return possiblemoves;
		
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
