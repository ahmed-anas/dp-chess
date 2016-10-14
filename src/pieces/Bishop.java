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
	
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		//Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
		possiblemoves.clear();
		checkSE(state, x, y);
		checkNW(state, x, y);
		checkSW(state, x, y);
		checkNE(state, x, y);
		return possiblemoves;
	}

	private void checkNE(Cell[][] state, int x, int y) {
		int tempx;
		int tempy;
		tempx=x+1;tempy=y+1;
		while(tempx<8&&tempy<8)
		{
			if(noPieceAtCell(state, tempx, tempy))
				possiblemoves.add(state[tempx][tempy]);
			else if(ourPieceAtCell(state, tempx, tempy))
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx++;
			tempy++;
		}
	}

	private void checkSW(Cell[][] state, int x, int y) {
		int tempx;
		int tempy;
		tempx=x-1;tempy=y-1;
		while(tempx>=0&&tempy>=0)
		{
			if(noPieceAtCell(state, tempx, tempy))
				possiblemoves.add(state[tempx][tempy]);
			else if(ourPieceAtCell(state, tempx, tempy))
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy--;
		}
	}

	private void checkNW(Cell[][] state, int x, int y) {
		int tempx;
		int tempy;
		tempx=x-1;tempy=y+1;
		while(tempx>=0&&tempy<8)
		{
			if(noPieceAtCell(state, tempx, tempy))
				possiblemoves.add(state[tempx][tempy]);
			else if(ourPieceAtCell(state, tempx, tempy))
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy++;
		}
	}

	private void checkSE(Cell[][] state, int x, int y) {
		int tempx=x+1,tempy=y-1;
		while(tempx<8 && tempy>=0)
		{
			if(noPieceAtCell(state, tempx, tempy))
			{
				possiblemoves.add(state[tempx][tempy]);
			}
			else if(ourPieceAtCell(state, tempx, tempy))
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx++;
			tempy--;
		}
	}

	private boolean ourPieceAtCell(Cell[][] state, int tempx, int tempy) {
		return state[tempx][tempy].getpiece().getcolor()==this.getcolor();
	}

	private boolean noPieceAtCell(Cell[][] state, int tempx, int tempy) {
		return state[tempx][tempy].getpiece()==null;
	}
}