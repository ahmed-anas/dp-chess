package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Rook class inherited from abstract Piece class
 *
 */
public class Rook extends Piece{
	
	public Rook(String id,String path,int color)
	{
		setId(id);
		setPath(path);
		setColor(color);
	}
	
	public ArrayList<Cell> move(Cell state[][], Coordinates position)
	{
		possiblemoves.clear();
		checkW(state, position);
		checkE(state, position);
		checkS(state, position);
		checkN(state, position);
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

	private boolean ourPieceAtCell(Cell[][] state, Coordinates position) {
		return state[position.getX()][position.getY()].getpiece().getcolor()==this.getcolor();
	}

	private boolean noPieceAtCell(Cell[][] state, Coordinates position) {
		return state[position.getX()][position.getY()].getpiece()==null;
	}
}
