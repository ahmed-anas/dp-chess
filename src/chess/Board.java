package chess;

import pieces.Coordinates;

public class Board {
	private Cell[][] boardState;

	public Board() {
	}

	public Cell[][] getBoardState() {
		return boardState;
	}

	public void setBoardState(Cell[][] boardState) {
		this.boardState = boardState;
	}
	
	public Cell at(Coordinates position){
		return this.boardState[position.getX()][position.getY()];
	}
}