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
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		
		possiblemoves.clear();
		if(white())
		{
			if(x==0)
				return possiblemoves;
			handleFirstWhiteMove(state, x, y);
			addKillMovesWhite(state, x, y);
		}
		else
		{
			if(x==8)
				return possiblemoves;
			handleFirstBlackMove(state, x, y);
			addKillMovesBlack(state, x, y);
		}
		return possiblemoves;
	}

	private void handleFirstWhiteMove(Cell[][] state, int x, int y) {
		int tempx = x-1;
		if(noPieceAtCell(state, y, tempx))
		{
			possiblemoves.add(state[x-1][y]);
			if(x==6)
			{
				if(state[4][y].getpiece()==null)
					possiblemoves.add(state[4][y]);
			}
		}
	}

	private void handleFirstBlackMove(Cell[][] state, int x, int y) {
		int tempx = x+1;
		if(noPieceAtCell(state, y, tempx))
		{
			possiblemoves.add(state[x+1][y]);
			if(x==1)
			{
				if(state[3][y].getpiece()==null)
					possiblemoves.add(state[3][y]);
			}
		}
	}

	private void addKillMovesBlack(Cell[][] state, int x, int y) {
		if((y>0)&&(state[x+1][y-1].getpiece()!=null)&&(state[x+1][y-1].getpiece().getcolor()!=this.getcolor()))
			possiblemoves.add(state[x+1][y-1]);
		if((y<7)&&(state[x+1][y+1].getpiece()!=null)&&(state[x+1][y+1].getpiece().getcolor()!=this.getcolor()))
			possiblemoves.add(state[x+1][y+1]);
	}

	private void addKillMovesWhite(Cell[][] state, int x, int y) {
		if((y>0)&&(state[x-1][y-1].getpiece()!=null)&&(state[x-1][y-1].getpiece().getcolor()!=this.getcolor()))
			possiblemoves.add(state[x-1][y-1]);
		if((y<7)&&(state[x-1][y+1].getpiece()!=null)&&(state[x-1][y+1].getpiece().getcolor()!=this.getcolor()))
			possiblemoves.add(state[x-1][y+1]);
	}

	private boolean noPieceAtCell(Cell[][] state, int y, int tempx) {
		return state[tempx][y].getpiece()==null;
	}

	private boolean white() {
		return getcolor()==0;
	}
}
