package chess;

class Board {
	private Cell[][] boardState;

	public Board() {
	}

	public Cell[][] getBoardState() {
		return boardState;
	}

	public void setBoardState(Cell[][] boardState) {
		this.boardState = boardState;
	}
	
}