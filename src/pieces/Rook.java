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
	
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		possiblemoves.clear();
		checkW(state, x, y);
		checkE(state, x, y);
		checkS(state, x, y);
		checkN(state, x, y);
		return possiblemoves;
	}

	private void checkN(Cell[][] state, int x, int y) {
		int tempy;
		tempy=y+1;
		while(tempy<8)
		{
			if(noPieceAtCell(state, tempy, x))
				possiblemoves.add(state[x][tempy]);
			else if(ourPieceAtCell(state, tempy, x))
				break;
			else
			{
				possiblemoves.add(state[x][tempy]);
				break;
			}
			tempy++;
		}
	}

	private void checkS(Cell[][] state, int x, int y) {
		int tempy=y-1;
		while(tempy>=0)
		{
			if(noPieceAtCell(state, tempy, x))
				possiblemoves.add(state[x][tempy]);
			else if(ourPieceAtCell(state, tempy, x))
				break;
			else
			{
				possiblemoves.add(state[x][tempy]);
				break;
			}
			tempy--;
		}
	}

	private void checkE(Cell[][] state, int x, int y) {
		int tempx;
		tempx=x+1;
		while(tempx<8)
		{
			if(noPieceAtCell(state, y, tempx))
				possiblemoves.add(state[tempx][y]);
			else if(ourPieceAtCell(state, y, tempx))
				break;
			else
			{
				possiblemoves.add(state[tempx][y]);
				break;
			}
			tempx++;
		}
	}

	private void checkW(Cell[][] state, int x, int y) {
		int tempx=x-1;
		while(tempx>=0)
		{
			if(noPieceAtCell(state, y, tempx))
				possiblemoves.add(state[tempx][y]);
			else if(ourPieceAtCell(state, y, tempx))
				break;
			else
			{
				possiblemoves.add(state[tempx][y]);
				break;
			}
			tempx--;
		}
	}

	private boolean ourPieceAtCell(Cell[][] state, int y, int tempx) {
		return state[tempx][y].getpiece().getcolor()==this.getcolor();
	}

	private boolean noPieceAtCell(Cell[][] state, int y, int tempx) {
		return state[tempx][y].getpiece()==null;
	}
}
