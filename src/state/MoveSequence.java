package state;

import chess.Cell;

class MoveSequence implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int previousX;
	private final int previousY;
	private final int nextX;
	private final int nextY;
	
	public int getPreviousX() {
		return previousX;
	}

	public int getPreviousY() {
		return previousY;
	}

	public int getNextX() {
		return nextX;
	}

	public int getNextY() {
		return nextY;
	}

	public MoveSequence(){
		this.previousX = this.previousY = this.nextX = this.nextY = 0;
	}
	
	public boolean isTurnWithoutMove(){
		return (this.previousX == this.previousY) && (this.nextX == this.nextY) && this.nextX == 0 && this.previousX == 0; 
	}
	MoveSequence(
			Cell previous,
			Cell next
	)
	{
		this.previousX = previous.getCellX();
		this.previousY = previous.getCellY();
		this.nextX = next.getCellX();
		this.nextY = next.getCellY();
	}
}
